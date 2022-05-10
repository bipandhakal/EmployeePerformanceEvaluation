package com.syntech.repository;

import com.syntech.model.CriteriaSelf;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bipan
 */
@Stateless
public class CriteriaSelfRepository extends AbstractRepository<CriteriaSelf> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;

    public CriteriaSelfRepository() {
        super(CriteriaSelf.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
