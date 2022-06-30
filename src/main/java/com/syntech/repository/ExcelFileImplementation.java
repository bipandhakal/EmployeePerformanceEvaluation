package com.syntech.repository;

import com.syntech.model.Criteria;
import com.syntech.model.Employee;
import com.syntech.model.Months;
import com.syntech.model.SupervisorEvaluation;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author bipan
 */
public class ExcelFileImplementation implements Serializable {

    @Inject
    MonthsRepository monthsRepository;

    @Inject
    EmployeeRepository employeeRepository;

    @Inject
    CriteriaRepository criteriaRepository;

    public List<SupervisorEvaluation> readExcelFile(String excelFilePath) throws EncryptedDocumentException, IOException {
        List<SupervisorEvaluation> supervisorEvaluations = new ArrayList<>();
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
            if (row.getRowNum() == 0) {
                continue;
            }
            SupervisorEvaluation supervisorEvaluation = new SupervisorEvaluation();

            String monthName = row.getCell(0).toString();
            Months months = monthsRepository.findByName(monthName);
            supervisorEvaluation.setMonths(months);

            Long employeeId = Long.valueOf((int) row.getCell(1).getNumericCellValue());
            Employee employee = employeeRepository.findById(employeeId);
            supervisorEvaluation.setEmployee(employee);

            Long criteriaId = Long.valueOf((int) row.getCell(3).getNumericCellValue());
            Criteria criteria = criteriaRepository.findById(criteriaId);
            supervisorEvaluation.setCriteria(criteria);

            supervisorEvaluation.setMarks(row.getCell(5).getNumericCellValue());

            System.out.println(supervisorEvaluation.getEmployee().getFirstName());
            System.out.println(supervisorEvaluation.getCriteria().getName());
            System.out.println(supervisorEvaluation.getMarks());
            supervisorEvaluations.add(supervisorEvaluation);
            System.out.println("");
        }
        fileInputStream.close();
        return supervisorEvaluations;
    }

}
