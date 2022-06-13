package com.syntech.controller;

import com.syntech.model.CalculatedBy;
import com.syntech.model.Category;
import com.syntech.model.Criteria;
import com.syntech.model.Employee;
import com.syntech.model.EmployeeAchievements;
import com.syntech.model.Months;
import com.syntech.model.Report;
import com.syntech.model.SupervisorEvaluation;
import com.syntech.repository.ReportRepository;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.mockito.Mock;

/**
 *
 * @author bipan
 */
public class ReportControllerTest {

    @Inject
    ReportController reportController;

    @Test
    public void averageAnnualMarksTest() {
        Months months = new Months(1L, "January", 1);
        Employee employee = new Employee(1L, "Bipan", "Dhakal", new Date(2022 - 04 - 02), "9845676438", "bipan.dhakal@syntechnepal.com");
        Category category = new Category(1L, "Task", 35d);
        Criteria criteria = new Criteria(1L, category, "Number of completed task", 25d, BigDecimal.valueOf(20.0), CalculatedBy.RANGE);

        Report report = new Report(1L, months, employee, category, criteria, "15", 2d, 25d, 27d);
        List<Report> reportList = Arrays.asList(report);
        reportController.setReportList(reportList);
        System.out.println(reportController.calculateAverageAnnualMarks());
    }
}
