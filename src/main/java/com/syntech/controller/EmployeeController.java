package com.syntech.controller;

import com.syntech.model.Employee;
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

    private static EmployeeRepository employeeRepository;

    private static ValidationUtil validationUtil;

//    public static void main(String[] args) {
//        employeeRepository = new EmployeeRepository();
//        Employee e1 = new Employee(1L, "bipan", "dhakal", LocalDate.now());
//        Employee e2 = new Employee(2L, "bibas", "poudel", LocalDate.now());
//        Employee e3 = new Employee(3L, "bikram", "sharma", LocalDate.now());
//        employeeRepository.create(e1);
//        employeeRepository.create(e2);
//        employeeRepository.create(e3);
//
//        employeeRepository.findAll().stream().forEach(x -> System.out.println(x));
//
//        Scanner sc = new Scanner(System.in);
//        String num;
//        do {
//            System.out.println("1.Employee");
//            System.out.println("Press 1.1 to create");
//            System.out.println("Press 1.2 to edit");
//            System.out.println("Press 1.3 to delete");
//            System.out.println("Press 1.4 to findAll");
//            System.out.println("Press 1.5 to findById");
//            System.out.println("Enter your choice : ");
//            num = sc.next();
//
//            switch (num) {
//                case "1.1":
//
//                    create();
//
//                    break;
//
//                case "1.2":
//
//                    edit();
//
//                    break;
//                case "1.3":
//
//                    delete();
//
//                    break;
//
//                case "1.4":
//
//                    findAll();
//
//                    break;
//
//                case "1.5":
//
//                    findById();
//
//                    break;
//
//                default:
//                    System.out.println("Invalid number");
//                    break;
//            }
//        } while (!num.equals("0"));
//    }
    public void create() {

        Long id = null;
        String firstName = null;
        String lastName = null;
        String joinedDate = null;

        Scanner sc = new Scanner(System.in);
       // while (validationUtil.validateLong(id)) {
            System.out.println("Enter employee id");
            id = sc.nextLong();
      //  }

        while (validationUtil.validateString(firstName)) {
            System.out.println("Enter employee firstname");
            firstName = sc.next();
        }

        do {
            System.out.println("Enter employee lastname");
            lastName = sc.next();

        } while (validationUtil.validateString(lastName));

        do {
            System.out.println("Enter employee join-date");
            joinedDate = sc.next();
        } while (joinedDate == null || joinedDate.isEmpty() || !validationUtil.validateDate(joinedDate));

        LocalDate joinDate = LocalDate.parse(joinedDate);
        Employee employee = new Employee(id, firstName, lastName, joinDate);

        employeeRepository.create(employee);

    }

    public static void edit() {
        Long id = null;
        String firstName = null;
        String lastName = null;
        String joinedDate = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter employee id to edit");
        id = sc.nextLong();

        Employee employee = employeeRepository.findById(id);
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
            Employee emp = new Employee(id, firstName, lastName, joinDate);

            employeeRepository.edit(emp);
            System.out.println("Edited Successfully!");
        }
    }

    public static void delete() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter employee id to delete");
        Long id = sc.nextLong();

        Employee employee = employeeRepository.findById(id);
        if (employee == null) {
            System.out.println("Employee with id: " + id + " not found");
        } else {
            employeeRepository.delete(employee);
            System.out.println("Employee removed");
        }

    }

    public static void findAll() {

        Iterator<Employee> i = employeeRepository.findAll().iterator();
        while (i.hasNext()) {
            Employee employee = i.next();
            System.out.println(employee);

        }
    }

    public static void findById() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter employee id to find");
        Long id = sc.nextLong();

        Employee employee = employeeRepository.findById(id);
        if (employee == null) {
            System.out.println("Employee with id: " + id + " not found");
        } else {
            System.out.println(employee);
        }
    }
}
