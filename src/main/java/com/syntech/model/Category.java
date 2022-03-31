package com.syntech.model;

import java.util.Objects;

/**
 *
 * @author bipan
 */
public class Category {

    private Long id;
    private String name;
    private Double totalMarks;

    public Category() {
    }

    public Category(Long id, String name, Double totalMarks) {
        this.id = id;
        this.name = name;
        this.totalMarks = totalMarks;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getTotalMarks() {
        return totalMarks;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotalMarks(Double totalMarks) {
        this.totalMarks = totalMarks;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.totalMarks);
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
        final Category other = (Category) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.totalMarks, other.totalMarks)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", Name=" + name + ", TotalMarks=" + totalMarks + '}';
    }
}
