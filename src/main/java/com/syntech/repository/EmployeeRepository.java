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

//    @Override
//    public void edit(Employee emp) {
//        Employee e = findById(emp.getId());
//        employeeList.remove(e);
//        employeeList.add(emp);
//        for (Employee eee : employeeList) {
//            if (eee.getId().equals(emp.getId())) {
//                eee.setFirstName(emp.getFirstName());
//                eee.setLastName(emp.getLastName());
//                eee.setJoinDate(emp.getJoinDate());
//                break;
//            }
//        }
//        super.findAll().stream().filter(x -> x.getId().equals(emp.getId()))
//                .forEach(eee -> {
//                    eee.setFirstName(emp.getFirstName());
//                    eee.setLastName(emp.getLastName());
//                    eee.setJoinDate(emp.getJoinDate());
//                });
//    }
//    @Override
//    public List<Employee> findAll() {
//        Query query = em.createQuery("select e from Employee e", Employee.class);
//        return query.getResultList();
//    }
//    @Override
//    public Employee findById(Long id) {
//        return em.find(Employee.class, id);
//    }
}
