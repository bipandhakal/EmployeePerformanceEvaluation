package com.syntech.repository;

import com.syntech.model.SupervisorEvaluation;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bipan
 */
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

    @Override
    public void edit(SupervisorEvaluation supervisorEvaluation) {

        super.findAll().stream().filter(x -> x.getId().equals(supervisorEvaluation.getId()))
                .forEach(seval -> {
                    seval.setEmployeeId(supervisorEvaluation.getEmployeeId());
                    seval.setCriteriaId(supervisorEvaluation.getCriteriaId());
                    seval.setMarks(supervisorEvaluation.getMarks());
                });
    }
}
