package com.syntech.controller;

import com.syntech.model.EmployeeEntity;
import com.syntech.repository.EmployeeRepository;
import com.syntech.util.ValidationUtil;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author bipan
 */
public class EmployeeController {

    private EmployeeRepository employeeRepository;
    private ValidationUtil validationUtil;

    public EmployeeController() {
        validationUtil = new ValidationUtil();
    }

    public void showMenu(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

        Scanner sc = new Scanner(System.in);
        String num;
        do {
            System.out.println("Employee");
            System.out.println("Press 1.1 to create employee");
            System.out.println("Press 1.2 to edit employee");
            System.out.println("Press 1.3 to delete employee");
            System.out.println("Press 1.4 to findAll employee");
            System.out.println("Press 1.5 to findById employee");
            System.out.println("Enter your choice : ");
            num = sc.next();

            switch (num) {
                case "1.1":
                    create();
                    break;

                case "1.2":
                    edit();
                    break;

                case "1.3":
                    delete();
                    break;

                case "1.4":
                    findAll();
                    break;

                case "1.5":
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
        String firstName = null;
        String lastName = null;
        String joinedDate = null;

        Scanner sc = new Scanner(System.in);
        while (!validationUtil.validatesLong(id)) {
            System.out.println("Enter employee id");
            id = sc.nextLong();
        }

        while (!validationUtil.validateString(firstName)) {
            System.out.println("Enter employee firstname");
            firstName = sc.next();
        }

        do {
            System.out.println("Enter employee lastname");
            lastName = sc.next();

        } while (!validationUtil.validateString(lastName));

        do {
            System.out.println("Enter employee join-date");
            joinedDate = sc.next();
        } while (joinedDate == null || joinedDate.isEmpty() || !validationUtil.validateDate(joinedDate));

        LocalDate joinDate = LocalDate.parse(joinedDate);
        EmployeeEntity employee = new EmployeeEntity(id, firstName, lastName, joinDate);

        employeeRepository.create(employee);
        System.out.println("Created Successfully!");

    }

    public void edit() {
        Long id = null;
        String firstName = null;
        String lastName = null;
        String joinedDate = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter employee id to edit");
        id = sc.nextLong();

        EmployeeEntity employee = employeeRepository.findById(id);
        if (employee == null) {
            System.out.println("Employee with id: " + id + " not found");

        } else {
            while (firstName == null || firstName.isEmpty()) {
                System.out.println("Enter employee firstname");
                firstName = sc.next();
            }

            do {
                System.out.println("Enter employee lastname");
                lastName = sc.next();

            } while (lastName == null || lastName.isEmpty());

            do {
                System.out.println("Enter employee join-date");
                joinedDate = sc.next();
            } while (joinedDate == null || joinedDate.isEmpty() || !validationUtil.validateDate(joinedDate));

            LocalDate joinDate = LocalDate.parse(joinedDate);
            EmployeeEntity emp = new EmployeeEntity(id, firstName, lastName, joinDate);

            employeeRepository.edit(emp);
            System.out.println("Edited Successfully!");
        }
    }

    public void delete() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter employee id to delete");
        Long id = sc.nextLong();

        EmployeeEntity employee = employeeRepository.findById(id);
        if (employee == null) {
            System.out.println("Employee with id: " + id + " not found");
        } else {
            employeeRepository.delete(employee);
            System.out.println("Employee removed");
        }

    }

    public void findAll() {

        Iterator<EmployeeEntity> i = employeeRepository.findAll().iterator();
        while (i.hasNext()) {
            EmployeeEntity employee = i.next();
            System.out.println(employee);
        }
    }

    public void findById() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter employee id to find");
        Long id = sc.nextLong();

        EmployeeEntity employee = employeeRepository.findById(id);
        if (employee == null) {
            System.out.println("Employee with id: " + id + " not found");
        } else {
            System.out.println(employee);
        }
    }
}
