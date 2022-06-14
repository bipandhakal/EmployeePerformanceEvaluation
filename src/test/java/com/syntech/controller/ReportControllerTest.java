package com.syntech.controller;

import com.syntech.model.CalculatedBy;
import com.syntech.model.Category;
import com.syntech.model.Criteria;
import com.syntech.model.Employee;
import com.syntech.model.Months;
import com.syntech.model.Report;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author bipan
 */
public class ReportControllerTest {

    ReportController reportController = new ReportController();

    @Test
    public void averageAnnualMarksTest() {
        Months months1 = new Months(1L, "January", 1);
        Months months2 = new Months(2L, "February", 2);
        Months months3 = new Months(3L, "March", 3);
        Months months4 = new Months(4L, "April", 4);
        Months months5 = new Months(5L, "May", 5);

        Employee employee1 = new Employee(1L, "Bipan", "Dhakal", new Date(2022 - 04 - 02), "9845676438", "bipan.dhakal@syntechnepal.com");
        Employee employee2 = new Employee(2L, "Santosh", "Timilsina", new Date(2022 - 04 - 02), "9845676438", "bipan.dhakal@syntechnepal.com");
        Employee employee3 = new Employee(3L, "Hrishi", "Sonar", new Date(2022 - 04 - 02), "9845676438", "bipan.dhakal@syntechnepal.com");
        Employee employee4 = new Employee(4L, "Madan", "Ghale", new Date(2022 - 04 - 02), "9845676438", "bipan.dhakal@syntechnepal.com");
        Employee employee5 = new Employee(5L, "Prakash", "Thapa", new Date(2022 - 04 - 02), "9845676438", "bipan.dhakal@syntechnepal.com");

        Category category1 = new Category(1L, "Task", 35d);
        Category category2 = new Category(2L, "Skills", 20d);
        Category category3 = new Category(3L, "Attendance", 20d);
        Category category4 = new Category(4L, "Behaviour", 15d);
        Category category5 = new Category(5L, "Task", 10d);

        Criteria criteria1 = new Criteria(1L, category1, "Number of completed task", 25d, BigDecimal.valueOf(20.0), CalculatedBy.RANGE);
        Criteria criteria2 = new Criteria(2L, category2, "Presentation skills", 15d, null, CalculatedBy.SELF);
        Criteria criteria3 = new Criteria(3L, category3, "Regular or Irregular", 15d, null, CalculatedBy.TRUEORFALSE);
        Criteria criteria4 = new Criteria(4L, category4, "Disciplined", 15d, null, CalculatedBy.SELF);
        Criteria criteria5 = new Criteria(5L, category5, "Number of completed task", 10d, BigDecimal.valueOf(30.0), CalculatedBy.AVERAGE);

        Report report1 = new Report(1L, months1, employee1, category1, criteria1, "15", 2d, 23d, 25d);
        Report report2 = new Report(1L, months2, employee2, category2, criteria2, "10", -2d, 10d, 8d);
        Report report3 = new Report(1L, months3, employee3, category3, criteria3, "Regular", -2d, 15d, 13d);
        Report report4 = new Report(1L, months4, employee4, category4, criteria4, "10", 2d, 10d, 12d);
        Report report5 = new Report(1L, months5, employee5, category5, criteria5, "15", 2d, 5d, 25d);

        List<Report> reportList1 = Arrays.asList(report1);
        List<Report> reportList2 = Arrays.asList(report2);
        List<Report> reportList3 = Arrays.asList(report3);
        List<Report> reportList4 = Arrays.asList(report4);
        List<Report> reportList5 = Arrays.asList(report5);

        reportController.setReportList(reportList1);
        reportController.setReportList(reportList2);
        reportController.setReportList(reportList3);
        reportController.setReportList(reportList4);
        reportController.setReportList(reportList5);

        assertEquals(25, reportController.calculateAverageAnnualMarks());
//        System.out.println(reportController.calculateAverageAnnualMarks());
    }
}
