package org.andy.file.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ExcelUtil {

    private static final String EXCEL_XLS = "xls";

    private static final String EXCEL_XLSX = "xlsx";

    /**
     * 读入excel文件，解析后返回
     *
     * @param file
     * @throws IOException
     */
    public static List<String[]> readExcel(MultipartFile file) throws IOException {
        //检查文件
        checkFile(file);
        //获得Workbook工作薄对象
        Workbook workbook = getWorkBook(file);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<String[]> list = new ArrayList<>();
        if (workbook != null) {
            for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
                //获得当前sheet工作表
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if (sheet == null) {
                    continue;
                }
                //获得当前sheet的开始行
                int firstRowNum = sheet.getFirstRowNum();
                //获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                int nullRowCount = 0;
                //循环除了第一行的所有行
                for (int rowNum = firstRowNum + 2; rowNum <= lastRowNum; rowNum++) {
                    //获得当前行
                    Row row = sheet.getRow(rowNum);
                    //去除文档中的注解部分
                    if (row == null || nullRowCount >= 2) {
                        nullRowCount++;
                        continue;
                    }
                    //获得当前行的开始列
                    int firstCellNum = row.getFirstCellNum();
                    //获得当前行的列数
                    int lastCellNum = row.getLastCellNum();
                    String[] cells = new String[lastCellNum];
                    //循环当前行
                    for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                        Cell cell = row.getCell(cellNum);
                        cells[cellNum] = isCombineCell(getCombineCell(sheet), cell, sheet);
                    }
                    list.add(cells);
                }
            }
            workbook.close();
        }
        return list;
    }


    /**
     * 处理单元格的格式类型
     *
     * @param cell
     * @return
     */
    public static String getValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            cell.setCellType(CellType.STRING);
        }
        //判断数据的类型
        switch (cell.getCellTypeEnum()) {
            case NUMERIC: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case STRING: //字符串
                cellValue = cell.getStringCellValue();
                break;
            case BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA: //公式
                cellValue = cell.getCellFormula();
                break;
            case BLANK: //空值
                cellValue = "";
                break;
            case ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue.trim();
    }

    /**
     * 获得excel工作簿
     *
     * @param file
     * @return
     */
    public static Workbook getWorkBook(MultipartFile file) {
        //获得文件名
        String fileName = file.getOriginalFilename();
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //获取excel文件的io流
            InputStream is = file.getInputStream();
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if (fileName.endsWith(EXCEL_XLS)) {
                //2003
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith(EXCEL_XLSX)) {
                //2007
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        return workbook;
    }


    /**
     * 检查文件类型
     *
     * @param file
     * @throws IOException
     */

    public static void checkFile(MultipartFile file) throws IOException {
        //判断文件是否存在
        if (null == file) {
            log.error("文件不存在！");
            throw new FileNotFoundException("文件不存在！");
        }
        //获得文件名
        String fileName = file.getOriginalFilename();
        //判断文件是否是excel文件
        if (!fileName.endsWith(EXCEL_XLS) && !fileName.endsWith(EXCEL_XLSX)) {
            log.error(fileName + "不是excel文件");
            throw new IOException(fileName + "不是excel文件");
        }
    }


    /**
     * 合并单元格处理,获取合并行
     *
     * @param sheet
     * @return List<CellRangeAddress>
     */
    public static List<CellRangeAddress> getCombineCell(Sheet sheet) {
        List<CellRangeAddress> list = new ArrayList<>();
        //获得一个 sheet 中合并单元格的数量
        int sheetmergerCount = sheet.getNumMergedRegions();
        //遍历所有的合并单元格
        for (int i = 0; i < sheetmergerCount; i++) {
            //获得合并单元格保存进list中
            CellRangeAddress ca = sheet.getMergedRegion(i);
            list.add(ca);
        }
        return list;
    }


    /**
     * 判断单元格是否为合并单元格，是的话则将单元格的值返回
     *
     * @param listCombineCell 存放合并单元格的list
     * @param cell            需要判断的单元格
     * @param sheet           sheet
     * @return
     */
    public static String isCombineCell(List<CellRangeAddress> listCombineCell, Cell cell, Sheet sheet) {
        int firstC = 0;
        int lastC = 0;
        int firstR = 0;
        int lastR = 0;
        String cellValue = null;
        for (CellRangeAddress ca : listCombineCell) {
            //获得合并单元格的起始行, 结束行, 起始列, 结束列
            firstC = ca.getFirstColumn();
            lastC = ca.getLastColumn();
            firstR = ca.getFirstRow();
            lastR = ca.getLastRow();
            if (cell.getRowIndex() >= firstR && cell.getRowIndex() <= lastR
                    && cell.getColumnIndex() >= firstC && cell.getColumnIndex() <= lastC) {
                Row fRow = sheet.getRow(firstR);
                Cell fCell = fRow.getCell(firstC);
                cellValue = getValue(fCell);
                break;
            }
        }
        if (null == cellValue) {
            cellValue = getValue(cell);
        }
        return cellValue;
    }

    /**
     * 获取合并单元格的值
     *
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public static String getMergedRegionValue(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();

        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();

            if (row >= firstRow && row <= lastRow && column >= firstColumn && column <= lastColumn) {
                Row fRow = sheet.getRow(firstRow);
                Cell fCell = fRow.getCell(firstColumn);
                return getValue(fCell);
            }
        }

        return null;
    }


    /**
     * 判断指定的单元格是否是合并单元格
     *
     * @param sheet
     * @param row    行下标
     * @param column 列下标
     * @return
     */
    public static boolean isMergedRegion(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (row >= firstRow && row <= lastRow
                    && column >= firstColumn && column <= lastColumn) {
                return true;
            }
        }
        return false;
    }


    public static void responseReadInputStream(HttpServletResponse response, InputStream is) {
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Content-Disposition", "attachment;filename=" + System.currentTimeMillis() + ".xls");

        try (
                ServletOutputStream out = response.getOutputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                BufferedOutputStream bos = new BufferedOutputStream(out);

        ) {
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 创建excel文档
     *
     * @param list     数据
     * @param fileName
     * @param resp
     */
    public static void createWorkBook(List<String[]> list, String fileName, HttpServletResponse resp) {

        try (
                HSSFWorkbook wb = new HSSFWorkbook();
                OutputStream outputStream = resp.getOutputStream();
        ) {
            if (null != list && !list.isEmpty()) {
                //设置文件头编码格式
                resp.setHeader("Content-disposition", "attachment;filename="
                        + new String(fileName.getBytes("utf-8"), "utf-8"));
                //设置类型
                resp.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");
                //设置头
                resp.setHeader("Cache-Control", "no-cache");
                //设置日期头
                resp.setDateHeader("Expires", 0);
                // 创建excel工作簿
                CellStyle cellStyle = wb.createCellStyle();
                cellStyle.setDataFormat(wb.createDataFormat().getFormat("yyyy-MM-dd"));

                // 创建第一个sheet（页），并命名
                HSSFSheet sheet = wb.createSheet();

                // 创建两种单元格格式
                CellStyle cs = wb.createCellStyle();
                CellStyle cs2 = wb.createCellStyle();

                // 创建两种字体
                Font f = wb.createFont();
                Font f2 = wb.createFont();

                // 创建第一种字体样式（用于列名）
                f.setFontHeightInPoints((short) 10);
                f.setColor(IndexedColors.BLACK.getIndex());
                f.setBold(true);

                // 创建第二种字体样式（用于值）
                f2.setFontHeightInPoints((short) 10);
                f2.setColor(IndexedColors.BLACK.getIndex());

                // 设置第一种单元格的样式（用于列名）
                cs.setFont(f);
                cs.setBorderLeft(BorderStyle.THIN);
                cs.setBorderRight(BorderStyle.THIN);
                cs.setBorderTop(BorderStyle.THIN);
                cs.setBorderBottom(BorderStyle.THIN);
                cs.setAlignment(HorizontalAlignment.CENTER);

                // 设置第二种单元格的样式（用于值）
                cs2.setFont(f2);
                cs2.setBorderLeft(BorderStyle.THIN);
                cs2.setBorderRight(BorderStyle.THIN);
                cs2.setBorderTop(BorderStyle.THIN);
                cs2.setBorderBottom(BorderStyle.THIN);
                //设置列名
                // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
                Row row;
                for (int i = 0; i < list.size(); i++) {
                    //创建每一行
                    row = sheet.createRow(i);
                    for (int j = 0; j < list.get(i).length; j++) {
                        Cell cell = row.createCell(j);
                        if (i == 0) {
                            sheet.setColumnWidth(j, (short) (35.7 * 150));
                            cell.setCellStyle(cs);
                        } else {
                            cell.setCellStyle(cs2);
                        }
                        cell.setCellValue(list.get(i)[j]);
                    }
                }
                wb.write(outputStream);
            }
        } catch (IOException e) {
            log.error("导出文件失败! " + e.getMessage());
        }
    }

    /**
     * 生成Excel文件名
     *
     * @param prefix 比如：车辆激活VA  vehicleActive
     * @param suffix 后缀：默认是xlxs
     * @return
     */
    public static String generateExcelFileName(String prefix, String suffix) {
        StringBuilder fileName = new StringBuilder(prefix);
        fileName.append("-");
        fileName.append(System.currentTimeMillis());
        fileName.append(".");
        fileName.append(suffix);
        return fileName.toString();
    }

}
