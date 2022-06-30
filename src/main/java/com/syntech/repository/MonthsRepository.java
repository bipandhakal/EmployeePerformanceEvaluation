package com.syntech.repository;

import com.syntech.model.Months;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    public Months findByName(String name) {
        Query query = em.createQuery("Select a from Months a where a.name = :u", Months.class);
        query.setParameter("u", name);
        Months months = (Months) query.getSingleResult();
        return months;
    }
}
