package com.syntech.repository;

import com.syntech.model.Criteria;
import com.syntech.model.CriteriaRange;
import com.syntech.model.CriteriaRange_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.Predicate;

/**
 *
 * @author bipan
 */
@Stateless
public class CriteriaRangeRepository extends LazyRepository<CriteriaRange> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;

    public CriteriaRangeRepository() {
        super(CriteriaRange.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CriteriaRangeRepository filterByCriteria(Criteria criteria) {
        Predicate criteriaPredicates = criteriaBuilder.equal(root.get(CriteriaRange_.criteria), criteria);
        this.addCriteria(criteriaPredicates);
        return this;
    }

    public List<CriteriaRange> findByCriteria(Criteria criteria) {
        List<CriteriaRange> cr = null;
        try {
            cr = ((CriteriaRangeRepository) this.startQuery()).filterByCriteria(criteria).getResultList();
//            Query query = em.createQuery("SELECT cr FROM CriteriaRange cr WHERE cr.criteria=:crt", CriteriaRange.class);
//            query.setParameter("crt", criteria);
//            cr = query.getResultList();
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
