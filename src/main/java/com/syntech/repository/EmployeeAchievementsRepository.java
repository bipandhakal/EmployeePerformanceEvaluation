package com.syntech.repository;

import com.syntech.model.EmployeeAchievements;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bipan
 */
@Stateless
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
}
