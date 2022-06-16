package com.syntech.repository;

import com.syntech.model.Employee;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author bipan
 */
@Stateless
public class EmployeeRepository extends AbstractRepository<Employee> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;

    public EmployeeRepository() {
        super(Employee.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void deleteById(Long id) {
        em.remove(findById(id));
        em.flush();
    }

    public List<Employee> findByOffsetNPagesize(int offset, int pagesize) {
        Query query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        query.setMaxResults(pagesize);
        query.setFirstResult(offset);
        return query.getResultList();

//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EPE");
//        EntityManager entityManager = emf.createEntityManager();
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Employee> criteriaQuery = cb.createQuery(Employee.class);
//        Root<Employee> root = criteriaQuery.from(Employee.class);
//        CriteriaQuery<Employee> select = criteriaQuery.select(root);
//
//        TypedQuery<Employee> query = entityManager.createQuery(select);
//        query.setFirstResult(offset);
//        query.setMaxResults(pagesize);
//        List<Employee> list = query.getResultList();
//        return list;
    }

    public Long countTotal() {
        Query query = em.createQuery("SELECT count(e) FROM Employee e", Long.class);
        return (Long) query.getSingleResult();
    }
}
