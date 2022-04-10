package com.syntech.controller;

import com.syntech.repository.CategoryRepository;
import com.syntech.repository.CriteriaRangeRepository;
import com.syntech.repository.CriteriaRepository;
import com.syntech.repository.CriteriaSelfRepository;
import com.syntech.repository.CriteriaTrueFalseRepository;
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
    static CriteriaRangeController criteriaRangeController;
    static CriteriaTrueFalseController criteriaTrueFalseController;
    static CriteriaSelfController criteriaSelfController;
    static CriteriaRangeRepository criteriaRangeRepository;
    static CriteriaTrueFalseRepository criteriaTrueFalseRepository;
    static CriteriaSelfRepository criteriaSelfRepository;

    public static void main(String[] args) {
        employeeController = new EmployeeController();
        categoryController = new CategoryController();
        criteriaController = new CriteriaController();
        criteriaRangeController = new CriteriaRangeController();
        criteriaTrueFalseController = new CriteriaTrueFalseController();
        criteriaSelfController = new CriteriaSelfController();
        employeeRepository = new EmployeeRepository();
        categoryRepository = new CategoryRepository();
        criteriaRepository = new CriteriaRepository();
        criteriaRangeRepository = new CriteriaRangeRepository();
        criteriaTrueFalseRepository = new CriteriaTrueFalseRepository();
        criteriaSelfRepository = new CriteriaSelfRepository();
        showMenu();
    }

    public static void showMenu() {

        Scanner sc = new Scanner(System.in);
        String num;
        do {
            System.out.println("1.Employee");
            System.out.println("2.Category");
            System.out.println("3.Criteria");
            System.out.println("4.Criteria Range");
            System.out.println("5.Criteria True False");
            System.out.println("6.Criteria Self Marks");
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

                case "4":
                    criteriaRangeController.showMenu(criteriaRangeRepository);
                    break;

                case "5":
                    criteriaTrueFalseController.showMenu(criteriaTrueFalseRepository);
                    break;

                case "6":
                    criteriaSelfController.showMenu(criteriaSelfRepository);
                    break;

                default:
                    System.out.println("Invalid number");
                    break;
            }
        } while (!num.equals("0"));

    }
}
