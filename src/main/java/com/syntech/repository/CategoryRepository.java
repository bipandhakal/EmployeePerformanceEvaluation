package com.syntech.repository;

import com.syntech.model.Category;

/**
 *
 * @author bipan
 */
public class CategoryRepository extends AbstractRepository<Category> {

    @Override
    public void edit(Category category) {

        super.findAll().stream().filter(x -> x.getId().equals(category.getId()))
                .forEach(ctg -> {
                    ctg.setName(category.getName());
                    ctg.setTotalMarks(category.getTotalMarks());
                });
    }
}
