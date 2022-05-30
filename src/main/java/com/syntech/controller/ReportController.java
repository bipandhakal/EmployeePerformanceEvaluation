package com.syntech.controller;

import com.syntech.adapter.ReportGenerator;
import com.syntech.model.Employee;
import com.syntech.model.Report;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author bipan
 */
@ViewScoped
@Named
public class ReportController implements Serializable {

    private Employee selectedEmployee;
    private List<Report> reportList;

    @Inject
    private ReportGenerator reportGenerator;

    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    @PostConstruct
    public void init() {
        this.selectedEmployee = new Employee();
        this.reportList = new ArrayList<>();
    }

    public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
    }

    public void generateReport() {
        reportList = reportGenerator.prepareReport(selectedEmployee);
    }

    public double calculateTotalMarks() {
        Double totalMarks = 0.0;
        for (Report r : reportList) {
            totalMarks = totalMarks + r.getFinalMarks();
        }
        return Double.parseDouble(String.format("%.2f", totalMarks));
    }
}
