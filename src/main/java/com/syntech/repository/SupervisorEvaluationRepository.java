package com.syntech.repository;

import com.syntech.model.SupervisorEvaluation;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
