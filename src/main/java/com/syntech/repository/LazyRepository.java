package com.syntech.repository;

import com.syntech.model.IEntity;
import java.util.List;
import java.util.Map;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
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
public abstract class LazyRepository<T extends IEntity> extends AbstractRepository<T> {

    public LazyRepository(Class<T> entityClass) {
        super(entityClass);
    }

    public List<T> lazyLoad(int offset, int pagesize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getEntityClass());
        Root<T> root = criteriaQuery.from(getEntityClass());
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

        TypedQuery<T> query = getEntityManager().createQuery(criteriaQuery);
        query.setFirstResult(offset);
        query.setMaxResults(pagesize);
        List<T> list = query.getResultList();
        return list;
    }

    public Integer lazyCount() {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(getEntityClass())));
        Long count = getEntityManager().createQuery(criteriaQuery).getSingleResult();
        return count == null ? 0 : count.intValue();
    }

//    protected Path getTransitivePath(String pathString) {
//        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
//        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getEntityClass());
//        Root<T> root = criteriaQuery.from(getEntityClass());
//        String result[];
//        result = pathString.split("\\.");
//        Path p = null;
//        Boolean firstTime = true;
//        for (String filterPropty : result) {
//            if (firstTime) {
//                firstTime = false;
//                p = root.get(filterPropty);
//            } else {
//                p = p.get(filterPropty);
//            }
//        }
//        return p;
//    }
//
//    public LazyRepository<T> addSorting(String sortField, final SortOrder sortOrder) {
//        if (sortField != null) {
//            Path pSort = getTransitivePath(sortField);
//            return this.addSorting(pSort, sortOrder);
//        }
//        return this;
//    }
//
//    public LazyRepository<T> addSorting(Path p, final SortOrder sortOrder) {
//        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
//        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getEntityClass());
//        Root<T> root = criteriaQuery.from(getEntityClass());
//        criteriaQuery.select(root);
//
//        if (sortOrder == SortOrder.ASCENDING) {
//            criteriaQuery.orderBy(criteriaBuilder.asc(p));
//        } else {
//            criteriaQuery.orderBy(criteriaBuilder.desc(p));
//        }
//        this.sorted = true;
//        return this;
//    }
}
