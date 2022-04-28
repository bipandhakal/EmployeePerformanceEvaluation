package com.syntech.repository;

import com.syntech.model.EmployeeAchievements;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bipan
 */
public class EmployeeAchievementsRepository extends AbstractRepository<EmployeeAchievements> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;

    public EmployeeAchievementsRepository() {
        super(EmployeeAchievements.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

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
