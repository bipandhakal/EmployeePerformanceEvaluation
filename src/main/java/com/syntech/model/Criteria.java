package com.syntech.model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "criteria")
public class Criteria implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category", nullable = false)
    private Category category;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "marks", nullable = false)
    private Double marks;

    @Column(name = "target", nullable = false)
    private BigDecimal target;

    @Enumerated(EnumType.STRING)
    @Column(name = "calculated_by", nullable = false)
    private CalculatedBy calculatedBy;

    public Criteria() {
    }

    public Criteria(Long id, Category category, String name, Double marks, BigDecimal target, CalculatedBy calculatedBy) {
        this.id = id;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
    public final int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.category);
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.marks);
        hash = 79 * hash + Objects.hashCode(this.target);
        hash = 79 * hash + Objects.hashCode(this.calculatedBy);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Criteria)) {
            return false;
        }
        final Criteria other = (Criteria) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }

        if (!Objects.equals(this.category, other.category)) {
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
        return "Criteria{" + "id=" + id + ", category=" + category + ", name=" + name + ", marks=" + marks + ", target=" + target + ", calculatedBy=" + calculatedBy + '}';
    }
}
