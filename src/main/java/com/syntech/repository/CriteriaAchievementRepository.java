package com.syntech.repository;

import com.syntech.model.CriteriaAchievement;

/**
 *
 * @author bipan
 */
public class CriteriaAchievementRepository extends AbstractRepository<CriteriaAchievement> {

    @Override
    public void edit(CriteriaAchievement criteriaAchievemnt) {

        super.findAll().stream().filter(x -> x.getId().equals(criteriaAchievemnt.getId()))
                .forEach(crt -> {
                    crt.setAchievement(criteriaAchievemnt.getAchievement());
                });
    }
}
