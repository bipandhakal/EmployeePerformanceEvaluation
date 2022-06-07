package com.syntech.controller;

import com.syntech.adapter.ReportGenerator;
import com.syntech.model.Employee;
import com.syntech.model.Months;
import com.syntech.model.Report;
import com.syntech.repository.ReportRepository;
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

    private Report report;
    private Employee selectedEmployee;
    private Months selectedMonths;
    private List<Report> reportList;

    @Inject
    private ReportGenerator reportGenerator;

    @Inject
    private ReportRepository reportRepository;

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public Months getSelectedMonths() {
        return selectedMonths;
    }

    public void setSelectedMonths(Months selectedMonths) {
        this.selectedMonths = selectedMonths;
    }

    @PostConstruct
    public void init() {
        this.report = new Report();
        this.selectedEmployee = new Employee();
        this.selectedMonths = new Months();
        this.reportList = new ArrayList<>();
    }

    public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
    }

    public void save() {
        reportList.forEach((r) -> {
            reportRepository.create(r);
        });
    }

    public void generateReport() {
        reportList = reportGenerator.prepareReport(selectedEmployee, selectedMonths);
    }

    public void generatAnnualReport() {
        reportList = reportGenerator.prepareAnnualReport(selectedEmployee);
    }

    public double calculateTotalMarks() {
        Double totalMarks = 0.0;
        for (Report r : reportList) {
            totalMarks = totalMarks + r.getFinalMarks();
        }
        return Double.parseDouble(String.format("%.2f", totalMarks));
    }

    public boolean isAlreadyGenerated() {
        return this.reportList == null || this.reportList.isEmpty() ? false : this.reportList.stream().allMatch(x -> x.getId() != null);
    }
}
