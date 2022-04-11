package com.syntech.model;

import java.util.Objects;

/**
 *
 * @author bipan
 */
public class EmployeeAchievements implements IEntity {

    private Long id;
    private Long employeeId;
    private Long criteriaId;
    private String achievement;

    public EmployeeAchievements() {
    }

    public EmployeeAchievements(Long id, Long employeeId, Long criteriaId, String achievement) {
        this.id = id;
        this.employeeId = employeeId;
        this.criteriaId = criteriaId;
        this.achievement = achievement;
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

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.employeeId);
        hash = 79 * hash + Objects.hashCode(this.criteriaId);
        hash = 79 * hash + Objects.hashCode(this.achievement);
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
        final EmployeeAchievements other = (EmployeeAchievements) obj;
        if (!Objects.equals(this.achievement, other.achievement)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.employeeId, other.employeeId)) {
            return false;
        }
        if (!Objects.equals(this.criteriaId, other.criteriaId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EmployeeAchievements{" + "id=" + id + ", employeeId=" + employeeId + ", criteriaId=" + criteriaId + ", achievement=" + achievement + '}';
    }

}
