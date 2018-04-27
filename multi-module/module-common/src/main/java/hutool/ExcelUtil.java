package hutool;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author: andy
 * @Date: 2018/4/24 16:33
 * @Description:
 */
public class ExcelUtil {
    /**
     * 多行表头
     * dataList：导出的数据；sheetName：表头名称； head0：表头第一行列名；headnum0：第一行合并单元格的参数
     * head1：表头第二行列名；headnum1：第二行合并单元格的参数；detail：导出的表体字段
     *
     */
    public void reportMergeXls(HttpServletRequest request,
                               HttpServletResponse response, List<Map<String, Object>> dataList,
                               String sheetName, String[] head0, String[] headnum0,
                               String[] head1, String[] headnum1, String[] detail , String date)
            throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(sheetName);// 创建一个表
        // 表头标题样式
        HSSFFont headfont = workbook.createFont();
        headfont.setFontName("宋体");
        headfont.setFontHeightInPoints((short) 22);// 字体大小
        HSSFCellStyle headstyle = workbook.createCellStyle();
        headstyle.setFont(headfont);
        headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        headstyle.setLocked(true);
        // 表头时间样式
        HSSFFont datefont = workbook.createFont();
        datefont.setFontName("宋体");
        datefont.setFontHeightInPoints((short) 12);// 字体大小
        HSSFCellStyle datestyle = workbook.createCellStyle();
        datestyle.setFont(datefont);
        datestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        datestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        datestyle.setLocked(true);
        // 列名样式
        HSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 12);// 字体大小
        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        style.setLocked(true);
        // 普通单元格样式（中文）
        HSSFFont font2 = workbook.createFont();
        font2.setFontName("宋体");
        font2.setFontHeightInPoints((short) 12);
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        style2.setFont(font2);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        style2.setWrapText(true); // 换行
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        // 设置列宽  （第几列，宽度）
        sheet.setColumnWidth( 0, 1600);
        sheet.setColumnWidth( 1, 3600);
        sheet.setColumnWidth( 2, 2800);
        sheet.setColumnWidth( 3, 2800);
        sheet.setColumnWidth( 4, 2800);
        sheet.setColumnWidth( 5, 2800);
        sheet.setColumnWidth( 6, 4500);
        sheet.setColumnWidth( 7, 3600);
        sheet.setDefaultRowHeight((short)360);//设置行高
        // 第一行表头标题
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, head0.length-1));
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short) 0x349);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(headstyle);
        CellUtil.setCellValue(cell, sheetName);
        // 第二行时间
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, head0.length-1));
        HSSFRow row1 = sheet.createRow(1);
        row.setHeight((short) 0x349);
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellStyle(datestyle);
        CellUtil.setCellValue(cell1, date);
        // 第三行表头列名
        row = sheet.createRow(2);
        for (int i = 0; i < 8; i++) {
            cell = row.createCell(i);
            cell.setCellValue(head0[i]);
            cell.setCellStyle(style);
        }
        //动态合并单元格
        for (int i = 0; i < headnum0.length; i++) {
            String[] temp = headnum0[i].split(",");
            Integer startrow = Integer.parseInt(temp[0]);
            Integer overrow = Integer.parseInt(temp[1]);
            Integer startcol = Integer.parseInt(temp[2]);
            Integer overcol = Integer.parseInt(temp[3]);
            sheet.addMergedRegion(new CellRangeAddress(startrow, overrow,
                    startcol, overcol));
        }
        //设置合并单元格的参数并初始化带边框的表头（这样做可以避免因为合并单元格后有的单元格的边框显示不出来）
        row = sheet.createRow(3);　　//因为下标从0开始，所以这里表示的是excel中的第四行
        for (int i = 0; i < head0.length; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(style);　　//设置excel中第四行的1、2、7、8列的边框
            if(i > 1 && i< 6) {
                for (int j = 0; j < head1.length; j++) {
                    cell = row.createCell(j + 2);
                    cell.setCellValue(head1[j]);　　//给excel中第四行的3、4、5、6列赋值（"温度℃", "湿度%", "温度℃", "湿度%"）
                    cell.setCellStyle(style);　　//设置excel中第四行的3、4、5、6列的边框
                }
            }
        }
        //动态合并单元格
        for (int i = 0; i < headnum1.length; i++) {
            String[] temp = headnum1[i].split(",");
            Integer startrow = Integer.parseInt(temp[0]);
            Integer overrow = Integer.parseInt(temp[1]);
            Integer startcol = Integer.parseInt(temp[2]);
            Integer overcol = Integer.parseInt(temp[3]);
            sheet.addMergedRegion(new CellRangeAddress(startrow, overrow,
                    startcol, overcol));
        }

        // 设置列值-内容
        for (int i = 0; i < dataList.size(); i++) {
            row = sheet.createRow(i + 4);//标题、时间、表头字段共占了4行，所以在填充数据的时候要加4，也就是数据要从第5行开始填充
            for (int j = 0; j < detail.length; j++) {
                Map tempmap = (HashMap) dataList.get(i);
                Object data = tempmap.get(detail[j]);
                cell = row.createCell(j);
                cell.setCellStyle(style2);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                CellUtil.setCellValue(cell, data);
            }
        }
        String fileName = new String(sheetName.getBytes("gb2312"), "ISO8859-1");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        response.setContentType("application/x-download;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename="
                + fileName + ".xls");
        OutputStream os = response.getOutputStream();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        byte[] b = new byte[1024];
        while ((bais.read(b)) > 0) {
            os.write(b);
        }
        bais.close();
        os.flush();
        os.close();
    }



    CellUtil类：

            package org.base.util;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;

    public class CellUtil {

        public static String returnCellValue(HSSFCell cell){
            String cellvalue = "";
            if (null != cell) {
                switch (cell.getCellType()) {
                    case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                        return String.valueOf(cell.getNumericCellValue()).trim();
                    case HSSFCell.CELL_TYPE_STRING: // 字符串
                        return String.valueOf(cell.getStringCellValue()).trim();
                    case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                        return String.valueOf(cell.getBooleanCellValue()).trim();
                    case HSSFCell.CELL_TYPE_FORMULA: // 公式
                        return String.valueOf(cell.getCellFormula()).trim();
                    case HSSFCell.CELL_TYPE_BLANK: // 空值
                        return "";
                    case HSSFCell.CELL_TYPE_ERROR: // 故障
                        return "";
                    default:
                        return "";
                }
            } else {

            }
            return cellvalue;
        }

        //避免cell.setCellValue(checkOrderQmSave.getSellOrderNo())中参数为空就会报错
        public static void setCellValue(HSSFCell cell, Object object){
            if(object == null){
                cell.setCellValue("");
            }else{
                if (object instanceof String) {
                    cell.setCellValue(String.valueOf(object));
                }else if(object instanceof Long){
                    Long temp = (Long)object;
                    String cellvalue = new DecimalFormat("#0.00").format(temp.doubleValue());
                    cell.setCellValue(cellvalue);
                }else if(object instanceof Double){
                    Double temp = (Double)object;
                    String cellvalue = new DecimalFormat("#0.00").format(temp.doubleValue());
                    cell.setCellValue(cellvalue);
                }else if(object instanceof Float){
                    Float temp = (Float)object;
                    String cellvalue = new DecimalFormat("#0.00").format(temp.doubleValue());
                    cell.setCellValue(cellvalue);
                }else if(object instanceof Integer){
                    Integer temp = (Integer)object;
                    cell.setCellValue(temp.intValue());
                }else if(object instanceof BigDecimal){
                    BigDecimal temp = (BigDecimal)object;
                    String cellvalue = new DecimalFormat("#0.00").format(temp.doubleValue());
                    cell.setCellValue(cellvalue);
                }else{
                    cell.setCellValue("");
                }
            }
        }
        public static void setCellValue(HSSFCell cell, Object object, String model){
            if(object == null){
                cell.setCellValue("");
            }else{
                if (object instanceof String) {
                    cell.setCellValue(String.valueOf(object));
                }else if(object instanceof Long){
                    Long temp = (Long)object;
                    String cellvalue = new DecimalFormat("#0.00").format(temp.doubleValue());
                    cell.setCellValue(cellvalue);
                }else if(object instanceof Double){
                    Double temp = (Double)object;
                    String cellvalue = new DecimalFormat("#0.00").format(temp.doubleValue());
                    cell.setCellValue(cellvalue);
                }else if(object instanceof Float){
                    Float temp = (Float)object;
                    String cellvalue = new DecimalFormat("#0.00").format(temp.doubleValue());
                    cell.setCellValue(cellvalue);
                }else if(object instanceof Integer){
                    Integer temp = (Integer)object;
                    cell.setCellValue(temp.intValue());
                }else if(object instanceof BigDecimal){
                    BigDecimal temp = (BigDecimal)object;
                    String cellvalue = new DecimalFormat("#0.00").format(temp.doubleValue());
                    cell.setCellValue(cellvalue);
                }else if(object instanceof java.sql.Date){
                    cell.setCellValue(new SimpleDateFormat(model).format(object));
                }else if(object instanceof java.util.Date){
                    cell.setCellValue(new SimpleDateFormat(model).format(object));
                }else{
                    cell.setCellValue("");
                }
            }
        }
        public static void setCellValue(HSSFCell cell, String object){
            if(object == null){
                cell.setCellValue("");
            }else{
                cell.setCellValue(object);
            }
        }
        public static void setCellValue(HSSFCell cell, Long object){
            if(object == null){
                cell.setCellValue("");
            }else{
                cell.setCellValue(object.doubleValue());
            }
        }
        public static void setCellValue(HSSFCell cell, Double object){
            if(object == null){
                cell.setCellValue("");
            }else{
                cell.setCellValue(object.doubleValue());
            }
        }
        public static void setCellValue(HSSFCell cell, double object){

            cell.setCellValue(object);

        }
        public static void setCellValue(HSSFCell cell, Date object, String model){
            if(object == null){
                cell.setCellValue("");
            }else{
                cell.setCellValue(new SimpleDateFormat(model).format(object));
            }
        }
        public static void setCellValue(HSSFCell cell, java.util.Date object, String model){
            if(object == null){
                cell.setCellValue("");
            }else{
                cell.setCellValue(new SimpleDateFormat(model).format(object));
            }
        }
        public static void setCellValue(HSSFCell cell, BigDecimal object){
            if(object == null){
                cell.setCellValue("");
            }else{
                cell.setCellValue(object.toString());
            }
        }

        //判断EXCEL表格高度用 row.setHeight((short) CellUtil.getExcelCellAutoHeight(TAR_VAL_ALL_STRING, 280, 30));
        public static float getExcelCellAutoHeight(String str,float defaultRowHeight, int fontCountInline) {
            int defaultCount = 0;
            for (int i = 0; i < str.length(); i++) {
                int ff = getregex(str.substring(i, i + 1));
                defaultCount = defaultCount + ff;
            }
            if (defaultCount > fontCountInline){
                return ((int) (defaultCount / fontCountInline) + 1) * defaultRowHeight;//计算
            } else {
                return defaultRowHeight;
            }
        }
        public static int getregex(String charStr) {
            if("".equals(charStr) || charStr == null){
                return 1;
            }
            // 判断是否为字母或字符
            if (Pattern.compile("^[A-Za-z0-9]+$").matcher(charStr).matches()) {
                return 1;
            }
            // 判断是否为全角
            if (Pattern.compile("[\u4e00-\u9fa5]+$").matcher(charStr).matches()) {
                return 2;
            }
            //全角符号 及中文
            if (Pattern.compile("[^x00-xff]").matcher(charStr).matches()) {
                return 2;
            }
            return 1;
        }
    }
}
