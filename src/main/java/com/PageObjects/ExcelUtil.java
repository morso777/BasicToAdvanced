package com.PageObjects;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import jxl.Cell;
import jxl.Sheet;
import org.apache.groovy.util.SystemUtil;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelUtil {



    //Method to Setup the Excel Output file where the reporting is done.
    public static String setupExcelOutput() {


        String exceloutputfolderpath = "./reports/excelreport/output/";
        String excelFileGeneratedName = new SimpleDateFormat("'Output_'yyyyMMdd_hhmmss'.xls'").format(new Date());
        String excelFileName = exceloutputfolderpath + excelFileGeneratedName;
        try {
            FileOutputStream fout = new FileOutputStream(excelFileName);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            HSSFWorkbook workBook = new HSSFWorkbook();

            workBook.write(outputStream);
            outputStream.writeTo(fout);
            outputStream.close();
            fout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return excelFileName;
    }


    //Method to write into the Output Excel file
    public static void writeExcelOutput(String testName, String fileName, LinkedList<LinkedHashMap<String, String>> results) {

        try {
            FileInputStream file = new FileInputStream(new File(fileName));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.createSheet(testName);
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            int rownum = 0;
            Row row = sheet.createRow(rownum++);

            //Create the header
            int cellnum = 0;
            row.createCell(cellnum++).setCellValue("TestCase Name");
            sheet.setColumnWidth(0,  8000);
            row.createCell(cellnum++).setCellValue("Step Number");
            row.createCell(cellnum++).setCellValue("Step Description");
            sheet.setColumnWidth(2,  12000);
            cellStyle.setBorderBottom(BorderStyle.THICK);
            row.createCell(cellnum++).setCellValue("Expected Result");
            sheet.setColumnWidth(3,  12000);
            row.createCell(cellnum++).setCellValue("Status");
            row.createCell(cellnum++).setCellValue("Browser");
            row.createCell(cellnum++).setCellValue("Exception (if any)");

            //Fill the rows
            for(LinkedHashMap<String, String> result : results) {
                row = sheet.createRow(rownum++);
                cellnum = 0;
                row.createCell(cellnum++).setCellValue(result.get("TestCase Name"));
                row.createCell(cellnum++).setCellValue(result.get("Step Number"));
                row.createCell(cellnum++).setCellValue(result.get("Step Description"));
                row.createCell(cellnum++).setCellValue(result.get("Expected Result"));
                row.createCell(cellnum++).setCellValue(result.get("Status"));
                row.createCell(cellnum++).setCellValue(result.get("Browser"));
                row.createCell(cellnum++).setCellValue(result.get("Exception (if any)"));
            }

            file.close();

            FileOutputStream outFile =new FileOutputStream(new File(fileName));
            workbook.write(outFile);
            outFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
