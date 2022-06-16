//package com.syntech.practice;
//
//import com.syntech.controller.EmployeeController;
//import com.syntech.model.Employee;
//import java.io.Serializable;
//import javax.annotation.PostConstruct;
//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;
//import javax.faces.view.ViewScoped;
//import javax.inject.Inject;
//import javax.inject.Named;
//import org.primefaces.event.SelectEvent;
//import org.primefaces.model.LazyDataModel;
//
///**
// *
// * @author bipan
// */
//@Named
//@ViewScoped
//public class LazyView implements Serializable {
//
//    private LazyDataModel<Employee> lazyModel;
//
//    private Employee selectedEmployee;
//
//    @Inject
//    private EmployeeController employeeController;
//
//    @PostConstruct
//    public void init() {
//        lazyModel = new LazyEmployeeDataModel(employeeController.getEmployeeList());
//    }
//
//    public LazyDataModel<Employee> getLazyModel() {
//        return lazyModel;
//    }
//
//    public Employee getSelectedEmployee() {
//        return selectedEmployee;
//    }
//
//    public void setSelectedEmployee(Employee selectedEmployee) {
//        this.selectedEmployee = selectedEmployee;
//    }
//
//    public void setEmployeeController(EmployeeController employeeController) {
//        this.employeeController = employeeController;
//    }
//
//    public void onRowSelect(SelectEvent<Employee> event) {
//        FacesMessage msg = new FacesMessage("Employee Selected", String.valueOf(event.getObject().getId()));
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
//}
