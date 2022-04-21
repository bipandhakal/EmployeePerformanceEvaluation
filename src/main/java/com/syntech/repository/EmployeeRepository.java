package com.syntech.repository;

import com.syntech.model.EmployeeEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author bipan
 */
public class EmployeeRepository extends AbstractRepository<EmployeeEntity> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;
    
    public EmployeeRepository(){
        System.out.println("fghjk");
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void edit(EmployeeEntity emp) {

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
        super.findAll().stream().filter(x -> x.getId().equals(emp.getId()))
                .forEach(eee -> {
                    eee.setFirstName(emp.getFirstName());
                    eee.setLastName(emp.getLastName());
                    eee.setJoinDate(emp.getJoinDate());
                });
    }

    @Override
    public void create(EmployeeEntity obj) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EPE");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("INSERT INTO employee (id, firstName, lastName, joinDate) VALUES (?, ?, ?, ?)");
        query.setParameter(1, obj.getId());
        query.setParameter(2, obj.getFirstName());
        query.setParameter(3, obj.getLastName());
        query.setParameter(4, obj.getJoinDate());
        query.executeUpdate();
        emf.close();
        em.close();
    }

    @Override
    public List<EmployeeEntity> findAll() {
        Query query = em.createQuery("select e from EmployeeEntity e", EmployeeEntity.class);
        return query.getResultList();
    }

}
