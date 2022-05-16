package com.syntech.controller;

import com.syntech.model.Criteria;
import com.syntech.model.CriteriaRange;
import com.syntech.model.CriteriaSelf;
import com.syntech.model.CriteriaTrueFalse;
import com.syntech.model.Employee;
import com.syntech.model.EmployeeAchievements;
import com.syntech.model.Report;
import com.syntech.model.SupervisorEvaluation;
import com.syntech.repository.CriteriaRangeRepository;
import com.syntech.repository.CriteriaRepository;
import com.syntech.repository.CriteriaTrueFalseRepository;
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

    private Employee selectedEmployee;

    private Report report;

    private List<Report> reportList;

//    private List<Criteria> criteriaList;
    private List<CriteriaRange> criteriaRangeList;

    private List<CriteriaTrueFalse> criteriaTrueFalseList;

    @Inject
    private ReportRepository reportRepository;

    @Inject
    private CriteriaRangeRepository criteriaRangeRepository;

    @Inject
    private CriteriaRepository criteriaRepository;

    @Inject
    private CriteriaTrueFalseRepository criteriaTrueFalseRepository;

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
    }

    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public List<CriteriaRange> getCriteriaRangeList() {
        return criteriaRangeList;
    }

    public void setCriteriaRangeList(List<CriteriaRange> criteriaRangeList) {
        this.criteriaRangeList = criteriaRangeList;
    }

    @PostConstruct
    public void init() {
        this.report = new Report();
        this.criteriaRangeList = criteriaRangeRepository.findAll();
        this.criteriaTrueFalseList = criteriaTrueFalseRepository.findAll();
        this.selectedEmployee = new Employee();
    }

    public Double criteriaRangeMarks(Criteria criteria, String employeeAchievementMarks) {
        for (CriteriaRange cr : criteriaRangeList) {
            if (cr.getCriteria().equals(criteria)) {
                Long achv = Long.parseLong(employeeAchievementMarks);
                if (achv >= cr.getFromRange() && achv <= cr.getToRange()) {
                    return cr.getMarks();
                }
            }
        }
        return 0.0;
    }

    public Double criteriaTrueFalseMarks(Criteria criteria, String employeeAchievements) {
        for (CriteriaTrueFalse ctf : criteriaTrueFalseList) {
            if (ctf.getCriteria().equals(criteria)) {
                if (employeeAchievements.equals(ctf.getStatus())) {
                    return ctf.getMarks();
                }
            }
        }
        return 0.0;
    }

    public Double criteriaAverageMarks(Criteria criteria, String employeeAchievements) {
        Double d = Double.parseDouble(employeeAchievements);
        return (criteria.getMarks() * d) / (criteria.getTarget().doubleValue());
    }

    public Double obtainedMarks(Criteria criteria, String employeeAchievementMarks, CriteriaSelf criteriaSelfMarks) {
        return criteriaRangeMarks(criteria, employeeAchievementMarks)
                + criteriaTrueFalseMarks(criteria, employeeAchievementMarks)
                + criteriaAverageMarks(criteria, employeeAchievementMarks)
                + criteriaSelfMarks.getMarks();
    }

    public Double finalMarks(Criteria criteria, String employeeAchievementsMarks, CriteriaSelf criteriaSelfMarks, SupervisorEvaluation sevaluation) {
        return obtainedMarks(criteria, employeeAchievementsMarks, criteriaSelfMarks) + sevaluation.getMarks();
    }

    public void prepareReport() {
        List<Criteria> criteriaList = criteriaRepository.findAll();
        List<EmployeeAchievements> employeeAchievementList = new ArrayList<>(); // find by selected employee
        List<SupervisorEvaluation> supervisorEvaluationList = new ArrayList<>(); // find by selected employee

        reportList = new ArrayList<>();
        for (Criteria c : criteriaList) {
            EmployeeAchievements ea = null; // find by employee and criteria
            SupervisorEvaluation se = null; // find by employee and criteria

            Double om = obtainedMarks(selectedEmployee, c, ea);// find by employee and criteria

            Report report = new Report(null, selectedEmployee, c.getCategory(), c, ea, se, om);
            reportList.add(report);
            System.out.println(c);
        }
    }
}
