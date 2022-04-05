package com.syntech.controller;

import java.util.Scanner;

/**
 *
 * @author bipan
 */
public class MainController {

    public static void main(String[] args) {
        EmployeeController employeeController;
        CategoryController categoryController;
        employeeController = new EmployeeController();
        categoryController = new CategoryController();

        Scanner sc = new Scanner(System.in);
        String num;
        do {
            System.out.println("1.Employee");
            System.out.println("Press 1.1 to create employee");
            System.out.println("Press 1.2 to edit employee");
            System.out.println("Press 1.3 to delete employee");
            System.out.println("Press 1.4 to findAll employee");
            System.out.println("Press 1.5 to findById employee");
            System.out.println("--------------------------------------");
            System.out.println("2.Category");
            System.out.println("Press 2.1 to create category");
            System.out.println("Press 2.2 to edit category");
            System.out.println("Press 2.3 to delete category");
            System.out.println("Press 2.4 to findAll category");
            System.out.println("Press 2.5 to findById category");
            System.out.println("Enter your choice : ");
            num = sc.next();

            switch (num) {
                case "1.1":

                    employeeController.create();

                    break;

                case "1.2":

                    employeeController.edit();

                    break;
                case "1.3":

                    employeeController.delete();

                    break;

                case "1.4":

                    employeeController.findAll();

                    break;

                case "1.5":

                    employeeController.findById();

                    break;

                case "2.1":

                    categoryController.create();

                    break;

                case "2.2":

                    categoryController.edit();

                    break;
                case "2.3":

                    categoryController.delete();

                    break;

                case "2.4":

                    categoryController.findAll();

                    break;

                case "2.5":

                    categoryController.findById();

                    break;

                default:
                    System.out.println("Invalid number");
                    break;
            }
        } while (!num.equals("0"));

    }
}
