package com.syntech.repository;

import com.syntech.model.CalculatedBy;
import com.syntech.model.Category;
import com.syntech.model.Criteria;
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
public class CriteriaRepository extends AbstractRepository<Criteria> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;

    public CriteriaRepository() {
        super(Criteria.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Criteria> findByCategory(Category category) {
        List<Criteria> crList = null;
        try {
            Query query = em.createQuery("SELECT crList FROM Criteria crList WHERE crList.category=:ctg", Criteria.class);
            query.setParameter("ctg", category);
            crList = query.getResultList();
        } catch (NoResultException e) {
            crList = null;
        }
        return crList;
    }

    public List<Criteria> findByCalculatedByMethod(CalculatedBy calculatedBy) {
        List<Criteria> criteriaList = null;
        try {
            Query query = em.createQuery("SELECT criteriaList FROM Criteria criteriaList WHERE criteriaList.calculatedBy:cby", Criteria.class);
            query.setParameter("cby", calculatedBy);
            criteriaList = query.getResultList();
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
