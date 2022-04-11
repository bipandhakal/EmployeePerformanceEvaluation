package com.syntech.model;

import java.util.Objects;

/**
 *
 * @author bipan
 */
public class CriteriaTrueFalse implements IEntity {

    private Long id;
    private Long criteriaId;
    private String status;
    private Double marks;

    public CriteriaTrueFalse() {
    }

    public CriteriaTrueFalse(Long id, Long criteriaId, String status, Double marks) {
        this.id = id;
        this.criteriaId = criteriaId;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getMarks() {
        return marks;
    }

    public void setMarks(Double marks) {
        this.marks = marks;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.criteriaId);
        hash = 17 * hash + Objects.hashCode(this.status);
        hash = 17 * hash + Objects.hashCode(this.marks);
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
        final CriteriaTrueFalse other = (CriteriaTrueFalse) obj;
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
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
        return "CriteriaTrueFalse{" + "id=" + id + ", criteriaId=" + criteriaId + ", status=" + status + ", marks=" + marks + '}';
    }

}
