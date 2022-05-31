package com.syntech.repository;

import com.syntech.model.Criteria;
import com.syntech.model.CriteriaRange;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    public List<CriteriaRange> findByCriteria(Criteria criteria) {
        List<CriteriaRange> cr = null;
        try {
            Query query = em.createQuery("SELECT cr FROM CriteriaRange cr WHERE cr.criteria=:crt", CriteriaRange.class);
            query.setParameter("crt", criteria);
            cr = query.getResultList();
        } catch (NoResultException e) {
            cr = null;
        }
        return cr;
    }

    public void deleteById(Long id) {
        em.remove(findById(id));
        em.flush();
    }
}
