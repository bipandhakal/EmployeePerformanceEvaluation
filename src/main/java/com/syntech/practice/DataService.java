//package com.syntech.practice;
//
//import com.syntech.model.Employee;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.Query;
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Expression;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//
///**
// *
// * @author bipan
// */
//public enum DataService {
//    INSTANCE;
//    private final EntityManagerFactory emf
//            = Persistence.createEntityManagerFactory("employee-unit");
//
//    DataService() {
//        //persisting some data in database
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//
//        DataFactory dataFactory = new DataFactory();
//        for (int i = 1; i < 100; i++) {
//            Employee employee = new Employee();
//            employee.setName(dataFactory.getName());
//            employee.setPhoneNumber(String.format("%s-%s-%s", dataFactory.getNumberText(3),
//                    dataFactory.getNumberText(3),
//                    dataFactory.getNumberText(4)));
//            employee.setAddress(dataFactory.getAddress() + "," + dataFactory.getCity());
//            em.persist(employee);
//        }
//
//        em.getTransaction().commit();
//        em.close();
//    }
//
//    public List<Employee> getEmployeeList(int start, int size,
//            Map<String, Object> filters) {
//        EntityManager em = emf.createEntityManager();
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Employee> criteriaQuery = cb.createQuery(Employee.class);
//        Root<Employee> root = criteriaQuery.from(Employee.class);
//        CriteriaQuery<Employee> select = criteriaQuery.select(root);
//
//        if (filters != null && filters.size() > 0) {
//            List<Predicate> predicates = new ArrayList<>();
//            for (Map.Entry<String, Object> entry : filters.entrySet()) {
//                String field = entry.getKey();
//                Object value = entry.getValue();
//                if (value == null) {
//                    continue;
//                }
//
//                Expression<String> expr = root.get(field).as(String.class);
//                Predicate p = cb.like(cb.lower(expr),
//                        "%" + value.toString().toLowerCase() + "%");
//                predicates.add(p);
//            }
//            if (predicates.size() > 0) {
//                criteriaQuery.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
//            }
//        }
//
//        TypedQuery<Employee> query = em.createQuery(select);
//        query.setFirstResult(start);
//        query.setMaxResults(size);
//        List<Employee> list = query.getResultList();
//        return list;
//    }
//
//    public int getEmployeeTotalCount() {
//        EntityManager em = emf.createEntityManager();
//        Query query = em.createQuery("Select count(e.id) From Employee e");
//        return ((Long) query.getSingleResult()).intValue();
//    }
//
//    public int getFilteredRowCount(Map<String, Object> filters) {
//        EntityManager em = emf.createEntityManager();
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
//        Root<Employee> root = criteriaQuery.from(Employee.class);
//        CriteriaQuery<Long> select = criteriaQuery.select(cb.count(root));
//
//        if (filters != null && filters.size() > 0) {
//            List<Predicate> predicates = new ArrayList<>();
//            for (Map.Entry<String, Object> entry : filters.entrySet()) {
//                String field = entry.getKey();
//                Object value = entry.getValue();
//                if (value == null) {
//                    continue;
//                }
//
//                Expression<String> expr = root.get(field).as(String.class);
//                Predicate p = cb.like(cb.lower(expr),
//                        "%" + value.toString().toLowerCase() + "%");
//                predicates.add(p);
//            }
//            if (predicates.size() > 0) {
//                criteriaQuery.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
//            }
//        }
//        Long count = em.createQuery(select).getSingleResult();
//        return count.intValue();
//    }
//
//}
