package com.syntech.repository;

import com.syntech.model.Criteria;

/**
 *
 * @author bipan
 */
public class CriteriaRepository extends AbstractRepository<Criteria> {
    
    @Override
    public void edit(Criteria criteria) {
        
        super.findAll().stream().filter(x -> x.getId().equals(criteria.getId()))
                .forEach(crt -> {
                    crt.setName(criteria.getName());
                    crt.setMarks(criteria.getMarks());
                    crt.setTarget(criteria.getTarget());
                    crt.setCalculatedBy(criteria.getCalculatedBy());
                });
    }
}
