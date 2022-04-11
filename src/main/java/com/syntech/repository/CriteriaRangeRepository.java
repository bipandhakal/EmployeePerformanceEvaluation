package com.syntech.repository;

import com.syntech.model.CriteriaRange;

/**
 *
 * @author bipan
 */
public class CriteriaRangeRepository extends AbstractRepository<CriteriaRange> {
    
    @Override
    public void edit(CriteriaRange criteriaRange) {
        
        super.findAll().stream().filter(x -> x.getId().equals(criteriaRange.getId()))
                .forEach(crt -> {
                    crt.setCriteriaId(criteriaRange.getCriteriaId());
                    crt.setFrom(criteriaRange.getFrom());
                    crt.setTo(criteriaRange.getTo());
                    crt.setMarks(criteriaRange.getMarks());
                });
    }
}
