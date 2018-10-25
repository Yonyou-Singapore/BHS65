package nc.ui.so.m32.billui.action.print;

import java.awt.Font;
import java.awt.FontMetrics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.ui.pub.print.version55.util.PTStringUtil;
import nc.ui.pubapp.pub.power.PowerCheckUtils;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.bd.defdoc.DefdocVO;
import nc.vo.price.adjustprice.entity.AdjustPriceHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pub.power.PowerActionEnum;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.pub.precision.SoVoPrecionScale;

/**
 * 打印时处理打印精度
 * 
 * @since 6.0
 * @version 2010-11-4 下午07:40:24
 * @author 祝会征
 */
public class SaleinvoicePrintProcessor
		implements
		nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess {

	private AbstractAppModel model;

	/**
	 * @return model
	 */
	public AbstractAppModel getModel() {
		return this.model;
	}

	/**
	 * 父类方法重写
	 * 
	 */
	@Override
	public Object[] processData(Object[] datas) {
		
		int len = 0;
		// 第一页可用高度
		double firstPageHeight = 255;
		// 后续页的可用高度
		double perpageHeight = 300;
		// Descrption行的宽度
		double columnWidth = 224.0;
		// 没页能显示最大文字行数
		int perPageMaxRows = 1;
		
		IUAPQueryBS query = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Collection<DefdocVO> defdocs = null;
		try {
			defdocs = query.retrieveByClause(DefdocVO.class, " pk_defdoclist in (select pk_defdoclist from bd_defdoclist where code = 'BHS99') ");
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(defdocs != null){
			for(DefdocVO vo : defdocs){
				if("firstPageHeight".equals(vo.getCode())){
					firstPageHeight = new Double(vo.getName());
				}
				if("perpageHeight".equals(vo.getCode())){
					perpageHeight = new Double(vo.getName());
				}
				if("columnWidth".equals(vo.getCode())){
					columnWidth = new Double(vo.getName());
				}
			}
		}
		
		// 字体高度
		FontMetrics fm = getFontMetrics();
		int ascent = fm.getAscent();
		int descent = fm.getDescent();
		int fontHeight = ascent + descent;
		
		List<String> finalWords = null;
		int totalWordRows = 0;
		int wordRowNo = 0;
		int leftWordRows = 0;
		StringBuffer wordBuff = null;

		int snno = 1;
		
		// 转化为销售发票vo
		SaleInvoiceVO[] vos = new SaleInvoiceVO[datas.length];
		for (int i = 0; i < datas.length; i++) {
			snno = 1;
			
			vos[i] = (SaleInvoiceVO) ((SaleInvoiceVO) this.model.getSelectedData()).clone();

			SaleInvoiceBVO[] bvos = vos[i].getChildrenVO();
			if (bvos == null || bvos.length < 1)
				continue;
			List<SaleInvoiceBVO> bvoLst = new ArrayList<SaleInvoiceBVO>();

			perPageMaxRows = (int) (firstPageHeight / fontHeight);

			for (int bvoRowNo = 0; bvoRowNo < bvos.length; bvoRowNo++) {
				SaleInvoiceBVO bvo = bvos[bvoRowNo];
				String description = bvo.getVbdef2();
				finalWords = getFinalRows(description, columnWidth);
				totalWordRows = finalWords.size();
				if(bvo.getCsaleinvoicebid() != null){
					bvo.setCrowno("" + snno);
					snno++;
				}

				if (bvoRowNo > 0 
						//add chenth 20181025
						&& perPageMaxRows <= 0
						//add end
						) {
					perPageMaxRows = (int) (perpageHeight / fontHeight);
				}
				// 没有超一页
				if (totalWordRows <= perPageMaxRows) {
					bvoLst.add(bvo);
					//add 少过半页  处理下继续能打下一行 chenth 20181025
					if(totalWordRows<perPageMaxRows/2){
						perPageMaxRows = perPageMaxRows - totalWordRows;
					}
					//add end
					continue;
				}

				// 超页了，做分拆
				// 1、拆第一页
				wordBuff = new StringBuffer();
				for (wordRowNo = 0; wordRowNo < perPageMaxRows; wordRowNo++) {
					//len = len + finalWords.get(wordRowNo).length();
					wordBuff = wordBuff.append(finalWords.get(wordRowNo)).append("\n");
				}
				bvo.setVbdef2(wordBuff.substring(0, wordBuff.length()-1));
//				description = description.substring(len, description.length());
				bvoLst.add(bvo);

				// 第一行拆完第一页，页面高度有调整
				if (bvoRowNo == 0) {
					perPageMaxRows = (int) (perpageHeight / fontHeight);
				}
				
				leftWordRows = totalWordRows - wordRowNo;
				while (leftWordRows > 0) {
					//add chenth 20181025
					if (perPageMaxRows <= 0
							|| perPageMaxRows < ((perpageHeight / fontHeight)/3)*2) {
						perPageMaxRows = (int) (perpageHeight / fontHeight);
					}
					//add end
					if (leftWordRows > perPageMaxRows) {
						leftWordRows = wordRowNo+perPageMaxRows;
					}else{
						leftWordRows = wordRowNo+leftWordRows;
					}
					wordBuff = new StringBuffer();
					for (; wordRowNo < leftWordRows; wordRowNo++) {
						len = len + finalWords.get(wordRowNo).length();
						wordBuff = wordBuff.append(finalWords.get(wordRowNo)).append("\n");
						//add chenth 20181025
						perPageMaxRows = perPageMaxRows - 1;
						//add end
					}
					SaleInvoiceBVO newbvo = new SaleInvoiceBVO();
					bvoLst.add(newbvo);

					//newbvo.setVbdef2(description.substring(0, len));
					newbvo.setVbdef2(wordBuff.substring(0, wordBuff.length()-1));
//					description = description.substring(len,
//							description.length());

					leftWordRows = totalWordRows - wordRowNo;
				}
				perPageMaxRows = 0;
			}
			bvos = bvoLst.toArray(new SaleInvoiceBVO[bvoLst.size()]);
		    vos[i].setChildrenVO(bvos);
		}

		// 权限校验
		PowerCheckUtils
				.checkHasPermission(vos, SOBillType.Invoice.getCode(),
						PowerActionEnum.PRINT.getActioncode(),
						AdjustPriceHVO.VBILLCODE);

		// vos = (SaleInvoiceVO[]) datas;
		// 精度处理
		SoVoPrecionScale handler = new SoVoPrecionScale(this.getModel()
				.getContext().getPk_group(), vos);
		handler.setScale();

		return vos;

	}

	private List<String> getFinalRows(String description, double width) {
		List<String> finalRows = new ArrayList<String>();
		if (description == null || description.trim().length() < 1) {
			finalRows.add(description);
			return finalRows;
		}

		// 字体高度
		FontMetrics fm = getFontMetrics();

		double wide = 0; // 所有行中最长的字符串长度

		List<String> rows = new ArrayList<String>();
		// 存在换行符 add chenth 20170713 新加坡
		rows = PTStringUtil.getConentOfLineSeparators(description);

		finalRows = new ArrayList<String>();
		for (ListIterator<String> rowItt = rows.listIterator(); rowItt
				.hasNext();) {
			String row = rowItt.next();

			// add chenth 20170713 新加坡 中间空行也要打印
			if ("".equals(row.trim())) {
				finalRows.add(row);
				continue;
			}

			// List<String> childRows = new ArrayList<String>();
			String[] words = row.split(" ");

			if (words == null || words.length == 0) {
				continue;
			}

			double wordWidth = fm.stringWidth(words[0]);
			wide = wordWidth;
			StringBuffer childRow = new StringBuffer(words[0]);
			for (int i = 1; i < words.length; i++) {
				String word = words[i];

				if (word == null || word.length() == 0)
					continue;

				double thiswordWidth = fm.stringWidth(" " + word);

				if ((wordWidth + thiswordWidth) > width) {
					// childRows.add(childRow.toString());
					finalRows.add(childRow.toString());
					childRow = new StringBuffer();
					childRow.append(word);
					wordWidth = fm.stringWidth(word);
					if (wordWidth > wide)
						wide = wordWidth;
					continue;
				}

				childRow.append(" ");
				childRow.append(word);
				wordWidth += thiswordWidth;
			}

			if (childRow.length() > 0)
				finalRows.add(childRow.toString());
		}
		return finalRows;
	}

	private FontMetrics getFontMetrics() {
		Font font = new Font("Times New Roman", Font.PLAIN, 9);
		FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(font);
		return fm;

		// Style style = new Style();
		// style.setHorAlign(Style.LEFT);
		// style.setVerAlign(Style.TOP);
		// MDFont mdfont = style.getFont();

		// Line line = LineFactory.createSingleLine(0, 0, MDColor.BLACK, false);
		// MDFont mdfont = MDFontFactory.createFont(null, "Times New Roman", 9,
		// 0, line);
		// return mdfont;
	}

	/**
	 * @param model
	 *            要设置的 model
	 */
	public void setModel(AbstractAppModel model) {
		this.model = model;
	}

}
