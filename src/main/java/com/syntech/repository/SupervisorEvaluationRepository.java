package com.syntech.repository;

import com.syntech.model.Criteria;
import com.syntech.model.Employee;
import com.syntech.model.SupervisorEvaluation;
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
public class SupervisorEvaluationRepository extends AbstractRepository<SupervisorEvaluation> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;

    public SupervisorEvaluationRepository() {
        super(SupervisorEvaluation.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
        public List<SupervisorEvaluation> findBySelectedEmployee(Employee employee) {
        List<SupervisorEvaluation> ea = null;
        try {
            Query query = em.createQuery("SELECT ea FROM SupervisorEvaluation ea WHERE ea.employee=:e", SupervisorEvaluation.class);
            query.setParameter("e", employee);
            ea = query.getResultList();
        } catch (NoResultException e) {
            ea = null;
        }
        return ea;
    }

    public SupervisorEvaluation findByEmployeeNCriteria(Employee employee, Criteria criteria) {
        SupervisorEvaluation se = null;
        try {
            Query query = em.createQuery("SELECT se FROM SupervisorEvaluation se WHERE se.criteria=:c AND se.employee=:e", SupervisorEvaluation.class);
            query.setParameter("c", criteria).setParameter("e", employee);
            se = (SupervisorEvaluation) query.getSingleResult();
        } catch (NoResultException e) {
            se = null;
        }
        return se;
    }
}
