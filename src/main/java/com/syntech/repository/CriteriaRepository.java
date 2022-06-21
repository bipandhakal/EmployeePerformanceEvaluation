package com.syntech.repository;

import com.syntech.model.CalculatedBy;
import com.syntech.model.Category;
import com.syntech.model.Criteria;
import com.syntech.model.Criteria_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

/**
 *
 * @author bipan
 */
@Stateless
public class CriteriaRepository extends LazyRepository<Criteria> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;

    public CriteriaRepository() {
        super(Criteria.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CriteriaRepository filterByCategory(Category category) {
        Predicate categoryPredicates = criteriaBuilder.equal(root.get(Criteria_.category), category);
        this.addCriteria(categoryPredicates);
        return this;
    }

    public CriteriaRepository filterByCalculatedByMethod(CalculatedBy calculatedBy) {
        Predicate calculatedByPredicates = criteriaBuilder.equal(root.get(Criteria_.calculatedBy), calculatedBy);
        this.addCriteria(calculatedByPredicates);
        return this;
    }

    public List<Criteria> findByCategory(Category category) {
        List<Criteria> crList = null;
        try {
            crList = ((CriteriaRepository) this.startQuery()).filterByCategory(category).getResultList();

//            Query query = em.createQuery("SELECT ct FROM Criteria ct WHERE ct.category=:ctg", Criteria.class);
//            query.setParameter("ctg", category);
//            crList = query.getResultList();
        } catch (NoResultException e) {
            crList = null;
        }
        return crList;
    }

    public List<Criteria> findByCalculatedByMethod(CalculatedBy calculatedBy) {
        List<Criteria> criteriaList = null;
        try {
            criteriaList = ((CriteriaRepository) this.startQuery()).filterByCalculatedByMethod(calculatedBy).getResultList();
//            Query query = em.createQuery("SELECT c FROM Criteria c WHERE c.calculatedBy=:cby", Criteria.class);
//            query.setParameter("cby", calculatedBy);
//            criteriaList = query.getResultList();
        } catch (NoResultException e) {
            criteriaList = null;
        }
        return criteriaList;
    }

    public void deleteById(Long id) {
        em.remove(findById(id));
        em.flush();
    }
}
