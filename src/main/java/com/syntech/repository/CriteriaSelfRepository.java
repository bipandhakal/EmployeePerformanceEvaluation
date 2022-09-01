package com.syntech.repository;

import com.syntech.model.Criteria;
import com.syntech.model.CriteriaSelf;
import com.syntech.model.CriteriaSelf_;
import com.syntech.model.Employee;
import com.syntech.model.Months;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author bipan
 */
@Stateless
public class CriteriaSelfRepository extends LazyRepository<CriteriaSelf> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;

    public CriteriaSelfRepository() {
        super(CriteriaSelf.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CriteriaSelfRepository filterByCriteria(Criteria criteria) {
        Predicate criteriaPredicates = criteriaBuilder.equal(root.get(CriteriaSelf_.criteria), criteria);
        this.addCriteria(criteriaPredicates);
        return this;
    }

    public CriteriaSelfRepository filterByEmployee(Employee employee) {
        Predicate employeePredicates = criteriaBuilder.equal(root.get(CriteriaSelf_.employee), employee);
        this.addCriteria(employeePredicates);
        return this;
    }

    public CriteriaSelfRepository filterByMonths(Months months) {
        Predicate monthsPredicates = criteriaBuilder.equal(root.get(CriteriaSelf_.months), months);
        this.addCriteria(monthsPredicates);
        return this;
    }

    public List<CriteriaSelf> findByCriteria(Criteria criteria) {
        List<CriteriaSelf> cs = null;
        try {
            cs = ((CriteriaSelfRepository) this.startQuery()).filterByCriteria(criteria).getResultList();
        } catch (NoResultException e) {
            cs = null;
        }
        return cs;
    }

    public void deleteById(Long id) {
        em.remove(findById(id));
        em.flush();
    }

    public List<CriteriaSelf> findByEmployeeNMonths(Employee employee, Months months) {
        List<CriteriaSelf> cs = null;
        try {
            cs = ((CriteriaSelfRepository) this.startQuery()).filterByEmployee(employee).filterByMonths(months).getResultList();
        } catch (NoResultException e) {
            cs = null;
        }
        return cs;
    }

    public Boolean isAlreadyInserted(Employee employee, Months months, Criteria criteria) {
        try {
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<CriteriaSelf> root = criteriaQuery.from(CriteriaSelf.class);
            criteriaQuery.select(criteriaBuilder.count(root));
            this.addCriteria(criteriaBuilder.equal(root.get(CriteriaSelf_.months), months));
            this.addCriteria(criteriaBuilder.equal(root.get(CriteriaSelf_.employee), employee));
            this.addCriteria(criteriaBuilder.equal(root.get(CriteriaSelf_.criteria), criteria));
            Long count = getEntityManager().createQuery(criteriaQuery.where(predicates.toArray(new Predicate[]{}))).getSingleResult();
            System.out.println("Total count is :" + count);
            return count != null && count > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
