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
public class CategoryRepository extends LazyRepository<Category> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;

    public CategoryRepository() {
        super(Category.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void deleteById(Long id) {
        em.remove(findById(id));
        em.flush();
    }
}
