package com.syntech.repository;

import com.syntech.model.Criteria;
import com.syntech.model.CriteriaSelf;
import com.syntech.model.Employee;
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
public class CriteriaSelfRepository extends AbstractRepository<CriteriaSelf> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;

    public CriteriaSelfRepository() {
        super(CriteriaSelf.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<CriteriaSelf> findByCriteria(Criteria criteria) {
        List<CriteriaSelf> cs = null;
        try {
            Query query = em.createQuery("SELECT cr FROM CriteriaSelf cr WHERE cr.criteria=:crt", CriteriaSelf.class);
            query.setParameter("crt", criteria);
            cs = query.getResultList();
        } catch (NoResultException e) {
            cs = null;
        }
        return cs;
    }
    
       public List<CriteriaSelf> findByEmployee(Employee employee) {
        List<CriteriaSelf> criteriaSelfList = null;
        try {
            Query query = em.createQuery("SELECT criteriaSelfList FROM CriteriaSelf criteriaSelfList WHERE criteriaSelfList.employee=:emp", CriteriaSelf.class);
            query.setParameter("emp", employee);
            criteriaSelfList = query.getResultList();
        } catch (NoResultException e) {
            criteriaSelfList = null;
        }
        return criteriaSelfList;
    }
}
