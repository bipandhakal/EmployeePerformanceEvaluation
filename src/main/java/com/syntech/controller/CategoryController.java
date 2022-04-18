package com.syntech.controller;

import com.syntech.model.Category;
import com.syntech.repository.CategoryRepository;
import com.syntech.util.ValidationUtil;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author bipan
 */
public class CategoryController {

    private CategoryRepository categoryRepository;
    private ValidationUtil validationUtil;

    public CategoryController() {
        validationUtil = new ValidationUtil();

    }

    public void showMenu(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;

        Scanner sc = new Scanner(System.in);
        String num;
        do {
            System.out.println("Category");
            System.out.println("Press 2.1 to create category");
            System.out.println("Press 2.2 to edit category");
            System.out.println("Press 2.3 to delete category");
            System.out.println("Press 2.4 to findAll category");
            System.out.println("Press 2.5 to findById category");
            System.out.println("Enter your choice : ");
            num = sc.next();

            switch (num) {
                case "2.1":
                    create();
                    break;

                case "2.2":
                    edit();
                    break;

                case "2.3":
                    delete();
                    break;

                case "2.4":
                    findAll();
                    break;

                case "2.5":
                    findById();
                    break;

                case "*":
                    MainController.showMenu();
                    break;

                default:
                    System.out.println("Invalid number");
                    break;
            }
        } while (!num.equals("0"));
    }

    public void create() {
        Long id = null;
        String name = null;
        Double totalMarks = null;

        Scanner sc = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        while (!validationUtil.validatesLong(id)) {
            System.out.println("Enter Category id");
            id = sc.nextLong();
        }

        while (!validationUtil.validateString(name)) {
            System.out.println("Enter Category Name");
            name = scanner.nextLine();
        }

        do {
            System.out.println("Enter TotalMarks for Category");
            totalMarks = sc.nextDouble();
        } while (!validationUtil.validatesDouble(totalMarks));
        Category category = new Category(id, name, totalMarks);

        categoryRepository.create(category);
        System.out.println("Created Successfully!");
    }

    public void edit() {
        Long id = null;
        String name = null;
        Double totalMarks = null;
        Scanner sc = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter category id to edit");
        id = sc.nextLong();

        Category category = categoryRepository.findById(id);
        if (category == null) {
            System.out.println("Category with id: " + id + " not found");

        } else {
            while (name == null || name.isEmpty()) {
                System.out.println("Enter Category name");
                name = scanner.nextLine();
            }

            while (totalMarks == null) {
                System.out.println("Enter totalMarks for Category");
                String tm = sc.next();
                try {
                    totalMarks = Double.parseDouble(tm);
                } catch (Exception e) {
                    System.out.println("Invalid input");
                    totalMarks = null;
                }
            }

            Category ctg = new Category(id, name, totalMarks);

            categoryRepository.edit(ctg);
            System.out.println("Edited Successfully!");
        }
    }

    public void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter category id to delete");
        Long id = sc.nextLong();

        Category category = categoryRepository.findById(id);
        if (category == null) {
            System.out.println("Category with id: " + id + " not found");
        } else {
            categoryRepository.delete(category);
            System.out.println("Category removed");
        }
    }

    public void findAll() {
        Iterator<Category> i = categoryRepository.findAll().iterator();
        while (i.hasNext()) {
            Category category = i.next();
            System.out.println(category);
        }
    }

    public void findById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Category id to find");
        Long id = sc.nextLong();

        Category category = categoryRepository.findById(id);
        if (category == null) {
            System.out.println("Category with id: " + id + " not found");
        } else {
            System.out.println(category);
        }
    }
}
