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

    @Column(name = "employee_achievements", nullable = false)
    private String employeeAchievements;

    @Column(name = "supervisor_evaluation", nullable = false)
    private Double supervisorEvaluation;

    @Column(name = "obtained_marks", nullable = false)
    private Double obtainedMarks;

    @Column(name = "final_marks", nullable = false)
    private Double finalMarks;

    public Report() {
    }

    public Report(Long id, Employee employee, Category category, Criteria criteria, String employeeAchievements, Double supervisorEvaluation, Double obtainedMarks, Double finalMarks) {
        this.id = id;
        this.employee = employee;
        this.category = category;
        this.criteria = criteria;
        this.employeeAchievements = employeeAchievements;
        this.supervisorEvaluation = supervisorEvaluation;
        this.obtainedMarks = obtainedMarks;
        this.finalMarks = finalMarks;
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

    public String getEmployeeAchievements() {
        return employeeAchievements;
    }

    public void setEmployeeAchievements(String employeeAchievements) {
        this.employeeAchievements = employeeAchievements;
    }

    public Double getSupervisorEvaluation() {
        return supervisorEvaluation;
    }

    public void setSupervisorEvaluation(Double supervisorEvaluation) {
        this.supervisorEvaluation = supervisorEvaluation;
    }

    public Double getObtainedMarks() {
        return obtainedMarks;
    }

    public void setObtainedMarks(Double obtainedMarks) {
        this.obtainedMarks = obtainedMarks;
    }

    public Double getFinalMarks() {
        return finalMarks;
    }

    public void setFinalMarks(Double finalMarks) {
        this.finalMarks = finalMarks;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.employee);
        hash = 73 * hash + Objects.hashCode(this.category);
        hash = 73 * hash + Objects.hashCode(this.criteria);
        hash = 73 * hash + Objects.hashCode(this.employeeAchievements);
        hash = 73 * hash + Objects.hashCode(this.supervisorEvaluation);
        hash = 73 * hash + Objects.hashCode(this.obtainedMarks);
        hash = 73 * hash + Objects.hashCode(this.finalMarks);
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
        if (!Objects.equals(this.employeeAchievements, other.employeeAchievements)) {
            return false;
        }
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
        if (!Objects.equals(this.supervisorEvaluation, other.supervisorEvaluation)) {
            return false;
        }
        if (!Objects.equals(this.obtainedMarks, other.obtainedMarks)) {
            return false;
        }
        if (!Objects.equals(this.finalMarks, other.finalMarks)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Report{" + "id=" + id + ", employee=" + employee + ", category=" + category + ", criteria=" + criteria + ", employeeAchievements=" + employeeAchievements + ", supervisorEvaluation=" + supervisorEvaluation + ", obtainedMarks=" + obtainedMarks + ", finalMarks=" + finalMarks + '}';
    }
}
