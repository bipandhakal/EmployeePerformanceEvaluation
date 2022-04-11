package com.syntech.model;

import java.util.Objects;

/**
 *
 * @author bipan
 */
public class CriteriaAchievement implements IEntity {

    private Long id;
    private String achievement;

    public CriteriaAchievement() {
    }

    public CriteriaAchievement(Long id, String achievement) {
        this.id = id;
        this.achievement = achievement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        final CriteriaAchievement other = (CriteriaAchievement) obj;
        if (!Objects.equals(this.achievement, other.achievement)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CriteriaAchievement{" + "id=" + id + ", achievement=" + achievement + '}';
    }

}
