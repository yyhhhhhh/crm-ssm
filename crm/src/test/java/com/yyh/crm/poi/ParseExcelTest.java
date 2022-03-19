package com.yyh.crm.poi;

import com.yyh.crm.commons.utils.HSSFUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author yyh
 * @date 2022-03-19 20:20
 * 使用apache-poi解析Excel文件
 */
public class ParseExcelTest {

    @Test
    public void testParseExcel() throws Exception{
        InputStream in = new FileInputStream("/Users/yyh/Documents/serverDir/abc.xls");
        HSSFWorkbook wb = new HSSFWorkbook(in);
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = null;
        HSSFCell cell = null;
        for(int i=0;i<=sheet.getLastRowNum();i++){ //最后一行
            row = sheet.getRow(i);
            String str = "";
            for(int j=0;j<row.getLastCellNum();j++){ //最后一列+1
                cell = row.getCell(j);
                CellType cellType = cell.getCellType();
                str += HSSFUtils.getCellValueForStr(cell,cellType)+" ";
            }
            System.out.println(str);
        }
    }
}
