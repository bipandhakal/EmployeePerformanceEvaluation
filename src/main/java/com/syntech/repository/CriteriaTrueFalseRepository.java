package com.syntech.repository;

import com.syntech.model.CriteriaTrueFalse;

/**
 *
 * @author bipan
 */
public class CriteriaTrueFalseRepository extends AbstractRepository<CriteriaTrueFalse> {
    
    @Override
    public void edit(CriteriaTrueFalse criteriaTrueFalse) {
        
        super.findAll().stream().filter(x -> x.getId().equals(criteriaTrueFalse.getId()))
                .forEach(crtf -> {
                    crtf.setCriteriaId(criteriaTrueFalse.getCriteriaId());
                    crtf.setStatus(criteriaTrueFalse.getStatus());
                    crtf.setMarks(criteriaTrueFalse.getMarks());
                });
    }
}
