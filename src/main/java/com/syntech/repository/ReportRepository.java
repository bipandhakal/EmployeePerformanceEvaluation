package com.syntech.repository;

import com.syntech.model.Report;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
