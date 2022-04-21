package com.syntech.repository;

import com.syntech.model.CriteriaSelf;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bipan
 */
public class CriteriaSelfRepository extends AbstractRepository<CriteriaSelf> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void edit(CriteriaSelf criteriaSelf) {

        super.findAll().stream().filter(x -> x.getId().equals(criteriaSelf.getId()))
                .forEach(crts -> {
                    crts.setCriteriaId(criteriaSelf.getCriteriaId());
                    crts.setMarks(criteriaSelf.getMarks());

                });
    }
}
