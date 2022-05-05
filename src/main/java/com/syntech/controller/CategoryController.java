package com.syntech.controller;

import com.syntech.model.Category;
import com.syntech.repository.CategoryRepository;
import com.syntech.util.MessageUtil;
import com.syntech.util.ValidationUtil;
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

    @Inject
    private CategoryRepository categoryRepository;

    @Inject
    private ValidationUtil validationUtil;

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

    @PostConstruct
    public void init() {
        this.category = new Category();
        this.categoryList = categoryRepository.findAll();
        System.out.println(categoryList.size());
    }

//    public void showMenu(CategoryRepository categoryRepository) {
//        this.categoryRepository = categoryRepository;
//
//        Scanner sc = new Scanner(System.in);
//        String num;
//        do {
//            System.out.println("Category");
//            System.out.println("Press 2.1 to create category");
//            System.out.println("Press 2.2 to edit category");
//            System.out.println("Press 2.3 to delete category");
//            System.out.println("Press 2.4 to findAll category");
//            System.out.println("Press 2.5 to findById category");
//            System.out.println("Enter your choice : ");
//            num = sc.next();
//
//            switch (num) {
//                case "2.1":
//                    create();
//                    break;
//
//                case "2.2":
//                    edit();
//                    break;
//
//                case "2.3":
//                    delete();
//                    break;
//
//                case "2.4":
//                    findAll();
//                    break;
//
//                case "2.5":
//                    findById();
//                    break;
//
//                case "*":
//                    MainController.showMenu();
//                    break;
//
//                default:
//                    System.out.println("Invalid number");
//                    break;
//            }
//        } while (!num.equals("0"));
//    }
    public void create() {
        categoryRepository.create(category);
        messageUtil.showInfo("Category Created Successfully!");
    }

    public void beforeEdit(Category ctg) {
        this.category = categoryRepository.findById(ctg.getId());
    }

    public void edit() {
        categoryRepository.edit(this.category);
        messageUtil.showInfo("Category Edited Successfully");
    }

    public void delete(Category category) {
        categoryRepository.delete(category);
        messageUtil.showInfo("Category Removed");
    }

    public void findAll() {
        categoryRepository.findAll();
    }

    public void findById(Long id) {
        categoryRepository.findById(id);
    }
}
