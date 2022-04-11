package com.syntech.model;

import java.util.Objects;

/**
 *
 * @author bipan
 */
public class CriteriaRange implements IEntity {

    private Long id;
    private Long criteriaId;
    private Long from;
    private Long to;
    private Double marks;

    public CriteriaRange() {
    }

    public CriteriaRange(Long id, Long criteriaId, Long from, Long to, Double marks) {
        this.id = id;
        this.criteriaId = criteriaId;
        this.from = from;
        this.to = to;
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

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
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
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.criteriaId);
        hash = 59 * hash + Objects.hashCode(this.from);
        hash = 59 * hash + Objects.hashCode(this.to);
        hash = 59 * hash + Objects.hashCode(this.marks);
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
        final CriteriaRange other = (CriteriaRange) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.criteriaId, other.criteriaId)) {
            return false;
        }
        if (!Objects.equals(this.from, other.from)) {
            return false;
        }
        if (!Objects.equals(this.to, other.to)) {
            return false;
        }
        if (!Objects.equals(this.marks, other.marks)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CriteriaRange{" + "id=" + id + ", criteriaId=" + criteriaId + ", from=" + from + ", to=" + to + ", marks=" + marks + '}';
    }

}
