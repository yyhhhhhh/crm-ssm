package com.yyh.crm.poi;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author yyh
 * @date 2022-03-18 20:11
 * 使用apache-poi生成Excel文件
 */
public class CreateExcelTest {

    @Test
    public void testMethod(){
        HSSFWorkbook wb = new HSSFWorkbook();//对应一个excel文件
        HSSFSheet sheet = wb.createSheet("学生列表");
        HSSFRow row = sheet.createRow(0);//从0行开始
        HSSFCell cell = row.createCell(0);//从0列开始
        cell.setCellValue("学号");
        cell = row.createCell(1);
        cell.setCellValue("姓名");
        cell = row.createCell(2);
        cell.setCellValue("年龄");

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        for(int i = 1;i<=10;i++){
            row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(100 + i);
            cell = row.createCell(1);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Name"+i);
            cell = row.createCell(2);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(20 + i);
        }
        OutputStream os = null;
        try {
            os = new FileOutputStream("/Users/yyh/Documents/serverDir/studentList.xls");
            wb.write(os);
            os.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
