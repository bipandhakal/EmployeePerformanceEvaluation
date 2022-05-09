package com.syntech.controller;

import com.syntech.model.Employee;
import com.syntech.repository.EmployeeRepository;
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

    public void beforeCreate() {
        this.employee = new Employee();
    }

    public void create() {
        employeeRepository.create(employee);
        this.employeeList = employeeRepository.findAll();
        messageUtil.showInfo("Employee Created Successfully!");
    }

    public void beforeEdit(Employee emp) {
        this.employee = employeeRepository.findById(emp.getId());
    }

    public void edit() {
        employeeRepository.edit(this.employee);
        this.employeeList = employeeRepository.findAll();
        messageUtil.showInfo("Employee Edited Successfully");
    }

    public void delete(Employee employee) {
        employeeRepository.delete(employee);
        this.employeeList = employeeRepository.findAll();
        messageUtil.showInfo("Employee removed");
    }

    public void findAll() {
        employeeRepository.findAll();
    }

    public void findById(Long id) {
        employeeRepository.findById(id);
    }
}
