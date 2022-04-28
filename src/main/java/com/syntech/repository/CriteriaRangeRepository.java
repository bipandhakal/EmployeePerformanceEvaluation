package com.syntech.repository;

import com.syntech.model.CriteriaRange;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bipan
 */
public class CriteriaRangeRepository extends AbstractRepository<CriteriaRange> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;

    public CriteriaRangeRepository() {
        super(CriteriaRange.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void edit(CriteriaRange criteriaRange) {

        super.findAll().stream().filter(x -> x.getId().equals(criteriaRange.getId()))
                .forEach(crt -> {
                    crt.setCriteriaId(criteriaRange.getCriteriaId());
                    crt.setFrom(criteriaRange.getFrom());
                    crt.setTo(criteriaRange.getTo());
                    crt.setMarks(criteriaRange.getMarks());
                });
    }
}
