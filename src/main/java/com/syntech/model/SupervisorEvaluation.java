package com.syntech.model;

import java.util.Objects;

/**
 *
 * @author bipan
 */
public class SupervisorEvaluation implements IEntity {

    private Long id;
    private Long employeeId;
    private Long criteriaId;
    private Double marks;

    public SupervisorEvaluation() {
    }

    public SupervisorEvaluation(Long id, Long employeeId, Long criteriaId, Double marks) {
        this.id = id;
        this.employeeId = employeeId;
        this.criteriaId = criteriaId;
        this.marks = marks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getCriteriaId() {
        return criteriaId;
    }

    public void setCriteriaId(Long criteriaId) {
        this.criteriaId = criteriaId;
    }

    public Double getMarks() {
        return marks;
    }

    public void setMarks(Double marks) {
        this.marks = marks;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.employeeId);
        hash = 29 * hash + Objects.hashCode(this.criteriaId);
        hash = 29 * hash + Objects.hashCode(this.marks);
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
        final SupervisorEvaluation other = (SupervisorEvaluation) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.employeeId, other.employeeId)) {
            return false;
        }
        if (!Objects.equals(this.criteriaId, other.criteriaId)) {
            return false;
        }
        if (!Objects.equals(this.marks, other.marks)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SupervisorEvaluation{" + "id=" + id + ", employeeId=" + employeeId + ", criteriaId=" + criteriaId + ", marks=" + marks + '}';
    }

}
