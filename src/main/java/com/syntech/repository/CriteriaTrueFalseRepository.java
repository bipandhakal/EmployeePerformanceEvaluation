package com.syntech.repository;

import com.syntech.model.CriteriaTrueFalse;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bipan
 */
public class CriteriaTrueFalseRepository extends AbstractRepository<CriteriaTrueFalse> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void edit(CriteriaTrueFalse criteriaTrueFalse) {

        super.findAll().stream().filter(x -> x.getId().equals(criteriaTrueFalse.getId()))
                .forEach(crtf -> {
                    crtf.setCriteriaId(criteriaTrueFalse.getCriteriaId());
                    crtf.setStatus(criteriaTrueFalse.getStatus());
                    crtf.setMarks(criteriaTrueFalse.getMarks());
                });
    }
}
