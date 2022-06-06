package com.syntech.repository;

import com.syntech.model.Months;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bipan
 */
@Stateless
public class MonthsRepository extends AbstractRepository<Months> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;

    public MonthsRepository() {
        super(Months.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
