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
import javax.validation.constraints.NotNull;

/**
 *
 * @author bipan
 */
@Entity
@Table(name = "employee_achievements")
public class EmployeeAchievements implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "criteria", nullable = false)
    private Criteria criteria;

    @NotNull(message = "Employee Achievements should not be null")
    @Column(name = "achievement", nullable = false)
    private String achievement;

    public EmployeeAchievements() {
    }

    public EmployeeAchievements(Long id, Employee employee, Criteria criteria, String achievement) {
        this.id = id;
        this.employee = employee;
        this.criteria = criteria;
        this.achievement = achievement;
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

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
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
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.employee);
        hash = 71 * hash + Objects.hashCode(this.criteria);
        hash = 71 * hash + Objects.hashCode(this.achievement);
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
        if (!Objects.equals(this.employee, other.employee)) {
            return false;
        }
        if (!Objects.equals(this.criteria, other.criteria)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EmployeeAchievements{" + "id=" + id + ", employee=" + employee + ", criteria=" + criteria + ", achievement=" + achievement + '}';
    }
}
