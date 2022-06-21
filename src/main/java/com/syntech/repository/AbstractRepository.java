package com.syntech.repository;

import com.syntech.model.IEntity;
import com.syntech.model.IRepository;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author bipan
 */
public abstract class AbstractRepository<T extends IEntity> implements IRepository<T> {

    protected abstract EntityManager getEntityManager();
    protected CriteriaQuery<T> criteriaQuery;
    protected CriteriaBuilder criteriaBuilder;
    protected Root<T> root;
    private Class<T> entityClass;
    protected List<Predicate> predicates;

    public AbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public CriteriaQuery<T> getCriteriaQuery() {
        return criteriaQuery;
    }

    public void setCriteriaQuery(CriteriaQuery<T> criteriaQuery) {
        this.criteriaQuery = criteriaQuery;
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return criteriaBuilder;
    }

    public void setCriteriaBuilder(CriteriaBuilder criteriaBuilder) {
        this.criteriaBuilder = criteriaBuilder;
    }

    public Root<T> getRoot() {
        return root;
    }

    public void setRoot(Root<T> root) {
        this.root = root;
    }

    @PostConstruct
    protected void _startQuery() {
        this.criteriaBuilder = getEntityManager().getCriteriaBuilder();
        this.criteriaQuery = this.criteriaBuilder.createQuery(getEntityClass());
        root = this.criteriaQuery.from(getEntityClass());
        predicates = new ArrayList<Predicate>();
    }

    public AbstractRepository<T> startQuery() {
        this._startQuery();
        return this;
    }

    @Override
    public void create(T obj) {
        getEntityManager().persist(obj);
        getEntityManager().flush();
    }

    @Override
    public void edit(T obj) {
        getEntityManager().merge(obj);
        getEntityManager().flush();
    }

    @Override
    public List<T> findAll() {
        return getEntityManager().createQuery("Select t from " + entityClass.getName() + " t").getResultList();
    }

    @Override
    public T findById(Long id) {
        return getEntityManager().find(entityClass, id);
    }

    @Override
    public void delete(T obj) {
        getEntityManager().remove(findById(obj.getId()));
        getEntityManager().flush();
    }

    public AbstractRepository<T> addCriteria(Predicate p) {
        this.predicates.add(p);
        return this;
    }

    public T getSingleResult() {
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        return getEntityManager().createQuery(criteriaQuery).getSingleResult();
    }

    public List<T> getResultList() {
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }
}
