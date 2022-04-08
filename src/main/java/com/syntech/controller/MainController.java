package com.syntech.controller;

import com.syntech.repository.CategoryRepository;
import com.syntech.repository.CriteriaRepository;
import com.syntech.repository.EmployeeRepository;
import java.util.Scanner;

/**
 *
 * @author bipan
 */
public class MainController {

    static EmployeeController employeeController;
    static CategoryController categoryController;
    static CriteriaController criteriaController;
    static EmployeeRepository employeeRepository;
    static CategoryRepository categoryRepository;
    static CriteriaRepository criteriaRepository;

    public static void main(String[] args) {
        employeeController = new EmployeeController();
        categoryController = new CategoryController();
        criteriaController = new CriteriaController();
        employeeRepository = new EmployeeRepository();
        categoryRepository = new CategoryRepository();
        criteriaRepository = new CriteriaRepository();
        showMenu();
    }

    public static void showMenu() {

        Scanner sc = new Scanner(System.in);
        String num;
        do {
            System.out.println("1.Employee");
            System.out.println("2.Category");
            System.out.println("3.Criteria");
            System.out.println("Enter your choice : ");
            num = sc.next();

            switch (num) {
                case "1":
                    employeeController.showMenu(employeeRepository);
                    break;

                case "2":
                    categoryController.showMenu(categoryRepository);
                    break;

                case "3":
                    criteriaController.showMenu(criteriaRepository);
                    break;

                default:
                    System.out.println("Invalid number");
                    break;
            }
        } while (!num.equals("0"));

    }
}
