package com.syntech.repository;

import com.syntech.model.IEntity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
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

    public List<T> lazyLoad(int offset, int pagesize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) throws ParseException {
        criteriaQuery.select(root);

        if (sortBy == null || sortBy.isEmpty()) {
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
        } else {
            for (Map.Entry<String, SortMeta> entry : sortBy.entrySet()) {
                if (entry.getValue().getOrder().equals(SortOrder.ASCENDING)) {
                    criteriaQuery.orderBy(criteriaBuilder.asc(getTransitivePath(entry.getValue().getField())));
                } else {
                    criteriaQuery.orderBy(criteriaBuilder.desc(getTransitivePath(entry.getValue().getField())));
                }
            }
        }
        if (filterBy != null && !filterBy.isEmpty()) {
            for (Map.Entry<String, FilterMeta> entry : filterBy.entrySet()) {
                Path p = getTransitivePath(entry.getValue().getField());
                if (p.getJavaType().isEnum() || p.getJavaType().isAssignableFrom(Date.class)) {
                    commonAddFilter(p, entry.getValue().getFilterValue().toString());
                } else {
                    if (entry.getValue().getMatchMode().equals(MatchMode.EXACT) || entry.getValue().getMatchMode().equals(MatchMode.EQUALS)) {
                        this.predicates.add(criteriaBuilder.equal(p, entry.getValue().getFilterValue().toString()));
                    }
                    if (entry.getValue().getMatchMode().equals(MatchMode.CONTAINS)) {
                        this.predicates.add(criteriaBuilder.like(p, "%" + entry.getValue().getFilterValue().toString() + "%"));
                    }
                    if (entry.getValue().getMatchMode().equals(MatchMode.STARTS_WITH)) {
                        this.predicates.add(criteriaBuilder.like(p, entry.getValue().getFilterValue().toString() + "%"));
                    }
                }
            }
        }

        List<T> list = getResultList(offset, pagesize);
        return list;
    }

    public void commonAddFilter(Path p, Object filterValue) throws ParseException {
        if (p.getJavaType().isEnum()) {
            Object objectEnum;
            if (filterValue.getClass().equals(String.class)) {
                objectEnum = Enum.valueOf(p.getJavaType(), (String) filterValue);
            } else {
                objectEnum = filterValue;
            }
            predicates.add(criteriaBuilder.equal(p, objectEnum));
        }

        if (p.getJavaType().isAssignableFrom(Date.class)) {
            Object objectEnum1;
            if (filterValue.getClass().equals(String.class)) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                objectEnum1 = formatter.parse((String) filterValue);
            } else {
                objectEnum1 = filterValue;
            }
            predicates.add(criteriaBuilder.equal(p, objectEnum1));
        }
    }

    public Integer lazyCount() {
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(getEntityClass())));
        Long count = getEntityManager().createQuery(criteriaQuery).getSingleResult();
        return count == null ? 0 : count.intValue();
    }

    protected Path getTransitivePath(String pathString) {
        String result[];
        result = pathString.split("\\.");
        Path p = null;
        Boolean firstTime = true;
        for (String filterPropty : result) {
            if (firstTime) {
                firstTime = false;
                p = root.get(filterPropty);
            } else {
                p = p.get(filterPropty);
            }
        }
        return p;
    }
}
