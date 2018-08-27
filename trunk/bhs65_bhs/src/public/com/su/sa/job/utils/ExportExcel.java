/**
 * ExportExcel.java
 * Created at 2014-10-10
 * Created by Administrator
 * Copyright (C) 2014 SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.su.sa.job.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.Log;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.Region;
import org.springframework.stereotype.Service;

/**
 * <p>
 * ClassName: ExportExcel
 * </p>
 * <p>
 * Description: ����excel
 * </p>
 * <p>
 * Author: bird
 * </p>
 * <p>
 * Date: 2014-10-11
 * </p>
 */
@Service
public class ExportExcel {
    /**
     * ��־
     */
    public static Logger logger = Logger.getLogger(ExportExcel.class);

    /**
     * ��ɫ
     */
    private static final List<String> COLORRED = new ArrayList<String>();

    /**
     * ��ɫ
     */
    private static final List<String> COLORBLUE = new ArrayList<String>();

    static {
        //��ɫ
        COLORRED.add("��Ч���쳣");
        COLORRED.add("����δ��");
        COLORRED.add("ȱʧ�ϰ��");
        COLORRED.add("ȱʧ�°��");
        COLORRED.add("�ϰ�ٵ�");
        COLORRED.add("�°�����");
        COLORRED.add("�ϰ�ٵ����°�����");
        COLORRED.add("�ϰ�����쳣");
        COLORRED.add("�°�����쳣");
        COLORRED.add("����ٵ������쳣��");
        COLORRED.add("�������˰����쳣��");
        COLORRED.add("����ٵ���������");
        COLORRED.add("����������������");
        COLORRED.add("�쳣��");
        COLORRED.add("�������쳣");
        //��ɫ
        COLORBLUE.add("��ɫ");
    }

    /**
     * Description:�ޱ�ͷEXCEL
     * 
     * @param title ����
     * @param dataset ���ݼ���
     * @return string
     * @throws Exception ҵ���쳣����
     */
    public static String exportExcel(String title, Collection dataset,String path) throws Exception {
        return exportex(title, null, null, dataset,path);
    }

    /**
     * Description:�ޱ�ͷEXCEL
     * 
     * @param title ����
     * @param dataset ���ݼ���
     * @return string
     * @throws Exception ҵ���쳣����
     */
    public static String exportExcel(String title, List<String[]> dataset,String path) throws Exception {
        return exportex(title, null, dataset, null,path);
    }

    /**
     * Description:�б�ͷEXCEL
     * 
     * @param title ����
     * @param headers ��ͷ
     * @param dataset ���ݼ���
     * @return string ����ֵ
     * @throws Exception ҵ���쳣����
     */
    public static String exportExcel(String title, String[] headers, Collection dataset,String path) throws Exception {
        return exportex(title, headers, null, dataset,path);
    }

    /**
     * Description:�б�ͷEXCEL
     * 
     * 
     * @param title ����
     * @param headers ��ͷ
     * @param dataset ���ݼ���
     * @return String
     * @throws Exception ҵ���쳣
     */
    public static String exportExcel(String title, String[] headers, List dataset,String path) throws Exception {
        return exportex(title, headers, dataset, null,path);
    }

