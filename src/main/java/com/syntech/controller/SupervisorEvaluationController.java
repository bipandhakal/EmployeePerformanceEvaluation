package com.syntech.controller;

import com.syntech.model.Criteria;
import com.syntech.model.Employee;
import com.syntech.model.Months;
import com.syntech.model.SupervisorEvaluation;
import com.syntech.repository.CriteriaRepository;
import com.syntech.repository.EmployeeRepository;
import com.syntech.repository.ExcelFileImplementation;
import com.syntech.repository.MonthsRepository;
import com.syntech.repository.SupervisorEvaluationRepository;
import com.syntech.util.MessageUtil;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author bipan
 */
@ViewScoped
@Named
public class SupervisorEvaluationController implements Serializable {

    private SupervisorEvaluation supervisorEvaluation;

    private List<SupervisorEvaluation> supervisorEvaluationList;

    private LazyDataModel<SupervisorEvaluation> lazyModel;

    @Inject
    private SupervisorEvaluationRepository supervisorEvaluationRepository;

    @Inject
    private CriteriaRepository criteriaRepository;

    @Inject
    private EmployeeRepository employeeRepository;

    @Inject
    private MonthsRepository monthsRepository;

    @Inject
    private MessageUtil messageUtil;

    @Inject
    private ExcelFileImplementation excelFileImplementation;

    public SupervisorEvaluation getSupervisorEvaluation() {
        return supervisorEvaluation;
    }

    public void setSupervisorEvaluation(SupervisorEvaluation supervisorEvaluation) {
        this.supervisorEvaluation = supervisorEvaluation;
    }

    public List<SupervisorEvaluation> getSupervisorEvaluationList() {
        return supervisorEvaluationList;
    }

    public void setSupervisorEvaluationList(List<SupervisorEvaluation> supervisorEvaluationList) {
        this.supervisorEvaluationList = supervisorEvaluationList;
    }

    public LazyDataModel<SupervisorEvaluation> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<SupervisorEvaluation> lazyModel) {
        this.lazyModel = lazyModel;
    }

    @PostConstruct
    public void init() {
        this.supervisorEvaluation = new SupervisorEvaluation();
        this.lazyModel = new LazyDataModel(supervisorEvaluationRepository);
//        this.supervisorEvaluationList = supervisorEvaluationRepository.findAll();
    }

    public List<Employee> getEmployeeDetails() {
        return employeeRepository.findAll();
    }

    public List<Criteria> getCriteriaDetails() {
        return criteriaRepository.findAll();
    }

    public List<Months> getMonthsDetails() {
        return monthsRepository.findAll();
    }

    public void beforeCreate() {
        this.supervisorEvaluation = new SupervisorEvaluation();
    }

    public void create() {
        supervisorEvaluationRepository.create(supervisorEvaluation);
        this.supervisorEvaluationList = supervisorEvaluationRepository.findAll();
        messageUtil.showInfo("Supervisor Evaluation Created Successfully!");
    }

    public void beforeEdit(SupervisorEvaluation supervisorEvaluation) {
        this.supervisorEvaluation = supervisorEvaluationRepository.findById(supervisorEvaluation.getId());
    }

    public void edit() {
        supervisorEvaluationRepository.edit(this.supervisorEvaluation);
        this.supervisorEvaluationList = supervisorEvaluationRepository.findAll();
        messageUtil.showInfo("Supervisor Evaluation Updated Successfully!");
    }

    public void delete(SupervisorEvaluation supervisorEvaluation) {
        supervisorEvaluationRepository.delete(supervisorEvaluation);
        this.supervisorEvaluationList = supervisorEvaluationRepository.findAll();
        messageUtil.showInfo("Supervisor Evaluation Deleted Successfully!");
    }

    public void handleExcelFileUpload(FileUploadEvent event) {
        try {
            InputStream file = event.getFile().getInputStream();
            List<SupervisorEvaluation> sEvaluationList = excelFileImplementation.processExcelFile(file);

            sEvaluationList.stream()
                    .filter(x -> !supervisorEvaluationRepository.isAlreadyInserted(x.getEmployee(), x.getMonths(), x.getCriteria()))
                    .forEach(x -> supervisorEvaluationRepository.create(x));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
