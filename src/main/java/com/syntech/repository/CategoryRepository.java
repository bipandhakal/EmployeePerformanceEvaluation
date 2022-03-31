package com.syntech.repository;

import com.syntech.model.Category;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bipan
 */
public class CategoryRepository {

    private List<Category> categoryList;

    public CategoryRepository() {
        categoryList = new ArrayList<>();
    }

    public void create(Category c) {
        this.categoryList.add(c);
    }

    public List<Category> findAll() {
        return categoryList;
    }

    public Category findById(Long id) {
        for (Category c : categoryList) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public void delete(Category category) {
        this.categoryList.remove(category);
    }

    public void edit(Category ctg) {

        categoryList.stream().filter(x -> x.getId().equals(ctg.getId()))
                .forEach(eee -> {
                    eee.setName(ctg.getName());
                    eee.setTotalMarks(ctg.getTotalMarks());
                });
    }
}
