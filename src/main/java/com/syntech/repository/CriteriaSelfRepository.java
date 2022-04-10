package com.syntech.repository;

import com.syntech.model.CriteriaSelf;

/**
 *
 * @author bipan
 */
public class CriteriaSelfRepository extends AbstractRepository<CriteriaSelf> {

    @Override
    public void edit(CriteriaSelf criteriaSelf) {

        super.findAll().stream().filter(x -> x.getId().equals(criteriaSelf.getId()))
                .forEach(crts -> {
                    crts.setMarks(criteriaSelf.getMarks());

                });
    }
}
