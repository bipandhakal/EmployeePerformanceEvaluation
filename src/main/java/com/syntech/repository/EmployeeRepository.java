package com.syntech.repository;

import com.syntech.model.Employee;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.MatchMode;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

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

    @Override
    public List<Employee> lazyLoad(int offset, int pagesize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(root);

        if (sortBy == null || sortBy.isEmpty()) {
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
        } else {
            for (Map.Entry<String, SortMeta> entry : sortBy.entrySet()) {
                if (entry.getValue().getOrder().equals(SortOrder.ASCENDING)) {
                    criteriaQuery.orderBy(criteriaBuilder.asc(root.get(entry.getValue().getField())));
                } else {
                    criteriaQuery.orderBy(criteriaBuilder.desc(root.get(entry.getValue().getField())));
                }
            }
        }
//
        if (filterBy != null && !filterBy.isEmpty()) {
            Predicate filterPredicate = criteriaBuilder.conjunction();
            for (Map.Entry<String, FilterMeta> entry : filterBy.entrySet()) {
                Predicate predicate = null;
                if (entry.getValue().getMatchMode().equals(MatchMode.EXACT) || entry.getValue().getMatchMode().equals(MatchMode.EQUALS)) {
                    predicate = criteriaBuilder.equal(root.get(entry.getValue().getField()), entry.getValue().getFilterValue().toString());
                }
                if (entry.getValue().getMatchMode().equals(MatchMode.CONTAINS)) {
                    predicate = criteriaBuilder.like(root.get(entry.getValue().getField()), "%" + entry.getValue().getFilterValue().toString() + "%");
                }
                if (entry.getValue().getMatchMode().equals(MatchMode.STARTS_WITH)) {
                    predicate = criteriaBuilder.like(root.get(entry.getValue().getField()), entry.getValue().getFilterValue().toString() + "%");
                }
                if (predicate != null) {
                    filterPredicate = criteriaBuilder.and(filterPredicate, predicate);
                }
            }
            if (filterPredicate != null) {
                criteriaQuery.where(filterPredicate);
            }
        }

        TypedQuery<Employee> query = em.createQuery(criteriaQuery);
        query.setFirstResult(offset);
        query.setMaxResults(pagesize);
        List<Employee> list = query.getResultList();
        return list;
    }

    @Override
    public Integer lazyCount() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(Employee.class)));
        Long count = em.createQuery(criteriaQuery).getSingleResult();
        return count == null ? 0 : count.intValue();
    }
}
