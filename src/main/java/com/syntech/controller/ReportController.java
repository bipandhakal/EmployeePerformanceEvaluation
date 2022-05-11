package com.syntech.controller;

import com.syntech.model.CriteriaRange;
import com.syntech.model.Employee;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author bipan
 */
@ViewScoped
@Named
public class ReportController implements Serializable {

    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @PostConstruct
    public void init() {
        this.employee = new Employee();
    }

    public Double criteriaRangeCalculation(List<CriteriaRange> criteriaRangeList, Long achievement) {
        for (CriteriaRange cr : criteriaRangeList) {
            if (achievement.compareTo(cr.getFromRange()) >= 0 && achievement.compareTo(cr.getToRange()) <= 0) {
                return cr.getMarks();
            }
        }
        return 0.0;
    }
}
