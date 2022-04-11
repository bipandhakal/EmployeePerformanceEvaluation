package com.syntech.model;

import java.util.Objects;

/**
 *
 * @author bipan
 */
public class CriteriaSelf implements IEntity {

    private Long id;
    private Long criteriaId;
    private Double marks;

    public CriteriaSelf() {
    }

    public CriteriaSelf(Long id, Long criteriaId, Double marks) {
        this.id = id;
        this.criteriaId = criteriaId;
        this.marks = marks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.criteriaId);
        hash = 23 * hash + Objects.hashCode(this.marks);
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
        final CriteriaSelf other = (CriteriaSelf) obj;
        if (!Objects.equals(this.id, other.id)) {
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
        return "CriteriaSelf{" + "id=" + id + ", criteriaId=" + criteriaId + ", marks=" + marks + '}';
    }

}
