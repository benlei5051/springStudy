package hutool;

import cn.hutool.core.lang.Console;
import cn.hutool.poi.excel.CellUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.RowUtil;
import cn.hutool.poi.excel.sax.handler.RowHandler;
import javafx.scene.control.Cell;
import org.apache.poi.ss.util.SheetUtil;

import java.util.List;

/**
 * @author: andy
 * @Date: 2018/4/23 11:48
 * @Description:
 */
public class Test7 {
    public static void main(String[] args){
      /*  ExcelReader reader = ExcelUtil.getReader("d:/ABS_DTC_List.xlsx");
        int size = reader.getSheets().size();
        reader.getSheetNames().forEach(System.out::println);
        System.out.println(size);
        List<List<Object>> read = reader.read(3);*/
        System.out.println("--------------------");
        ExcelUtil.readBySax("d:/ABS_DTC_List.xlsx", -1, createRowHandler());


    }

    private static RowHandler createRowHandler() {
        return new RowHandler() {
            @Override
            public void handle(int sheetIndex, int rowIndex, List<Object> rowlist) {
                SheetUtil.getCellWithMerges()
                if (rowlist.size() >= 16 && rowIndex >= 2) {
                    Console.log("[{}] [{}] {}", sheetIndex, rowIndex, rowlist);
                }
            }
        };
    }
}
