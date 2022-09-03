package com.syntech.repository;

import com.syntech.model.Criteria;
import com.syntech.model.Employee;
import com.syntech.model.EmployeeAchievements;
import com.syntech.model.EmployeeAchievements_;
import com.syntech.model.Months;
import java.util.ArrayList;
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
public class EmployeeAchievementsRepository extends LazyRepository<EmployeeAchievements> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;

    public EmployeeAchievementsRepository() {
        super(EmployeeAchievements.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeeAchievementsRepository filterByEmployee(Employee employee) {
        Predicate employeePredicates = criteriaBuilder.equal(root.get(EmployeeAchievements_.employee), employee);
        this.addCriteria(employeePredicates);
        return this;
    }

    public EmployeeAchievementsRepository filterByMonths(Months months) {
        Predicate monthsPredicates = criteriaBuilder.equal(root.get(EmployeeAchievements_.months), months);
        this.addCriteria(monthsPredicates);
        return this;
    }

    public EmployeeAchievementsRepository filterByCriteria(Criteria criteria) {
        Predicate criteriaPredicates = criteriaBuilder.equal(root.get(EmployeeAchievements_.criteria), criteria);
        this.addCriteria(criteriaPredicates);
        return this;
    }

    public List<EmployeeAchievements> findBySelectedEmployee(Employee employee) {
        List<EmployeeAchievements> ea = null;
        try {
            ea = ((EmployeeAchievementsRepository) this.startQuery()).filterByEmployee(employee).getResultList();
        } catch (NoResultException e) {
            ea = null;
        }
        return ea;
    }

    public List<EmployeeAchievements> findByEmployeeNMonths(Employee employee, Months months) {
        List<EmployeeAchievements> ea = null;
        try {
            ea = ((EmployeeAchievementsRepository) this.startQuery()).filterByEmployee(employee).filterByMonths(months).getResultList();
        } catch (NoResultException e) {
            ea = null;
        }
        return ea;
    }

    public EmployeeAchievements findByEmployeeNCriteria(Employee employee, Criteria criteria) {
        EmployeeAchievements ea = null;
        try {
            ea = ((EmployeeAchievementsRepository) this.startQuery()).filterByEmployee(employee).filterByCriteria(criteria).getSingleResult();
        } catch (NoResultException e) {
            ea = null;
        }
        return ea;
    }

    public void deleteById(Long id) {
        em.remove(findById(id));
        em.flush();
    }

    public Boolean isAlreadyInserted(Employee employee, Months months, Criteria criteria) {
        try {
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<EmployeeAchievements> root = criteriaQuery.from(EmployeeAchievements.class);
            criteriaQuery.select(criteriaBuilder.count(root));
            this.addCriteria(criteriaBuilder.equal(root.get(EmployeeAchievements_.months), months));
            this.addCriteria(criteriaBuilder.equal(root.get(EmployeeAchievements_.employee), employee));
            this.addCriteria(criteriaBuilder.equal(root.get(EmployeeAchievements_.criteria), criteria));
            Long count = getEntityManager().createQuery(criteriaQuery.where(predicates.toArray(new Predicate[]{}))).getSingleResult();
            System.out.println("Total count is :" + count);
            return count != null && count > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<EmployeeAchievements> findByEmployee(Employee employee) {
        List<EmployeeAchievements> cs = new ArrayList<>();
        try {
            cs = ((EmployeeAchievementsRepository) this.startQuery()).filterByEmployee(employee).getResultList();
        } catch (NoResultException e) {
            cs = null;
        }
        return cs;
    }
}
