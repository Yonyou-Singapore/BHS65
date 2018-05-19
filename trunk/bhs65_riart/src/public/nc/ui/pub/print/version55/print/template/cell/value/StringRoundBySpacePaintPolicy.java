package nc.ui.pub.print.version55.print.template.cell.value;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import nc.ui.pub.print.version55.log.PrintLog;
import nc.ui.pub.print.version55.print.template.cell.style.Line;
import nc.ui.pub.print.version55.print.template.cell.style.MDFont;
import nc.ui.pub.print.version55.print.template.cell.style.MDFontFactory;
import nc.ui.pub.print.version55.print.template.cell.style.Style;
import nc.ui.pub.print.version55.util.DoublePoint;
import nc.ui.pub.print.version55.util.PTStringUtil;

/**
 * <b>   </b>
 *
 * <p>  
 *
 * </p>
 *
 * Create at 2008-12-30 ����08:32:03
 * 
 * @author bq 
 * @since V5.5
 */

public class StringRoundBySpacePaintPolicy extends AbstractStringPaintPolicy{

	public void paint(Graphics2D g, String text, Style style, Rectangle2D rect) {
		if (text == null)
			return;
		
		if(style == null)
			style = new Style();
		
		double lineSpaceBetween = 0;
		
		try {
			
			Font oldFont = g.getFont();
			Color oldColor = g.getColor();
			
			MDFont mdfont = style.getFont();
			if(mdfont == null)
				mdfont = MDFontFactory.createFont();
			
			Font font = mdfont.getFont();
			FontMetrics fm = g.getFontMetrics(font);
			int ascent = fm.getAscent();
			int descent = fm.getDescent();
			int fontHeight = ascent+descent;

			if (fontHeight <= 0)
				return;

			g.setFont(font);
			g.setColor(mdfont.getColor().getColor());
			
			//
//			double permitRowNum = rect.getHeight()/ fontHeight; // ��Ԫ��߶������ӡ������ 
//			int allPrintRowNum = vec.size(); //��Ԫ��������Ҫ��ӡ������
			int allPrintRowNum  = 1;
			double wide = 0; // ������������ַ�������
//			String curStr = "";
			
			double width = rect.getWidth();
			
			
			List<String> rows = new ArrayList<String>();
//			for(int i = 0; i < vec.size(); i++)
//				rows.add((String)vec.elementAt(i));
			//rows.add(text);
			//���ڻ��з� add chenth 20170713 �¼��� 
			rows = PTStringUtil.getConentOfLineSeparators(text);
			
			List<String> finalRows = new ArrayList<String>();
			for(ListIterator<String> rowItt  = rows.listIterator(); rowItt.hasNext();) {
				String row = rowItt.next();
				
				// add chenth 20170713 �¼���  �м����ҲҪ��ӡ
				if("".equals(row.trim())){
					finalRows.add(row);
					continue;
				}
				
//				List<String> childRows = new ArrayList<String>();
				String[] words = row.split(" ");
				
				if(words == null || words.length == 0){
					continue;
				}
				
				double wordWidth = fm.stringWidth(words[0]);
				wide = wordWidth;
				StringBuffer childRow = new StringBuffer(words[0]);
				for(int i = 1; i < words.length; i++) {
					String word = words[i];
					
					if(word == null || word.length() == 0)
						continue;
					
					double thiswordWidth = fm.stringWidth(" " + word);
					
					if((wordWidth + thiswordWidth) > width) {
//						childRows.add(childRow.toString());
						finalRows.add(childRow.toString());
						childRow = new StringBuffer();
						childRow.append(word);
						wordWidth = fm.stringWidth(word);
						if(wordWidth > wide)
							wide = wordWidth;
						continue;
					}
					
					childRow.append(" ");
					childRow.append(word);
					wordWidth += thiswordWidth;
				}
				
				if(childRow.length() > 0)
					finalRows.add(childRow.toString());
					
			}
			
			// ������������������ֱ����λ��
			allPrintRowNum = finalRows.size();
			
			/* ���㵥Ԫ��ɻ���������*/
			double rectPrintWidth = getPrintWidthOfCell(rect.getWidth(), style);
			
			if (wide > (rect.getWidth() - 2))
				wide = rect.getWidth() - 2; //���п��
				
			lineSpaceBetween = style.getLineSpace().getPt();
			int highs = fontHeight * allPrintRowNum  + (int)lineSpaceBetween * (allPrintRowNum - 1); // ���и߶�
			
			// �����ӡλ��  
			int incry = 0;
			if (style.getVerAlign() == Style.TOP)
				incry = 0;
			else
				if (style.getVerAlign() == Style.MIDDLE)
					incry = (int)(rect.getHeight() - highs) / 2;
				else
					if (style.getVerAlign() == Style.BOTTOM)
						incry = (int)rect.getHeight() - highs - descent;
			
			// �»���
			Line underLine = style.getFont().getUnderLine();
			boolean isFiscal = MDFont.isFiscalUnderLine(underLine);
			
			// ����ÿһ��
			int i = 0;
			for(String row : finalRows) {
				wide = fm.stringWidth(row);
				
				int incrx = getIncrx(style, rect.getWidth(), wide);
				
				int cry = (int) (rect.getY() + (i + 1) * fontHeight + i
						* lineSpaceBetween + incry - 2);
				g.drawString(row, (int)(rect.getX() + incrx), cry);
								
				// �����»���
				if(!isFiscal && underLine != null) {
					cry = cry + descent;
					DoublePoint from = new DoublePoint(rect.getX() + incrx, cry);
					DoublePoint to = new DoublePoint(rect.getX() + incrx + wide, cry);
					underLine.paint(g, from, to);
				}
				
				i++;
			}
			
			// ���ƻ���»���
			if(isFiscal && underLine != null) {
				int crx = isDoubleLineOfLeftBorder(style) ? 2 : 0;
				int cry = (int) (rect.getY() + allPrintRowNum * fontHeight
						+ (allPrintRowNum - 1) * lineSpaceBetween + incry - 2
						+ descent + 1);
				DoublePoint from = new DoublePoint(rect.getX() + crx, cry);
				DoublePoint to = new DoublePoint(rect.getX() + crx + rectPrintWidth, cry);
				underLine.paint(g, from, to);
			}
			
			g.setColor(oldColor);
			g.setFont(oldFont);
			
		} catch (Exception e) {
			nc.bs.logging.Logger.error(e.getMessage(), e);			
			PrintLog.debug("error! ���ִ���");/*-=notranslate=-*/ 
		}
		
		//new StringBendPaintPolicy().paint(g, text, style, rect);
		
	}

	
}
