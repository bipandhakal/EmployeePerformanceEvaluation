package com.syntech.repository;

import com.syntech.model.CriteriaTrueFalse;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bipan
 */
@Stateless
public class CriteriaTrueFalseRepository extends AbstractRepository<CriteriaTrueFalse> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;

    public CriteriaTrueFalseRepository() {
        super(CriteriaTrueFalse.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
