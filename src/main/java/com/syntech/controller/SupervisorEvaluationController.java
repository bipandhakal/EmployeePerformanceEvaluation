package com.syntech.controller;

import com.syntech.model.Criteria;
import com.syntech.model.Employee;
import com.syntech.model.Months;
import com.syntech.model.SupervisorEvaluation;
import com.syntech.repository.CriteriaRepository;
import com.syntech.repository.CriteriaSelfRepository;
import com.syntech.repository.EmployeeAchievementsRepository;
import com.syntech.repository.EmployeeRepository;
import com.syntech.repository.ExcelFileImplementation;
import com.syntech.repository.MonthsRepository;
import com.syntech.repository.SupervisorEvaluationRepository;
import com.syntech.util.MessageUtil;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
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
    private EmployeeAchievementsRepository employeeAchievementsRepository;

    @Inject
    private CriteriaSelfRepository criteriaSelfRepository;

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
        messageUtil.showInfo("Evaluation Record Created Successfully !!!");
    }

    public void beforeEdit(SupervisorEvaluation supervisorEvaluation) {
        this.supervisorEvaluation = supervisorEvaluationRepository.findById(supervisorEvaluation.getId());
    }

    public void edit() {
        supervisorEvaluationRepository.edit(this.supervisorEvaluation);
        messageUtil.showInfo("Evaluation Record Updated Successfully !!!");
    }

    public void delete(SupervisorEvaluation supervisorEvaluation) {
        supervisorEvaluationRepository.delete(supervisorEvaluation);
        this.supervisorEvaluationList = supervisorEvaluationRepository.findAll();
        messageUtil.showInfo("Evaluation Record Deleted Successfully !!!");
    }

    private List<SupervisorEvaluation> sEvaluations;

    public void handleExcelFileUpload(FileUploadEvent event) {
        this.sEvaluations = new ArrayList<>();
        try {
            InputStream file = event.getFile().getInputStream();
            sEvaluations = excelFileImplementation.processExcelFile(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUploadedSupervisorEvaluationList() {
        this.sEvaluations.stream()
                .filter(x -> !supervisorEvaluationRepository.isAlreadyInserted(x.getEmployee(), x.getMonths(), x.getCriteria()))
                .forEach(x -> supervisorEvaluationRepository.create(x));
    }

    public void saveIfNotInserted() {
        if (employeeAchievementsRepository.isAlreadyInserted(supervisorEvaluation.getEmployee(),
                supervisorEvaluation.getMonths(), supervisorEvaluation.getCriteria())
                || criteriaSelfRepository.isAlreadyInserted(supervisorEvaluation.getEmployee(),
                        supervisorEvaluation.getMonths(), supervisorEvaluation.getCriteria())) {
            if (!supervisorEvaluationRepository.isAlreadyInserted(supervisorEvaluation.getEmployee(),
                    supervisorEvaluation.getMonths(), supervisorEvaluation.getCriteria())) {
                supervisorEvaluationRepository.create(supervisorEvaluation);
                messageUtil.showInfo("Evaluation Record Created Successfully !!!");
            } else {
                messageUtil.showError("Record is already inserted !!!");
            }
        } else {
            messageUtil.showError("Employee achievements record / Criteria Self record not found !!!");
        }
    }
}
