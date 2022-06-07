package com.syntech.repository;

import com.syntech.model.Employee;
import com.syntech.model.Months;
import com.syntech.model.Report;
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
public class ReportRepository extends AbstractRepository<Report> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;

    public ReportRepository() {
        super(Report.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Report> findByEmployeeNMonths(Employee employee, Months months) {
        List<Report> r = null;
        try {
            Query query = em.createQuery("SELECT r FROM Report r WHERE r.months=:m AND r.employee=:e", Report.class);
            query.setParameter("m", months).setParameter("e", employee);
            r = query.getResultList();
        } catch (NoResultException e) {
            r = null;
        }
        return r;
    }
}
