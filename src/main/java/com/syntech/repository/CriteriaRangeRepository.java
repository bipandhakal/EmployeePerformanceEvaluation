package com.syntech.repository;

import com.syntech.model.CriteriaRange;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bipan
 */
@Stateless
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
}
