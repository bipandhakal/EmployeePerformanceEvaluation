package com.syntech.repository;

import com.syntech.model.Category;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bipan
 */
@Stateless
public class CategoryRepository extends AbstractRepository<Category> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;

    public CategoryRepository() {
        super(Category.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
