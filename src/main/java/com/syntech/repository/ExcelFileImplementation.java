package com.syntech.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author bipan
 */
public class ExcelFileImplementation implements Serializable {

    public void readExcelFile(String excelFilePath) throws EncryptedDocumentException, IOException {

        FileInputStream fileInputStream = new FileInputStream(new File(excelFilePath));

        //Get the workbook instance for XLS file 
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

        //Get first sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);

        //Iterate through each rows from first sheet
        Iterator<Row> rowIterator = sheet.iterator();
        System.out.println("file read success");

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            //For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                if (cell.getCellType().equals(CellType.NUMERIC)) {
                    System.out.println(cell.getNumericCellValue());
                }
                if (cell.getCellType().equals(CellType.STRING)) {
                    System.out.println(cell.getStringCellValue());
                }
            }
            System.out.println("");
        }
        fileInputStream.close();
    }

}
