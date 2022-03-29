package com.syntech.controller;

import com.syntech.model.Employee;
import com.syntech.repository.EmployeeRepository;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author bipan
 */
public class EmployeeController {

    private static EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        employeeRepository = new EmployeeRepository();
        Employee e1 = new Employee(1L, "bipan1", "dhakal", LocalDate.now());
        Employee e2 = new Employee(2L, "bipan2", "dhakal", LocalDate.now());
        Employee e3 = new Employee(3L, "bipan3", "dhakal", LocalDate.now());
        employeeRepository.create(e1);
        employeeRepository.create(e2);
        employeeRepository.create(e3);

        employeeRepository.findAll().stream().forEach(x -> System.out.println(x));

        Long id = null;
        String firstName = null;
        String lastName = null;
        String joinedDate = null;

        Scanner sc = new Scanner(System.in);

        while (id == null) {
            System.out.println("Enter employee id");
            String idd = sc.next();
            try {
                id = Long.parseLong(idd);
            } catch (Exception e) {
                System.out.println("Invalid input");
                id = null;
            }
        }

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
        } while (joinedDate == null || joinedDate.isEmpty() || !validateDate(joinedDate));

        LocalDate joinDate = LocalDate.parse(joinedDate);
        sc.close();

        Employee employee = new Employee(id, firstName, lastName, joinDate);
        System.out.println(employee);

        employeeRepository.create(employee);
        employeeRepository.findAll().stream().forEach(x -> System.out.println(x));
    }

    public static boolean validateDate(String joinedDate) {

        String p = "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$";
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(joinedDate);
        if (matcher.matches()) {
            System.out.println(joinedDate + "is valid date format");
            return true;
        } //        if (Pattern.matches(joinedDate, p)) {
        //            LocalDate.parse(joinedDate);
        //            System.out.println(joinedDate + " is valid date format");
        //            return true;
        else {
            System.out.println(joinedDate + "is invalid date format");
            return false;
        }
    }
}
