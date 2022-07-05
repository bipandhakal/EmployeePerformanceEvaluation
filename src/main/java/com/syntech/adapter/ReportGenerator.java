package com.syntech.adapter;

import com.syntech.model.CalculatedBy;
import com.syntech.model.Criteria;
import com.syntech.model.CriteriaRange;
import com.syntech.model.CriteriaSelf;
import com.syntech.model.CriteriaTrueFalse;
import com.syntech.model.Employee;
import com.syntech.model.EmployeeAchievements;
import com.syntech.model.Months;
import com.syntech.model.Report;
import com.syntech.model.SupervisorEvaluation;
import com.syntech.repository.CriteriaRangeRepository;
import com.syntech.repository.CriteriaRepository;
import com.syntech.repository.CriteriaSelfRepository;
import com.syntech.repository.CriteriaTrueFalseRepository;
import com.syntech.repository.EmployeeAchievementsRepository;
import com.syntech.repository.ReportRepository;
import com.syntech.repository.SupervisorEvaluationRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author bipan
 */
@RequestScoped
public class ReportGenerator {

    private List<CriteriaRange> criteriaRangeList;

    private List<CriteriaTrueFalse> criteriaTrueFalseList;

    private List<CriteriaSelf> criteriaSelfList;

    @Inject
    private CriteriaRepository criteriaRepository;

    @Inject
    private CriteriaRangeRepository criteriaRangeRepository;

    @Inject
    private CriteriaTrueFalseRepository criteriaTrueFalseRepository;

    @Inject
    private CriteriaSelfRepository criteriaSelfRepository;

    @Inject
    private EmployeeAchievementsRepository employeeAchievementsRepository;

    @Inject
    private SupervisorEvaluationRepository supervisorEvaluationRepository;

    @Inject
    private ReportRepository reportRepository;

    @PostConstruct
    public void init() {
        //TODO fetching all records on postconstruct is not a good practice
        this.criteriaRangeList = criteriaRangeRepository.findAll();
        this.criteriaTrueFalseList = criteriaTrueFalseRepository.findAll();
        this.criteriaSelfList = criteriaSelfRepository.findAll();
    }

    public Double criteriaRangeMarks(Criteria criteria, String employeeAchievementMarks) {
        if (!criteria.getCalculatedBy().equals(CalculatedBy.RANGE)) {
            return 0.0;
        }
        Optional<CriteriaRange> optCr = criteriaRangeList.stream().filter(cr -> cr.getCriteria().equals(criteria))
                .filter(cr -> Long.parseLong(employeeAchievementMarks) >= cr.getFromRange()
                && Long.parseLong(employeeAchievementMarks) <= cr.getToRange())
                .findFirst();
        return optCr.isPresent() ? optCr.get().getMarks() : 0.0;
    }

    public Double criteriaTrueFalseMarks(Criteria criteria, String employeeAchievements) {
        if (!criteria.getCalculatedBy().equals(CalculatedBy.TRUEORFALSE)) {
            return 0.0;
        }

        Optional<CriteriaTrueFalse> optCr = criteriaTrueFalseList.stream().filter(cr -> cr.getCriteria().equals(criteria))
                .filter(cr -> cr.getStatus().equals(employeeAchievements))
                .findFirst();
        return optCr.isPresent() ? optCr.get().getMarks() : 0.0;

//        for (CriteriaTrueFalse ctf : criteriaTrueFalseList) {
//            if (ctf.getCriteria().equals(criteria)) {
//                if (employeeAchievements.equals(ctf.getStatus())) {
//                    return ctf.getMarks();
//                }
//            }
//        }
//        return 0.0;
    }

