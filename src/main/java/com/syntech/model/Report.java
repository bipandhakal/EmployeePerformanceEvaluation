package com.syntech.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author bipan
 */
@Entity
@Table(name = "report")
public class Report implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "category", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "criteria", nullable = false)
    private Criteria criteria;

    @ManyToOne
    @JoinColumn(name = "employee_achievements", nullable = false)
    private EmployeeAchievements employeeAchievements;

    @ManyToOne
    @JoinColumn(name = "supervisor_evaluation", nullable = false)
    private SupervisorEvaluation supervisorEvaluation;

    @Column(name = "obtained_marks", nullable = false)
    private Double obtainedMarks;   

    public Report() {
    }

    public Report(Long id, Employee employee, Category category, Criteria criteria, EmployeeAchievements employeeAchievements, SupervisorEvaluation supervisorEvaluation, Double obtainedMarks) {
        this.id = id;
        this.employee = employee;
        this.category = category;
        this.criteria = criteria;
        this.employeeAchievements = employeeAchievements;
        this.supervisorEvaluation = supervisorEvaluation;
        this.obtainedMarks = obtainedMarks;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public EmployeeAchievements getEmployeeAchievements() {
        return employeeAchievements;
    }

    public void setEmployeeAchievements(EmployeeAchievements employeeAchievements) {
        this.employeeAchievements = employeeAchievements;
    }

    public SupervisorEvaluation getSupervisorEvaluation() {
        return supervisorEvaluation;
    }

    public void setSupervisorEvaluation(SupervisorEvaluation supervisorEvaluation) {
        this.supervisorEvaluation = supervisorEvaluation;
    }

    public Double getObtainedMarks() {
        return obtainedMarks;
    }

    public void setObtainedMarks(Double obtainedMarks) {
        this.obtainedMarks = obtainedMarks;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.employee);
        hash = 89 * hash + Objects.hashCode(this.category);
        hash = 89 * hash + Objects.hashCode(this.criteria);
        hash = 89 * hash + Objects.hashCode(this.employeeAchievements);
        hash = 89 * hash + Objects.hashCode(this.supervisorEvaluation);
        hash = 89 * hash + Objects.hashCode(this.obtainedMarks);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Report other = (Report) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.employee, other.employee)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (!Objects.equals(this.criteria, other.criteria)) {
            return false;
        }
        if (!Objects.equals(this.employeeAchievements, other.employeeAchievements)) {
            return false;
        }
        if (!Objects.equals(this.supervisorEvaluation, other.supervisorEvaluation)) {
            return false;
        }
        if (!Objects.equals(this.obtainedMarks, other.obtainedMarks)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Report{" + "id=" + id + ", employee=" + employee + ", category=" + category + ", criteria=" + criteria + ", employeeAchievements=" + employeeAchievements + ", supervisorEvaluation=" + supervisorEvaluation + ", obtainedMarks=" + obtainedMarks + '}';
    }
}
