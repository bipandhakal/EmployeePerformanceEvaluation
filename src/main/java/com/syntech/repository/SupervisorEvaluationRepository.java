package com.syntech.repository;

import com.syntech.model.SupervisorEvaluation;

/**
 *
 * @author bipan
 */
public class SupervisorEvaluationRepository extends AbstractRepository<SupervisorEvaluation> {

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
