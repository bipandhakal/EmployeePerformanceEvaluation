package com.syntech.repository;

import com.syntech.model.Criteria;
import com.syntech.model.Employee;
import com.syntech.model.EmployeeAchievements;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    public List<EmployeeAchievements> findBySelectedEmployee(Employee employee) {
        List<EmployeeAchievements> ea = null;
        try {
            Query query = em.createQuery("SELECT ea FROM EmployeeAchievements ea WHERE ea.employee=:e", EmployeeAchievements.class);
            query.setParameter("e", employee);
            ea = query.getResultList();
        } catch (NoResultException e) {
            ea = null;
        }
        return ea;
    }

    public EmployeeAchievements findByEmployeeNCriteria(Employee employee, Criteria criteria) {
        EmployeeAchievements ea = null;
        try {
            Query query = em.createQuery("SELECT ea FROM EmployeeAchievements ea WHERE ea.criteria=:c AND ea.employee=:e", EmployeeAchievements.class);
            query.setParameter("c", criteria).setParameter("e", employee);
            ea = (EmployeeAchievements) query.getSingleResult();
        } catch (NoResultException e) {
            ea = null;
        }
        return ea;
    }

    public void deleteById(Long id) {
        em.remove(findById(id));
        em.flush();
    }
}
