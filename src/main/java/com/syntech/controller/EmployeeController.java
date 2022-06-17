package com.syntech.controller;

import com.syntech.model.Employee;
import com.syntech.practice.LazyEmployeeDataModel;
import com.syntech.repository.EmployeeRepository;
import com.syntech.util.MessageUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author bipan
 */
@ViewScoped
@Named
public class EmployeeController implements Serializable {

    private Employee employee;

    private LazyDataModel<Employee> lazyModel;

    private List<Employee> employeeList;

    @Inject
    private EmployeeRepository employeeRepository;

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

    public LazyDataModel<Employee> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Employee> lazyModel) {
        this.lazyModel = lazyModel;
    }

    @PostConstruct
    public void init() {
        this.employee = new Employee();
//        this.employeeList = employeeRepository.findAll();
        //    this.employeeList = employeeRepository.findByOffsetNPagesize(1, 5);
        //   System.out.println(employeeList.size());
        this.lazyModel = new LazyEmployeeDataModel(employeeRepository);
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
