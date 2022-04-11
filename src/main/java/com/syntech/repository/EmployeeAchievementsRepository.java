package com.syntech.repository;

import com.syntech.model.EmployeeAchievements;

/**
 *
 * @author bipan
 */
public class EmployeeAchievementsRepository extends AbstractRepository<EmployeeAchievements> {
    
    @Override
    public void edit(EmployeeAchievements employeeAchievemnts) {
        
        super.findAll().stream().filter(x -> x.getId().equals(employeeAchievemnts.getId()))
                .forEach(eart -> {
                    eart.setEmployeeId(employeeAchievemnts.getEmployeeId());
                    eart.setCriteriaId(employeeAchievemnts.getCriteriaId());
                    eart.setAchievement(employeeAchievemnts.getAchievement());
                });
    }
}
