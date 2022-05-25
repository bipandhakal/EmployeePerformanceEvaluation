package com.syntech.repository;

import com.syntech.model.Employee;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
//        Employee employee = em.find(Employee.class, id);
//        em.remove(employee);
        em.flush();
    }

}
