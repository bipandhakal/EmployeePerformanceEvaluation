package com.syntech.controller;

import com.syntech.model.Category;
import com.syntech.repository.CategoryRepository;
import com.syntech.util.MessageUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author bipan
 */
@ViewScoped
@Named
public class CategoryController implements Serializable {

    private Category category;

    private List<Category> categoryList;

    private LazyDataModel<Category> lazyModel;

    @Inject
    private CategoryRepository categoryRepository;

    @Inject
    private MessageUtil messageUtil;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public LazyDataModel<Category> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Category> lazyModel) {
        this.lazyModel = lazyModel;
    }

    @PostConstruct
    public void init() {
        this.category = new Category();
        this.lazyModel = new LazyDataModel(categoryRepository);
    }

    public void beforeCreate() {
        this.category = new Category();
    }

    public void create() {
        categoryRepository.create(category);
        this.categoryList = categoryRepository.findAll();
        messageUtil.showInfo("Category Created Successfully!");
    }

    public void beforeEdit(Category ctg) {
        this.category = categoryRepository.findById(ctg.getId());
    }

    public void edit() {
        categoryRepository.edit(this.category);
        this.categoryList = categoryRepository.findAll();
        messageUtil.showInfo("Category Edited Successfully");
    }

    public void delete(Category category) {
        categoryRepository.delete(category);
        this.categoryList = categoryRepository.findAll();
        messageUtil.showInfo("Category Removed");
    }

    public void findAll() {
        categoryRepository.findAll();
    }

    public void findById(Long id) {
        categoryRepository.findById(id);
    }
}
