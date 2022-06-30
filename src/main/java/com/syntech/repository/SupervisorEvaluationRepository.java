package com.syntech.repository;

import com.syntech.model.Criteria;
import com.syntech.model.Employee;
import com.syntech.model.Months;
import com.syntech.model.SupervisorEvaluation;
import com.syntech.model.SupervisorEvaluation_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author bipan
 */
@Stateless
public class SupervisorEvaluationRepository extends LazyRepository<SupervisorEvaluation> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;

    public SupervisorEvaluationRepository() {
        super(SupervisorEvaluation.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SupervisorEvaluationRepository filterByEmployee(Employee employee) {
        Predicate employeePredicates = criteriaBuilder.equal(root.get(SupervisorEvaluation_.employee), employee);
        this.addCriteria(employeePredicates);
        return this;
    }

    public SupervisorEvaluationRepository filterByMonths(Months months) {
        Predicate monthsPredicates = criteriaBuilder.equal(root.get(SupervisorEvaluation_.months), months);
        this.addCriteria(monthsPredicates);
        return this;
    }

    public SupervisorEvaluationRepository filterByCriteria(Criteria criteria) {
        Predicate criteriaPredicates = criteriaBuilder.equal(root.get(SupervisorEvaluation_.criteria), criteria);
        this.addCriteria(criteriaPredicates);
        return this;
    }

    public List<SupervisorEvaluation> findBySelectedEmployee(Employee employee) {
        List<SupervisorEvaluation> se = null;
        try {
            se = ((SupervisorEvaluationRepository) this.startQuery()).filterByEmployee(employee).getResultList();
//            Query query = em.createQuery("SELECT ea FROM SupervisorEvaluation ea WHERE ea.employee=:e", SupervisorEvaluation.class);
//            query.setParameter("e", employee);
//            ea = query.getResultList();
        } catch (NoResultException e) {
            se = null;
        }
        return se;
    }

    public SupervisorEvaluation findByEmployeeNCriteria(Employee employee, Criteria criteria) {
        SupervisorEvaluation se = null;
        try {
            se = ((SupervisorEvaluationRepository) this.startQuery()).filterByEmployee(employee).filterByCriteria(criteria).getSingleResult();
//            Query query = em.createQuery("SELECT se FROM SupervisorEvaluation se WHERE se.criteria=:c AND se.employee=:e", SupervisorEvaluation.class);
//            query.setParameter("c", criteria).setParameter("e", employee);
//            se = (SupervisorEvaluation) query.getSingleResult();
        } catch (NoResultException e) {
            se = null;
        }
        return se;
    }

    public List<SupervisorEvaluation> findByEmployeeNMonths(Employee employee, Months months) {
        List<SupervisorEvaluation> se = null;
        try {
            se = ((SupervisorEvaluationRepository) this.startQuery()).filterByEmployee(employee).filterByMonths(months).getResultList();
//            Query query = em.createQuery("SELECT se FROM SupervisorEvaluation se WHERE se.months=:m AND se.employee=:e", SupervisorEvaluation.class);
//            query.setParameter("m", months).setParameter("e", employee);
//            se = query.getResultList();
        } catch (NoResultException e) {
            se = null;
        }
        return se;
    }

//    public Boolean checkIfFound(SupervisorEvaluation sevaluation) {
//
//        String month = sevaluation.getMonths().getName();
//        String employeeName = sevaluation.getEmployee().getFirstName();
//
//        Query query = em.createQuery("Select a from SupervisorEvaluation a where a.months = :u and a.employee = :x", SupervisorEvaluation.class);
//        query.setParameter("u", month);
//        query.setParameter("x", employeeName);
//        SupervisorEvaluation s = (SupervisorEvaluation) query.getSingleResult();
//
//        if (s == null) {
//            return false;
//        }
//        return true;
//    }
    public void deleteById(Long id) {
        em.remove(findById(id));
        em.flush();
    }

    public Boolean isAlreadyInserted(Employee employee, Months months, Criteria criteria) {
        try {
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<SupervisorEvaluation> root = criteriaQuery.from(SupervisorEvaluation.class);
            criteriaQuery.select(criteriaBuilder.count(root));
            this.addCriteria(criteriaBuilder.equal(root.get(SupervisorEvaluation_.months), months));
            this.addCriteria(criteriaBuilder.equal(root.get(SupervisorEvaluation_.employee), employee));
            this.addCriteria(criteriaBuilder.equal(root.get(SupervisorEvaluation_.criteria), criteria));
            Long count = getEntityManager().createQuery(criteriaQuery.where(predicates.toArray(new Predicate[]{}))).getSingleResult();
            System.out.println("Total count is :" + count);
            return count != null && count > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
