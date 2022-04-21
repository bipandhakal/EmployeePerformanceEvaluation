package com.syntech.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author bipan
 */
public class Criteria implements IEntity {

    private Long id;
    private Long categoryId;
    private String name;
    private Double marks;
    private BigDecimal target;
    private CalculatedBy calculatedBy;

    public Criteria() {
    }

    public Criteria(Long id, Long categoryId, String name, Double marks, BigDecimal target, CalculatedBy calculatedBy) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.marks = marks;
        this.target = target;
        this.calculatedBy = calculatedBy;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMarks() {
        return marks;
    }

    public void setMarks(Double marks) {
        this.marks = marks;
    }

    public BigDecimal getTarget() {
        return target;
    }

    public void setTarget(BigDecimal target) {
        this.target = target;
    }

    public CalculatedBy getCalculatedBy() {
        return calculatedBy;
    }

    public void setCalculatedBy(CalculatedBy calculatedBy) {
        this.calculatedBy = calculatedBy;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.categoryId);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.marks);
        hash = 97 * hash + Objects.hashCode(this.target);
        hash = 97 * hash + Objects.hashCode(this.calculatedBy);
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
        final Criteria other = (Criteria) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.categoryId, other.categoryId)) {
            return false;
        }
        if (!Objects.equals(this.marks, other.marks)) {
            return false;
        }
        if (!Objects.equals(this.target, other.target)) {
            return false;
        }
        if (this.calculatedBy != other.calculatedBy) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Criteria{" + "id=" + id + ", categoryId=" + categoryId + ", name=" + name + ", marks=" + marks + ", target=" + target + ", calculatedBy=" + calculatedBy + '}';
    }

}
