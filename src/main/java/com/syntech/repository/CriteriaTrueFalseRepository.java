package com.syntech.repository;

import com.syntech.model.Criteria;
import com.syntech.model.CriteriaTrueFalse;
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

    public List<CriteriaTrueFalse> findByCriteria(Criteria criteria) {
        List<CriteriaTrueFalse> crtf = null;
        try {
            Query query = em.createQuery("SELECT crtf FROM CriteriaTrueFalse crtf WHERE crtf.criteria=:crt", CriteriaTrueFalse.class);
            query.setParameter("crt", criteria);
            crtf = query.getResultList();
        } catch (NoResultException e) {
            crtf = null;
        }
        return crtf;
    }
}