    /**
     * Description:����excel �����ʱ�����ݣ��趨�����ʽ��Ĭ��Ϊ"yyy-MM-dd"
     * 
     * @param title ��������
     * @param headers ���������������
     * @param obj ���ݶ���
     * @param dataset ��Ҫ��ʾ�����ݼ���
     * @return String ����ֵ
     * @throws Exception ҵ���쳣����
     */
    private static String exportex(String title, String[] headers, List obj, Collection dataset,String path) throws Exception {
        logger.debug("��ʼ����excel");
        FileOutputStream fOut = null;
        String fileName = "";
        //�����ļ���
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String excelpath = path + File.separator + sf.format(new Date());
        try {
            fileName = title +"_"+DateUtils.format(new Date(), "yyyyMMddHHmmss") + ".xls"; //������Excel�ļ�������
//            String path = excelpath;
            String xlsFile = excelpath + File.separator + fileName;
            File file = new File(excelpath);
            if (!file.exists()) {
                file.mkdir();
            }
            File xFile = new File(xlsFile);
            if (!xFile.exists()) {
                xFile.createNewFile();
            }
            fOut = new FileOutputStream(xlsFile);

            // ����һ��������
            HSSFWorkbook workbook = new HSSFWorkbook();
            // ����һ�����
            HSSFSheet sheet = workbook.createSheet(title);

            HSSFCellStyle titleStyle = setTileStyle(workbook);
            HSSFCellStyle bodyStyle = setBodyStyle(workbook);

            // ������������
            HSSFRow row = sheet.createRow(0);
            row.setHeightInPoints(20f);
            // ���ñ��Ĭ���п��Ϊ15���ֽ�
            sheet.setDefaultColumnWidth(20);

            if (null != headers && headers.length > 0) {
                for (int i = 0; i < headers.length; i++) {
                    // ����Ӧ���
                    // sheet.autoSizeColumn(i);
                    HSSFCell cell = row.createCell(i);
                    cell.setCellStyle(titleStyle);
                    HSSFRichTextString text = new HSSFRichTextString(headers[i]);
                    cell.setCellValue(text);

                }
            }

            if (null != dataset) {
                setdaClass(sheet, dataset, row, bodyStyle);
            } else {
                setDataByObject(sheet, obj, row, bodyStyle);
            }
            workbook.write(fOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";

        } finally {
            if (null != fOut) {
                fOut.close();
            }
        }
        logger.debug("����excel����");
        return fileName;
    }

    /**
     * Description:���鷽ʽ���ñ�������
     * 
     * @param sheet ������
     * @param obj ���ݼ���
     * @param row ��
     * @param body ��ʽ
     */
    private static void setDataByObject(HSSFSheet sheet, List<String[]> obj, HSSFRow row, HSSFCellStyle body) {
        int index = 0;
        for (String[] s : obj) {
            index++;
            row = sheet.createRow(index);
            row.setHeightInPoints(20f);
            for (int i = 0; i < s.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(body);
                cell.setCellValue(s[i]);
            }
        }
    }

    /**
     * 
     * <p>
     * Description: ���䷽ʽ���ñ�������
     * </p>
     * 
     * @param sh ������
     * @param ds ���ݼ���
     * @param row ��
     * @param bo ��ʽ
     * @throws Exception ҵ���쳣����
     */
    private static void setdaClass(HSSFSheet sh, Collection ds, HSSFRow row, HSSFCellStyle bo) throws Exception {
        // �����������ݣ�����������
        Iterator<Object> it = ds.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sh.createRow(index);
            row.setHeightInPoints(20f);
            Object t = (Object) it.next();

            Field[] fields = t.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(bo);
                Field field = fields[i];
                String fieldName = field.getName();
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                if ("getSerialVersionUID".equals(getMethodName)) {
                    continue;
                }

                Class<?> tCls = t.getClass();
                Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
                Object value = getMethod.invoke(t, new Object[] {});
                // �ж�ֵ�����ͺ����ǿ������ת��
                String textValue = null;
                if (value instanceof Integer) {
                    int intValue = (Integer) value;
                    textValue = String.valueOf(intValue);
                } else if (value instanceof Float) {
                    float fValue = (Float) value;
                    textValue = String.valueOf(fValue);
                } else if (value instanceof Double) {
                    double dValue = (Double) value;
                    textValue = String.valueOf(dValue);
                } else if (value instanceof Long) {
                    long longValue = (Long) value;
                    textValue = String.valueOf(longValue);
                } else if (value instanceof Boolean) {
                    boolean bValue = (Boolean) value;
                    textValue = "��";
                    if (!bValue) {
                        textValue = "��";
                    }
                } else if (value instanceof Date) {
                    Date date = (Date) value;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    textValue = sdf.format(date);
                } else if (value instanceof byte[]) {

                } else {
                    if (null != value) {
                        textValue = value.toString();
                    }
                }
               // textValue = StringUtils.replaceHtml(textValue);
                cell.setCellValue(textValue);

            }
        }
    }

    /**
     * Description:���ñ�ͷ��ʽ
     * 
     * @param workbook ������
     * @return ����ֵ
     */
    private static HSSFCellStyle setTileStyle(HSSFWorkbook workbook) {

        // ����һ����ʽ
        HSSFCellStyle titleStyle = workbook.createCellStyle();
        // ���ñ�ͷ��ʽ
        // titleStyle.setFillForegroundColor(HSSFColor.LEMON_CHIFFON.index);
        //titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // ��������
        HSSFFont titleFont = workbook.createFont();
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //        titleFont.setFontHeightInPoints((short) NumberUtils.NUM_14);
        //        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // ������Ӧ�õ���ǰ����ʽ
        titleStyle.setFont(titleFont);

        // ����һ����ͼ�Ķ���������
        // HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // ����ע�͵Ĵ�С��λ��,����ĵ�
        // HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
        // 0, 0, 0, (short) 4, 2, (short) 6, 5));
        // ����ע������
        // comment.setString(new HSSFRichTextString("������POI�����ע�ͣ�"));
        // ����ע�����ߣ�������ƶ�����Ԫ�����ǿ�����״̬���п���������.
        // comment.setAuthor("");

        return titleStyle;
    }

    /**
     * Description:���ñ�����ʽ
     * 
     * @param workbook ������
     * @return ����ֵ
     */
    private static HSSFCellStyle setBodyStyle(HSSFWorkbook workbook) {
        // ����������ʽ
        HSSFCellStyle bodyStyle = workbook.createCellStyle();

        bodyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        bodyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        bodyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        bodyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        bodyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        bodyStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // ��������
        HSSFFont bodyFont = workbook.createFont();
        bodyFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // ������Ӧ�õ���ǰ����ʽ
        bodyStyle.setFont(bodyFont);
        return bodyStyle;
    }

    /**
     * Description:���ñ�����ʽ
     * 
     * @param workbook ������
     * @return ����ֵ
     */
    private static Map<String, HSSFCellStyle> getBodyStyleMap(HSSFWorkbook workbook) {
        Map<String, HSSFCellStyle> styleMap = new HashMap<String, HSSFCellStyle>();
        // ����������ʽ
        HSSFCellStyle bodyStyle = workbook.createCellStyle();

        bodyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        bodyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        bodyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        bodyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        bodyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        bodyStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // ��������
        HSSFFont bodyFont = workbook.createFont();
        bodyFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // ������Ӧ�õ���ǰ����ʽ
        bodyStyle.setFont(bodyFont);
        //��ɫ
        styleMap.put("black", bodyStyle);

        //��ɫ
        // ����������ʽ
        HSSFCellStyle bodyStyleRed = workbook.createCellStyle();

        bodyStyleRed.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        bodyStyleRed.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        bodyStyleRed.setBorderRight(HSSFCellStyle.BORDER_THIN);
        bodyStyleRed.setBorderTop(HSSFCellStyle.BORDER_THIN);
        bodyStyleRed.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        bodyStyleRed.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // ��������
        HSSFFont bodyFontRed = workbook.createFont();
        bodyFontRed.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        bodyFontRed.setColor(HSSFColor.RED.index);
        // ������Ӧ�õ���ǰ����ʽ
        bodyStyleRed.setFont(bodyFontRed);
        styleMap.put("red", bodyStyleRed);
        //��ɫ
        // ����������ʽ
        HSSFCellStyle bodyStyleBlue = workbook.createCellStyle();

        bodyStyleBlue.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        bodyStyleBlue.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        bodyStyleBlue.setBorderRight(HSSFCellStyle.BORDER_THIN);
        bodyStyleBlue.setBorderTop(HSSFCellStyle.BORDER_THIN);
        bodyStyleBlue.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        bodyStyleBlue.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // ��������
        HSSFFont bodyFontBlue = workbook.createFont();
        bodyFontBlue.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        bodyFontBlue.setColor(HSSFColor.BLUE.index);
        // ������Ӧ�õ���ǰ����ʽ
        bodyStyleBlue.setFont(bodyFontBlue);
        styleMap.put("blue", bodyStyleBlue);

        //��ɫ
        // ����������ʽ
        HSSFCellStyle bodyStyleGrey = workbook.createCellStyle();

        bodyStyleGrey.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        bodyStyleGrey.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        bodyStyleGrey.setBorderRight(HSSFCellStyle.BORDER_THIN);
        bodyStyleGrey.setBorderTop(HSSFCellStyle.BORDER_THIN);
        bodyStyleGrey.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        bodyStyleGrey.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // ��������
        HSSFFont bodyFontGrey = workbook.createFont();
        bodyFontGrey.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        bodyFontGrey.setColor(HSSFColor.GREY_50_PERCENT.index);
        bodyFontGrey.setUnderline((byte) 1);
        bodyFontGrey.setItalic(true);
        // ������Ӧ�õ���ǰ����ʽ
        bodyStyleGrey.setFont(bodyFontGrey);
        //bodyStyleGrey.set
        styleMap.put("grey", bodyStyleGrey);

        
        //��ɫ
        // ����������ʽ
        HSSFCellStyle bodyStyleOrange = workbook.createCellStyle();

        bodyStyleOrange.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        bodyStyleOrange.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        bodyStyleOrange.setBorderRight(HSSFCellStyle.BORDER_THIN);
        bodyStyleOrange.setBorderTop(HSSFCellStyle.BORDER_THIN);
        bodyStyleOrange.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        bodyStyleOrange.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // ��������
        HSSFFont bodyFontOrange = workbook.createFont();
        bodyFontOrange.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        bodyFontOrange.setColor(HSSFColor.ORANGE.index);
        // ������Ӧ�õ���ǰ����ʽ
        bodyStyleOrange.setFont(bodyFontOrange);
        //bodyStyleGrey.set
        styleMap.put("orange", bodyStyleOrange);
        return styleMap;
    }

    /**
     * Description:���ñ�����ʽ��ɫ����
     * 
     * @param workbook ������
     * @return ����ֵ
     */
    private static HSSFCellStyle setBodyStyleRed(HSSFWorkbook workbook) {
        // ����������ʽ
        HSSFCellStyle bodyStyle = workbook.createCellStyle();

        bodyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        bodyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        bodyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        bodyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        bodyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        bodyStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // ��������
        HSSFFont bodyFont = workbook.createFont();
        bodyFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // ������Ӧ�õ���ǰ����ʽ
        bodyStyle.setFont(bodyFont);
        return bodyStyle;
    }

    /**
     * 
     * <p>
     * Description: �����ı�����������ɫ
     * </p>
     * 
     * @param str �ı�
     * @return ����
     */
    private static String getFontColorByStr(String str) {
        //Ĭ�Ϻ�ɫ
        String key = "black";
        if (str!=null && !str.equals("")) {
            if (COLORRED.contains(str)) {
                //��ɫ
                key = "red";
            } else if (COLORBLUE.contains(str)) {
                //��ɫ
                key = "blue";
            } else if ("��".equals(str)) {
                //��ɫ
                key = "grey";
            } else {
                //��ɫ
                key = "black";
            }
            //            String fstr = str.substring(0, 1);
            //            if ("-".equals(fstr)) {
            //                //��ɫ(����)
            //                key = "red";
            //            }
            if (str.indexOf("-") == 0 || str.indexOf("Сʱ-") != -1) {
                key = "red";
            }
            if (str.indexOf("color='red'") != -1 || str.indexOf("color=\"red\"") != -1
                    || str.indexOf("color:red") != -1) {
                key = "red";
            }
            if (str.indexOf("color='blue'") != -1 || str.indexOf("color=\"blue\"") != -1
                    || str.indexOf("color:blue") != -1) {
                key = "blue";
            }
            if (str.indexOf("color='orange'") != -1 || str.indexOf("color=\"orange\"") != -1
                    || str.indexOf("color:orange") != -1) {
                key = "orange";
            }
        }
        return key;
    }

    /**
     * Description:�������sheet excel
     * 
     * @param paramList ��������
     * @param expTableList ��ͷ
     * @return String ����ֵ����
     * @throws Exception ҵ���쳣
     */
    public static String exportReportList(List<Map<String, Object>> paramList,
            List<LinkedHashMap<String, String>> expTableList) throws Exception {
        logger.debug("��ʼ����excel");
        FileOutputStream fOut = null;
        String fileName = "";
        String excelpath = FileUtils.excelpath;
        try {
            fileName = DateUtils.format(new Date(), "yyyyMMddHHmmss") + ".xls"; //������Excel�ļ�������
            String path = excelpath;
            String xlsFile = excelpath + File.separator + fileName;
            File file = new File(path);
            if (!file.exists()) {
                file.mkdir();
            }
            File xFile = new File(xlsFile);
            if (!xFile.exists()) {
                xFile.createNewFile();
            }
            fOut = new FileOutputStream(xlsFile);
            // ����һ��������
            HSSFWorkbook workbook = new HSSFWorkbook();
            for (Map<String, Object> map : paramList) {
                LinkedHashMap<String, String> cmlMap = (LinkedHashMap<String, String>) map.get("cmlMap");
                List dataList = (List) map.get("dataList");
                List<LinkedHashMap<String, String>> topList = null;
                if (null != map.get("topList")) {
                    topList = (List<LinkedHashMap<String, String>>) map.get("topList");
                }
                setExcelSheet(String.valueOf(map.get("title")), workbook, cmlMap, dataList, expTableList, topList);
            }

            workbook.write(fOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";

        } finally {
            if (null != fOut) {
                fOut.close();
            }
        }
        logger.debug("����excel����");
        return fileName;
    }

    /**
     * Description:����excel �����ʱ�����ݣ��趨�����ʽ��Ĭ��Ϊ"yyy-MM-dd"
     * 
     * @param title ����
     * @param mp ����
     * @param dl ���ݶ���
     * @param expTableList ��ͷ����
     * @return String ����ֵ
     * @throws Exception ҵ���쳣����
     */
    public static String exportReport(String title, LinkedHashMap<String, String> mp, List dl,
            List<LinkedHashMap<String, String>> expTableList) throws Exception {
        logger.debug("��ʼ����excel");
        FileOutputStream fOut = null;
        String fileName = "";
        String excelpath = FileUtils.excelpath;
        try {
            fileName = DateUtils.format(new Date(), "yyyyMMddHHmmss") + ".xls"; //������Excel�ļ�������
            String path = excelpath;
            String xlsFile = path + File.separator + fileName;
            File file = new File(path);
            if (!file.exists()) {
                file.mkdir();
            }
            File xFile = new File(xlsFile);
            if (!xFile.exists()) {
                xFile.createNewFile();
            }
            fOut = new FileOutputStream(xlsFile);

            // ����һ��������
            HSSFWorkbook workbook = new HSSFWorkbook();
            setExcelSheet(title, workbook, mp, dl, expTableList);
            workbook.write(fOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        } finally {
            if (null != fOut) {
                fOut.close();
            }
        }
        logger.debug("����excel����");
        return fileName;
    }

    /**
     * Description:����excel �����ʱ�����ݣ��趨�����ʽ��Ĭ��Ϊ"yyy-MM-dd"
     * 
     * @param title ����
     * @param cmlMap key:������ value:����
     * @param dataList ���ݶ���
     * @param expTableList ��ͷ����
     * @return String ����ֵ
     * @throws Exception ҵ���쳣����
     */
    public static String exportReportMap(String title, LinkedHashMap<String, String> cmlMap,
            List<Map<String, Object>> dataList, List<LinkedHashMap<String, String>> expTableList) throws Exception {
        logger.debug("��ʼ����excel");
        FileOutputStream fOut = null;
        String fileName = "";
        String excelpath = FileUtils.excelpath;
        try {
            fileName = DateUtils.format(new Date(), "yyyyMMddHHmmss") + ".xls"; //������Excel�ļ�������
            String path = excelpath;
            String xlsFile = path + File.separator + fileName;
            File file = new File(path);
            if (!file.exists()) {
                file.mkdir();
            }
            File xFile = new File(xlsFile);
            if (!xFile.exists()) {
                xFile.createNewFile();
            }
            fOut = new FileOutputStream(xlsFile);

            // ����һ��������
            HSSFWorkbook workbook = new HSSFWorkbook();
            setExcelSheetMap(title, workbook, cmlMap, dataList, expTableList);
            workbook.write(fOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";

        } finally {
            if (null != fOut) {
                fOut.close();
            }
        }
        logger.debug("����excel����");
        return fileName;
    }

    /**
     * <p>
     * Description: ����sheet
     * </p>
     * 
     * @param title ����
     * @param workbook excel����
     * @param cmlMap ��
     * @param dataList ����
     * @param expTableList ��ͷ
     * @throws Exception ҵ���쳣����
     */
    private static void setExcelSheet(String title, HSSFWorkbook workbook, LinkedHashMap<String, String> cmlMap,
            List<?> dataList, List<LinkedHashMap<String, String>> expTableList) throws Exception {
        // ����һ�����
        HSSFSheet sheet = workbook.createSheet(title);
        HSSFCellStyle titleStyle = setTileStyle(workbook);
        HSSFCellStyle bodyStyle = setBodyStyle(workbook);
        Map<String, HSSFCellStyle> styleMap = getBodyStyleMap(workbook);
        styleMap.put("default", bodyStyle);

        // ������������
        HSSFRow row = sheet.createRow(0);
        // ���ñ��Ĭ���п��Ϊ20���ֽ�
        //sheet.setDefaultColumnWidth(NumberUtils.NUM_15);
        row.setHeightInPoints(20f);

        int cmlIndex = 0;
        LinkedHashMap<String, Integer> cmlIndexMap = new LinkedHashMap<String, Integer>();
        if (null != cmlMap && !cmlMap.isEmpty()) {
            int columnNum = cmlMap.size();
            int first = columnNum / 2;
            int second = columnNum;
            int s = 0;
            if (null != expTableList && !expTableList.isEmpty()) {
                int count = expTableList.size();
                for (int i = 0; i < count; i++) {
                    if (first > 0) {
                        int fag = i % 2;
                        if (fag == 0) {
                            // ������������

                            if (i > 0) {
                                row = sheet.createRow(s);
                                row.setHeightInPoints(20f);
                            }
                            // ���ñ��Ĭ���п��Ϊ20���ֽ�
                            //sheet.setDefaultColumnWidth(NumberUtils.NUM_15);

                            sheet.addMergedRegion(new Region(s, (short) 0, s, (short) (first - 1)));
                            HSSFCell firstcell = row.createCell(0);
                            firstcell.setCellValue(expTableList.get(i).get("name") + expTableList.get(i).get("value"));
                            int j = i + 1;
                            if (j < count) {
                                sheet.addMergedRegion(new Region(s, (short) first, s, (short) (second - 1)));
                                HSSFCell secondcell = row.createCell(first);
                                secondcell.setCellValue(expTableList.get(j).get("name")
                                        + expTableList.get(j).get("value"));
                            }

                            s++;
                        }
                    } else {
                        if (i > 0) {
                            row = sheet.createRow(i);
                            row.setHeightInPoints(20f);
                        }
                        HSSFCell firstcell = row.createCell(0);
                        firstcell.setCellValue(expTableList.get(i).get("name") + expTableList.get(i).get("value"));
                    }

                }
            }
            if (s > 0) {
                row = sheet.createRow(s);
                row.setHeightInPoints(20f);
            }
            for (String cml : cmlMap.keySet()) {
                // ����Ӧ���
                sheet.autoSizeColumn(cmlIndex);
                HSSFCell cell = row.createCell(cmlIndex);
                cell.setCellStyle(titleStyle);
                HSSFRichTextString text = new HSSFRichTextString(cmlMap.get(cml));
                cell.setCellValue(text);
                cmlIndexMap.put(cml, cmlIndex);
                cmlIndex++;
            }
            if (null != dataList && !dataList.isEmpty()) {
                setBodyClass(cmlIndexMap, dataList, sheet, row, styleMap);
            }

        }

    }

    /**
     * <p>
     * Description: ����sheet
     * </p>
     * 
     * @param title ����
     * @param workbook excel����
     * @param cmlMap ��
     * @param dataList ����
     * @param expTableList ��ͷ
     * @param topList ����
     * @throws Exception ҵ���쳣����
     */
    private static void setExcelSheet(String title, HSSFWorkbook workbook, LinkedHashMap<String, String> cmlMap,
            List<?> dataList, List<LinkedHashMap<String, String>> expTableList,
            List<LinkedHashMap<String, String>> topList) throws Exception {
        // ����һ�����
        HSSFSheet sheet = workbook.createSheet(title);
        HSSFCellStyle titleStyle = setTileStyle(workbook);
        HSSFCellStyle bodyStyle = setBodyStyle(workbook);
        Map<String, HSSFCellStyle> styleMap = getBodyStyleMap(workbook);
        styleMap.put("default", bodyStyle);

        // ������������
        HSSFRow row = sheet.createRow(0);
        // ���ñ��Ĭ���п��Ϊ20���ֽ�
        //sheet.setDefaultColumnWidth(NumberUtils.NUM_15);
        row.setHeightInPoints(20f);

        int cmlIndex = 0;
        LinkedHashMap<String, Integer> cmlIndexMap = new LinkedHashMap<String, Integer>();
        if (null != cmlMap && !cmlMap.isEmpty()) {
            int columnNum = cmlMap.size();
            int first = columnNum / 2;
            int second = columnNum;
            int s = 0;
            //�����ͷ
            if (null != expTableList && !expTableList.isEmpty()) {
                int count = expTableList.size();
                for (int i = 0; i < count; i++) {
                    if (first > 0) {
                        int fag = i % 2;
                        if (fag == 0) {
                            // ������������

                            if (i > 0) {
                                row = sheet.createRow(s);
                                row.setHeightInPoints(20f);
                            }
                            // ���ñ��Ĭ���п��Ϊ20���ֽ�
                            //sheet.setDefaultColumnWidth(NumberUtils.NUM_15);

                            sheet.addMergedRegion(new Region(s, (short) 0, s, (short) (first - 1)));
                            HSSFCell firstcell = row.createCell(0);
                            firstcell.setCellValue(expTableList.get(i).get("name") + expTableList.get(i).get("value"));
                            int j = i + 1;
                            if (j < count) {
                                sheet.addMergedRegion(new Region(s, (short) first, s, (short) (second - 1)));
                                HSSFCell secondcell = row.createCell(first);
                                secondcell.setCellValue(expTableList.get(j).get("name")
                                        + expTableList.get(j).get("value"));
                            }

                            s++;
                        }
                    } else {
                        if (i > 0) {
                            row = sheet.createRow(i);
                            row.setHeightInPoints(20f);
                        }
                        HSSFCell firstcell = row.createCell(0);
                        firstcell.setCellValue(expTableList.get(i).get("name") + expTableList.get(i).get("value"));
                    }

                }
            }

            //���˿�����ϸ��ͷ����
            if (null != topList && !topList.isEmpty()) {
                int topcount = topList.size();
                if (topcount <= columnNum) {
                    if (s > 0) {
                        row = sheet.createRow(s);
                        row.setHeightInPoints(20f);
                        sheet.autoSizeColumn(s);
                    }
                    for (int k = 0; k < columnNum; k++) {
                        HSSFCell topcell = row.createCell(k);
                        if (k < topcount) {
                            String textValue = topList.get(k).get("title") + topList.get(k).get("value");
                            String key = getFontColorByStr(topList.get(k).get("value"));
                            HSSFCellStyle topstyle = styleMap.get(key);
                            topcell.setCellStyle(topstyle);
                            topcell.setCellValue(textValue);
                        } else {
                            topcell.setCellStyle(bodyStyle);
                        }
                    }
                    s++;
                } else {
                    for (int k = 0; k < topcount; k++) {
                        HSSFCell topcell = row.createCell(k);
                        String textValue = topList.get(k).get("title") + topList.get(k).get("value");
                        String key = getFontColorByStr(topList.get(k).get("value"));
                        HSSFCellStyle topstyle = styleMap.get(key);
                        topcell.setCellStyle(topstyle);
                        topcell.setCellValue(textValue);
                    }
                }
            }

            if (s > 0) {
                row = sheet.createRow(s);
                row.setHeightInPoints(20f);
            }
            for (String cml : cmlMap.keySet()) {
                // ����Ӧ���
                sheet.autoSizeColumn(cmlIndex);
                HSSFCell cell = row.createCell(cmlIndex);
                cell.setCellStyle(titleStyle);
                HSSFRichTextString text = new HSSFRichTextString(cmlMap.get(cml));
                cell.setCellValue(text);
                cmlIndexMap.put(cml, cmlIndex);
                cmlIndex++;
            }
            if (null != dataList && !dataList.isEmpty()) {
                setBodyClass(cmlIndexMap, dataList, sheet, row, styleMap);
            }

        }

    }

    /**
     * <p>
     * Description: ����sheet
     * </p>
     * 
     * @param title ����
     * @param workbook excel����
     * @param cmlMap ��
     * @param dataList ����
     * @param expTableList ��ͷ
     * @throws Exception ҵ���쳣����
     */
    private static void setExcelSheetMap(String title, HSSFWorkbook workbook, LinkedHashMap<String, String> cmlMap,
            List<Map<String, Object>> dataList, List<LinkedHashMap<String, String>> expTableList) throws Exception {
        // ����һ�����
        HSSFSheet sheet = workbook.createSheet(title);

        HSSFCellStyle titleStyle = setTileStyle(workbook);
        HSSFCellStyle bodyStyle = setBodyStyle(workbook);

        Map<String, HSSFCellStyle> styleMap = getBodyStyleMap(workbook);
        styleMap.put("default", bodyStyle);

        // ������������
        HSSFRow row = sheet.createRow(0);
        row.setHeightInPoints(20f);
        // ���ñ��Ĭ���п��Ϊ20���ֽ�
        //sheet.setDefaultColumnWidth(20);
        int cmlIndex = 0;
        LinkedHashMap<String, Integer> cmlIndexMap = new LinkedHashMap<String, Integer>();
        if (null != cmlMap && !cmlMap.isEmpty()) {
            int columnNum = cmlMap.size();
            int first = columnNum / 2;
            int second = columnNum;
            int s = 0;
            if (null != expTableList && !expTableList.isEmpty()) {
                int count = expTableList.size();
                for (int i = 0; i < count; i++) {
                    if (first > 0) {
                        int fag = i % 2;
                        if (fag == 0) {
                            // ������������

                            if (i > 0) {
                                row = sheet.createRow(s);
                                row.setHeightInPoints(20f);
                            }
                            // ���ñ��Ĭ���п��Ϊ20���ֽ�
                            //sheet.setDefaultColumnWidth(NumberUtils.NUM_15);

                            sheet.addMergedRegion(new Region(s, (short) 0, s, (short) (first - 1)));
                            HSSFCell firstcell = row.createCell(0);
                            firstcell.setCellValue(expTableList.get(i).get("name") + expTableList.get(i).get("value"));
                            int j = i + 1;
                            sheet.addMergedRegion(new Region(s, (short) first, s, (short) (second - 1)));
                            HSSFCell secondcell = row.createCell(first);
                            if (j < count) {
                                secondcell.setCellValue(expTableList.get(j).get("name")
                                        + expTableList.get(j).get("value"));
                            }

                            s++;
                        }
                    } else {
                        if (i > 0) {
                            row = sheet.createRow(i);
                            row.setHeightInPoints(20f);
                        }
                        HSSFCell firstcell = row.createCell(0);
                        firstcell.setCellValue(expTableList.get(i).get("name") + expTableList.get(i).get("value"));
                    }

                }
            }
            if (s > 0) {
                row = sheet.createRow(s);
                row.setHeightInPoints(20f);
            }
            for (String cml : cmlMap.keySet()) {
                // ����Ӧ���
                // sheet.autoSizeColumn(cmlIndex);
                HSSFCell cell = row.createCell(cmlIndex);
                cell.setCellStyle(titleStyle);
                HSSFRichTextString text = new HSSFRichTextString(cmlMap.get(cml));
                cell.setCellValue(text);
                cmlIndexMap.put(cml, cmlIndex);
                cmlIndex++;
            }
        }
        if (null != dataList && !dataList.isEmpty()) {
            setBodyClassMap(cmlIndexMap, dataList, sheet, row, styleMap);
        }

    }

    /**
     * 
     * <p>
     * Description: ���䷽ʽ���ñ�������
     * </p>
     * 
     * @param cmlIndexMap ��
     * @param dataList ���ݼ���
     * @param sheet ������
     * @param row ��
     * @param styleMap ��ʽ
     * @throws Exception ҵ���쳣����
     */
    private static void setBodyClass(Map<String, Integer> cmlIndexMap, List<?> dataList, HSSFSheet sheet, HSSFRow row,
            Map<String, HSSFCellStyle> styleMap) throws Exception {
        // �����������ݣ�����������
        Iterator<?> it = dataList.iterator();
        int index = row.getRowNum();
        while (it.hasNext()) {
            sheet.autoSizeColumn(index);
            index++;
            row = sheet.createRow(index);
            row.setHeightInPoints(20f);
            Object ti = (Object) it.next();
            Field[] fields = ti.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                String fieldName = field.getName();
                //�ж����������Ҫ�����򲻴���
                if (null == cmlIndexMap.get(fieldName)) {
                    continue;
                }
                int cmlIndex = cmlIndexMap.get(fieldName);
                HSSFCell cell = row.createCell(cmlIndex);

                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                if ("getSerialVersionUID".equals(getMethodName)) {
                    continue;
                }

                Class<?> tCls = ti.getClass();
                Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
                Object value = getMethod.invoke(ti, new Object[] {});
                // �ж�ֵ�����ͺ����ǿ������ת��
                String textValue = null;
                if (value instanceof Integer) {
                    int intValue = (Integer) value;
                    textValue = String.valueOf(intValue);
                } else if (value instanceof Float) {
                    float fValue = (Float) value;
                    textValue = String.valueOf(fValue);
                } else if (value instanceof Double) {
                    double dValue = (Double) value;
                    textValue = String.valueOf(dValue);
                } else if (value instanceof Long) {
                    long longValue = (Long) value;
                    textValue = String.valueOf(longValue);
                } else if (value instanceof Boolean) {
                    boolean bValue = (Boolean) value;
                    textValue = "��";
                    if (!bValue) {
                        textValue = "��";
                    }
                } else if (value instanceof Date) {
                    Date date = (Date) value;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    textValue = sdf.format(date);
                } else if (value instanceof byte[]) {

                } else {
                    if (null != value) {
                        textValue = value.toString();
                    }
                }
                String key = getFontColorByStr(textValue);
                HSSFCellStyle bodyStyle = styleMap.get(key);
                if (null != bodyStyle) {
                    cell.setCellStyle(bodyStyle);
                } else {
                    cell.setCellStyle(styleMap.get("default"));
                }

                cell.setCellValue(textValue);
            }
        }
    }

    /**
     * 
     * <p>
     * Description: ���䷽ʽ���ñ�������
     * </p>
     * 
     * @param cmlIndexMap ��
     * @param dataList ���ݼ���
     * @param sh ������
     * @param row ��
     * @param styleMap ��ʽ
     * @throws Exception ҵ���쳣
     */
    private static void setBodyClassMap(Map<String, Integer> cmlIndexMap, List<Map<String, Object>> dataList,
            HSSFSheet sh, HSSFRow row, Map<String, HSSFCellStyle> styleMap) throws Exception {
        // �����������ݣ�����������
        Iterator<Map<String, Object>> it = dataList.iterator();
        int index = row.getRowNum();

        while (it.hasNext()) {
            sh.autoSizeColumn(index);
            Map<String, Object> t = it.next();
            index++;
            row = sh.createRow(index);
            row.setHeightInPoints(20f);
            if (null != cmlIndexMap && !cmlIndexMap.isEmpty()) {
                for (String cml : cmlIndexMap.keySet()) {
                    int cmlIndex = cmlIndexMap.get(cml);
                    HSSFCell cell = row.createCell(cmlIndex);
                    Object value = t.get(cml);
                    // �ж�ֵ�����ͺ����ǿ������ת��
                    String textValue = null;
                    if (value instanceof Integer) {
                        int intValue = (Integer) value;
                        textValue = String.valueOf(intValue);
                    } else if (value instanceof Float) {
                        float fValue = (Float) value;
                        textValue = String.valueOf(fValue);
                    } else if (value instanceof Double) {
                        double dValue = (Double) value;
                        textValue = String.valueOf(dValue);
                    } else if (value instanceof Long) {
                        long longValue = (Long) value;
                        textValue = String.valueOf(longValue);
                    } else if (value instanceof Boolean) {
                        boolean bValue = (Boolean) value;
                        textValue = "��";
                        if (!bValue) {
                            textValue = "��";
                        }
                    } else if (value instanceof Date) {
                        Date date = (Date) value;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        textValue = sdf.format(date);
                    } else if (value instanceof byte[]) {

                    } else {
                        if (null != value) {
                            textValue = value.toString();
                        }
                    }
                    String key = getFontColorByStr(textValue);
                    HSSFCellStyle bodyStyle = styleMap.get(key);
                    if (null != bodyStyle) {
                        cell.setCellStyle(bodyStyle);
                    } else {
                        cell.setCellStyle(styleMap.get("default"));
                    }
                    cell.setCellValue(textValue);
                }
            }
        }
    }
    
    
    public static void createCSV(String title, String[] headers, List<String[]> dataList,String path) throws BusinessException {
    	String fileName = "";
        //�����ļ���
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String excelpath = path; // + File.separator + sf.format(new Date()); //�ļ���
        fileName = title + ".csv"; // +"_"+DateUtils.format(new Date(), "yyyyMMddHHmmss") + ".csv"; //������Excel�ļ�������
        String xlsFile = excelpath + File.separator + fileName; //ȫ·��
        
        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            csvFile = new File(xlsFile);
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();

            // GB2312ʹ��ȷ��ȡ�ָ���","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GB2312"), 1024);
            
            //int num = headers.length / 2;
           // StringBuffer buffer = new StringBuffer();
           // for (int i = 0; i < num; i++) {
          //      buffer.append(" ,");
          //  }
            //csvWtriter.write(buffer.toString() + fileName + buffer.toString());
           // csvWtriter.newLine();

            // д���ļ�ͷ��
            writeRow(headers, csvWtriter);
            // д���ļ�����
            for (String[] row : dataList) {
            	writeRow(row, csvWtriter);
			}
            csvWtriter.flush();
        } catch (Exception e) {
        	ExceptionUtils.marsh(e);
        } finally {
            try {
                csvWtriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * дһ������
     * @param row �����б�
     * @param csvWriter
     * @throws IOException
     */
    private static void writeRow(String[] row, BufferedWriter csvWriter) throws IOException {
    	boolean isFirstCol = true;
        for (String data : row) {
        	StringBuffer sb = new StringBuffer();
        	if(isFirstCol){
        		isFirstCol = false;
        	}else{
        		sb.append(",");
        	}
            String rowStr = sb.append(data).toString();
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }   
    

    public static void deleteCSV(String filename,String path) throws BusinessException {
    	String fileName = filename + ".csv"; 
        String xlsFile = path + File.separator + fileName; //ȫ·��
        
        File csvFile = null;
        try {
            csvFile = new File(xlsFile);
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                return;
            }
            if(csvFile.exists()){
            	csvFile.delete();
            }

        } catch (Exception e) {
        	ExceptionUtils.marsh(e);
        }
    }
    
    
    public static void main(String[] args) {
		String[] tilleArry2 = new String[] { "Header/Detail", "Invoice No.",
				"Company", "Profit Center", "Invoice Date", "GL Date",
				"Customer No.", "Contract No.", "Tax Rate", "SO No.",
				"SO Type", "Quantity", "Item Name", "Gross Amount",
				"Tax Amount", "Taxable Amount", "Foreign Gross Amount",
				"Foreign Tax Amount", "Foreign Taxable Amount",
				"Exchange Rate", "Trans Currency", "Remarks",
				"Payment Terms", "Due Day" };    	
		List<String[]> recordList = new ArrayList<String[]>();
		String[] record = new String[24];
		for (int i = 0; i < 24; i++) {
			record[i] = "abc_"+i;
		}
		recordList.add(record);
		try{
			ExportExcel.createCSV("AR", tilleArry2, recordList, "E:\\abc");
		}catch (Exception e) {
        }
	}
}