    // handle divide by zero exception while division using custom exception
    public Double criteriaAverageMarks(Criteria criteria, String employeeAchievements) {
        if (!criteria.getCalculatedBy().equals(CalculatedBy.AVERAGE)) {
            return 0.0;
        }
        try {
            Double d = Double.parseDouble(employeeAchievements);
            return (criteria.getMarks() * d) / (criteria.getTarget().doubleValue());
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public Double obtainedMarks(Criteria criteria, EmployeeAchievements ea) {
        Double d = 0.0;
        if (ea != null) {
            d = criteriaRangeMarks(criteria, ea.getAchievement())
                    + criteriaTrueFalseMarks(criteria, ea.getAchievement())
                    + criteriaAverageMarks(criteria, ea.getAchievement());
        }
        d = d + criteriaSelfMarks(criteria);
        return Double.parseDouble(String.format("%.2f", d));
    }

    public Double finalMarks(Double obtainedMarks, Double sevaluation) {
        return obtainedMarks + sevaluation;
    }

    public Double criteriaSelfMarks(Criteria criteria) {
        if (!criteria.getCalculatedBy().equals(CalculatedBy.SELF)) {
            return 0.0;
        }
        for (CriteriaSelf cs : criteriaSelfList) {
            if (cs.getCriteria().equals(criteria)) {
                return cs.getMarks();
            }
        }
        return 0.0;
    }

//    public EmployeeAchievements employeeAchievementsDetails(Criteria criteria, Employee selectedEmployee) {
//        List<EmployeeAchievements> employeeAchievementList = employeeAchievementsRepository.findBySelectedEmployee(selectedEmployee);
//        for (EmployeeAchievements ea : employeeAchievementList) {
//            if (ea.getCriteria().equals(criteria)) {
//                return ea;
//            }
//        }
//        return null;
//    }
    public EmployeeAchievements employeeAchievementsDetails(Criteria criteria, Employee selectedEmployee, Months selectedMonths) {
        List<EmployeeAchievements> employeeAchievementList = employeeAchievementsRepository.findByEmployeeNMonths(selectedEmployee, selectedMonths);
        for (EmployeeAchievements ea : employeeAchievementList) {
            if (ea.getCriteria().equals(criteria)) {
                return ea;
            }
        }
        return null;
    }

//    public double supervisorEvaluationMarks(Criteria criteria, Employee selectedEmployee) {
//        List<SupervisorEvaluation> supervisorEvaluationList = supervisorEvaluationRepository.findBySelectedEmployee(selectedEmployee);
//        for (SupervisorEvaluation se : supervisorEvaluationList) {
//            if (se.getCriteria().equals(criteria)) {
//                return se.getMarks();
//            }
//        }
//        return 0.0;
//    }
    public double supervisorEvaluationMarks(Criteria criteria, Employee selectedEmployee, Months selectedMonths) {
        List<SupervisorEvaluation> supervisorEvaluationList = supervisorEvaluationRepository.findByEmployeeNMonths(selectedEmployee, selectedMonths);
        for (SupervisorEvaluation se : supervisorEvaluationList) {
            if (se.getCriteria().equals(criteria)) {
                return se.getMarks();
            }
        }
        return 0.0;
    }

    public List<Report> prepareReport(Employee selectedEmployee, Months selectedMonths) {
        List<Report> r = reportRepository.findByEmployeeNMonths(selectedEmployee, selectedMonths);
        if (!r.isEmpty()) {
            return r;
        }
        List<Criteria> criteriaList = criteriaRepository.findAll();
        List<Report> reportList = new ArrayList<>();
        for (Criteria c : criteriaList) {

            EmployeeAchievements empachv = employeeAchievementsDetails(c, selectedEmployee, selectedMonths);
            Double sevalMarks = supervisorEvaluationMarks(c, selectedEmployee, selectedMonths);

            Double obtmarks = obtainedMarks(c, empachv);
            Double finalMarks = finalMarks(obtmarks, sevalMarks);

            Report report = new Report(null, selectedMonths, selectedEmployee, c.getCategory(), c, empachv == null ? null : empachv.getAchievement(), sevalMarks, obtmarks, finalMarks);
            reportList.add(report);
            System.out.println(c);
        }
        return reportList;
    }

    public List<Report> rePrepareMonthlyReport(Employee selectedEmployee, Months selectedMonths) {
        List<Report> rList = reportRepository.findByEmployeeNMonths(selectedEmployee, selectedMonths);

        List<Criteria> criteriaList = criteriaRepository.findAll();
        List<Report> reportList = new ArrayList<>();
        for (Criteria c : criteriaList) {

            EmployeeAchievements empachv = employeeAchievementsDetails(c, selectedEmployee, selectedMonths);
            Double sevalMarks = supervisorEvaluationMarks(c, selectedEmployee, selectedMonths);

            Double obtmarks = obtainedMarks(c, empachv);
            Double finalMarks = finalMarks(obtmarks, sevalMarks);

            Report oldreport = rList.stream().filter(r -> r.getCriteria().equals(c)).findFirst().orElse(null);
            Report report = new Report(oldreport == null ? null : oldreport.getId(), selectedMonths, selectedEmployee, c.getCategory(), c, empachv == null ? null : empachv.getAchievement(), sevalMarks, obtmarks, finalMarks);
            reportList.add(report);
        }
        return reportList;
    }

    public List<Report> prepareAnnualReport(Employee selectedEmployee) {
        return reportRepository.findByEmployee(selectedEmployee);
    }
}
