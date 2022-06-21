package com.syntech.controller;

import static com.syntech.model.CalculatedBy.SELF;
import com.syntech.model.Criteria;
import com.syntech.model.Employee;
import com.syntech.model.EmployeeAchievements;
import com.syntech.model.Months;
import com.syntech.repository.CriteriaRepository;
import com.syntech.repository.EmployeeAchievementsRepository;
import com.syntech.repository.EmployeeRepository;
import com.syntech.repository.MonthsRepository;
import com.syntech.util.MessageUtil;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
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
public class EmployeeAchievementsController implements Serializable {

    private EmployeeAchievements employeeAchievements;

    private List<EmployeeAchievements> employeeAchievementsList;

    private LazyDataModel<EmployeeAchievements> lazyModel;

    @Inject
    private EmployeeAchievementsRepository employeeAchievementsRepository;

    @Inject
    private CriteriaRepository criteriaRepository;

    @Inject
    private EmployeeRepository employeeRepository;

    @Inject
    private MonthsRepository monthsRepository;

    @Inject
    private MessageUtil messageUtil;

    public EmployeeAchievements getEmployeeAchievements() {
        return employeeAchievements;
    }

    public void setEmployeeAchievements(EmployeeAchievements employeeAchievements) {
        this.employeeAchievements = employeeAchievements;
    }

    public List<EmployeeAchievements> getEmployeeAchievementsList() {
        return employeeAchievementsList;
    }

    public void setEmployeeAchievementsList(List<EmployeeAchievements> employeeAchievementsList) {
        this.employeeAchievementsList = employeeAchievementsList;
    }

    public LazyDataModel<EmployeeAchievements> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<EmployeeAchievements> lazyModel) {
        this.lazyModel = lazyModel;
    }

    @PostConstruct
    public void init() {
        this.employeeAchievements = new EmployeeAchievements();
        this.lazyModel = new LazyDataModel(employeeAchievementsRepository);
    }

    public List<Employee> getEmployeeDetails() {
        return employeeRepository.findAll();
    }

    public List<Months> getMonthsDetails() {
        return monthsRepository.findAll();
    }

    public List<Criteria> getCriteriaDetails() {
        return criteriaRepository.findAll().stream()
                .filter(x -> !x.getCalculatedBy().equals(SELF))
                .collect(Collectors.toList());
    }

    public void beforeCreate() {
        this.employeeAchievements = new EmployeeAchievements();
    }

    public void create() {
        employeeAchievementsRepository.create(employeeAchievements);
        this.employeeAchievementsList = employeeAchievementsRepository.findAll();
        messageUtil.showInfo("Employee Achievements Created Successfully!");
    }

    public void beforeEdit(EmployeeAchievements employeeAchievements) {
        this.employeeAchievements = employeeAchievementsRepository.findById(employeeAchievements.getId());
    }

    public void edit() {
        employeeAchievementsRepository.edit(this.employeeAchievements);
        this.employeeAchievementsList = employeeAchievementsRepository.findAll();
        messageUtil.showInfo("Employee Achievements Updated Successfully!");
    }

    public void delete(EmployeeAchievements employeeAchievements) {
        employeeAchievementsRepository.delete(employeeAchievements);
        this.employeeAchievementsList = employeeAchievementsRepository.findAll();
        messageUtil.showInfo("Employee Achievements Deleted Successfully!");
    }

    public void findAll() {
        employeeAchievementsRepository.findAll();
    }

    public void findById(Long id) {
        employeeAchievementsRepository.findById(id);
    }
}
