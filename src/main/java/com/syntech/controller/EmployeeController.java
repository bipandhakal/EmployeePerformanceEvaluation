package com.syntech.controller;

import com.syntech.model.Employee;
import com.syntech.repository.EmployeeRepository;
import com.syntech.util.MessageUtil;
import com.syntech.util.ValidationUtil;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
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
public class EmployeeController implements Serializable {

    private Employee employee;

    private List<Employee> employeeList;

    @Inject
    private EmployeeRepository employeeRepository;

    @Inject
    private ValidationUtil validationUtil;

    @Inject
    private MessageUtil messageUtil;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @PostConstruct
    public void init() {
        this.employee = new Employee();
        this.employeeList = employeeRepository.findAll();
        System.out.println(employeeList.size());
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
//                    edit();
                    break;

                case "1.3":
                    //  delete(employee);
                    break;

                case "1.4":
                    findAll();
                    break;

                case "1.5":
//                    findById();
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

//        Long id = null;
//        String firstName = null;
//        String lastName = null;
//        String joinedDate = null;
//
//        Scanner sc = new Scanner(System.in);
//        while (!validationUtil.validatesLong(id)) {
//            System.out.println("Enter employee id");
//            id = sc.nextLong();
//        }
//
//        while (!validationUtil.validateString(firstName)) {
//            System.out.println("Enter employee firstname");
//            firstName = sc.next();
//        }
//
//        do {
//            System.out.println("Enter employee lastname");
//            lastName = sc.next();
//
//        } while (!validationUtil.validateString(lastName));
//
//        do {
//            System.out.println("Enter employee join-date");
//            joinedDate = sc.next();
//        } while (joinedDate == null || joinedDate.isEmpty() || !validationUtil.validateDate(joinedDate));
//
//        LocalDate joinDate = LocalDate.parse(joinedDate);
//        Employee employee = new Employee(id, firstName, lastName, joinDate);
//        employee.setJoinDate(new Date());
        employeeRepository.create(employee);
        employee = new Employee();
        this.employeeList = employeeRepository.findAll();
        messageUtil.showInfo("Employee Created Successfully!");
    }

    public void beforeEdit(Employee emp) {
        this.employee = employeeRepository.findById(emp.getId());
    }

    public void edit() {
        employeeRepository.edit(this.employee);
        messageUtil.showInfo("Employee Edited Successfully");
//        Long id = null;
//        String firstName = null;
//        String lastName = null;
//        String joinedDate = null;
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter employee id to edit");
//        id = sc.nextLong();
//
//        Employee employee = employeeRepository.findById(id);
//        if (employee == null) {
//            System.out.println("Employee with id: " + id + " not found");
//
//        } else {
//            while (firstName == null || firstName.isEmpty()) {
//                System.out.println("Enter employee firstname");
//                firstName = sc.next();
//            }
//
//            do {
//                System.out.println("Enter employee lastname");
//                lastName = sc.next();
//
//            } while (lastName == null || lastName.isEmpty());
//
//            do {
//                System.out.println("Enter employee join-date");
//                joinedDate = sc.next();
//            } while (joinedDate == null || joinedDate.isEmpty() || !validationUtil.validateDate(joinedDate));
//
//            LocalDate joinDate = LocalDate.parse(joinedDate);
//            Employee emp = new Employee(id, firstName, lastName, new Date());

    }

    public void delete(Employee employee) {

//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter employee id to delete");
//        Long id = sc.nextLong();
//
//        Employee employee = employeeRepository.findById(id);
//        if (employee == null) {
//            System.out.println("Employee with id: " + id + " not found");
//        } else {
//            employeeRepository.delete(employee);
//            System.out.println("Employee removed");
//        }
        employeeRepository.delete(employee);
        messageUtil.showInfo("Employee removed");
    }

    public void findAll() {
        employeeRepository.findAll();
//        Iterator<Employee> i = employeeRepository.findAll().iterator();
//        while (i.hasNext()) {
//            Employee employee = i.next();
//            System.out.println(employee);
//        }
    }

    public void findById(Long id) {
        employeeRepository.findById(id);
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter employee id to find");
//        Long id = sc.nextLong();
//
//        Employee employee = employeeRepository.findById(id);
//        if (employee == null) {
//            System.out.println("Employee with id: " + id + " not found");
//        } else {
//            System.out.println(employee);
//        }
    }
}
