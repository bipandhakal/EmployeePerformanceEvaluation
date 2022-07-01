package com.syntech.repository;

import com.syntech.model.Employee;
import com.syntech.model.Employee_;
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
public class EmployeeRepository extends LazyRepository<Employee> {

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

    public EmployeeRepository filterByUserName(String username) {
        Predicate userNamePredicates = criteriaBuilder.equal(root.get(Employee_.firstName), username);
        this.addCriteria(userNamePredicates);
        return this;
    }
    

    public Employee findByUserName(String uname) {
        Employee emp;
        try {
            emp = ((EmployeeRepository) this.startQuery()).filterByUserName(uname).getSingleResult();
        } catch (NoResultException e) {
            emp = null;
        }
        return emp;
    }
}
