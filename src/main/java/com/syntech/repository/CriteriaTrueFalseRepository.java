package com.syntech.repository;

import com.syntech.model.Criteria;
import com.syntech.model.CriteriaTrueFalse;
import com.syntech.model.CriteriaTrueFalse_;
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
public class CriteriaTrueFalseRepository extends LazyRepository<CriteriaTrueFalse> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;

    public CriteriaTrueFalseRepository() {
        super(CriteriaTrueFalse.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CriteriaTrueFalseRepository filterByCriteria(Criteria criteria) {
        Predicate criteriaPredicates = criteriaBuilder.equal(root.get(CriteriaTrueFalse_.criteria), criteria);
        this.addCriteria(criteriaPredicates);
        return this;
    }

    public List<CriteriaTrueFalse> findByCriteria(Criteria criteria) {
        List<CriteriaTrueFalse> crtf = null;
        try {
            crtf = ((CriteriaTrueFalseRepository) this.startQuery()).filterByCriteria(criteria).getResultList();
//            Query query = em.createQuery("SELECT crtf FROM CriteriaTrueFalse crtf WHERE crtf.criteria=:crt", CriteriaTrueFalse.class);
//            query.setParameter("crt", criteria);
//            crtf = query.getResultList();
        } catch (NoResultException e) {
            crtf = null;
        }
        return crtf;
    }

    public void deleteById(Long id) {
        em.remove(findById(id));
        em.flush();
    }
}
