package com.syntech.repository;

import com.syntech.model.Criteria;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bipan
 */
@Stateless
public class CriteriaRepository extends AbstractRepository<Criteria> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;

    public CriteriaRepository() {
        super(Criteria.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

//    @Override
//    public void edit(Criteria criteria) {
//
//        super.findAll().stream().filter(x -> x.getId().equals(criteria.getId()))
//                .forEach(crt -> {
//                    crt.setCategoryId(criteria.getCategoryId());
//                    crt.setName(criteria.getName());
//                    crt.setMarks(criteria.getMarks());
//                    crt.setTarget(criteria.getTarget());
//                    crt.setCalculatedBy(criteria.getCalculatedBy());
//                });
//    }
}
