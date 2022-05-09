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
@Table(name = "criteria_range")
public class CriteriaRange implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "criteria", nullable = false)
    private Criteria criteria;

    @Column(name = "from_range", nullable = false)
    private Long fromRange;

    @Column(name = "to_range", nullable = false)
    private Long toRange;

    @Column(name = "marks", nullable = false)
    private Double marks;

    public CriteriaRange() {
    }

    public CriteriaRange(Long id, Criteria criteria, Long fromRange, Long toRange, Double marks) {
        this.id = id;
        this.criteria = criteria;
        this.fromRange = fromRange;
        this.toRange = toRange;
        this.marks = marks;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public Long getFromRange() {
        return fromRange;
    }

    public void setFromRange(Long fromRange) {
        this.fromRange = fromRange;
    }

    public Long getToRange() {
        return toRange;
    }

    public void setToRange(Long toRange) {
        this.toRange = toRange;
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
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.criteria);
        hash = 29 * hash + Objects.hashCode(this.fromRange);
        hash = 29 * hash + Objects.hashCode(this.toRange);
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
        final CriteriaRange other = (CriteriaRange) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.criteria, other.criteria)) {
            return false;
        }
        if (!Objects.equals(this.fromRange, other.fromRange)) {
            return false;
        }
        if (!Objects.equals(this.toRange, other.toRange)) {
            return false;
        }
        if (!Objects.equals(this.marks, other.marks)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CriteriaRange{" + "id=" + id + ", criteria=" + criteria + ", fromRange=" + fromRange + ", toRange=" + toRange + ", marks=" + marks + '}';
    }
}
