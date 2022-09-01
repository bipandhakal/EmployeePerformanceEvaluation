package com.syntech.controller;

import static com.syntech.model.CalculatedBy.SELF;
import com.syntech.model.Criteria;
import com.syntech.model.CriteriaSelf;
import com.syntech.model.Employee;
import com.syntech.model.Months;
import com.syntech.repository.CriteriaRepository;
import com.syntech.repository.CriteriaSelfRepository;
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
public class CriteriaSelfController implements Serializable {

    private CriteriaSelf criteriaSelf;

    private List<CriteriaSelf> criteriaSelfList;

    private LazyDataModel<CriteriaSelf> lazyModel;

    @Inject
    private CriteriaSelfRepository criteriaSelfRepository;

    @Inject
    private CriteriaRepository criteriaRepository;

    @Inject
    private MessageUtil messageUtil;

    @Inject
    private EmployeeRepository employeeRepository;

    @Inject
    private MonthsRepository monthsRepository;

    public CriteriaSelf getCriteriaSelf() {
        return criteriaSelf;
    }

    public void setCriteriaSelf(CriteriaSelf criteriaSelf) {
        this.criteriaSelf = criteriaSelf;
    }

    public List<CriteriaSelf> getCriteriaSelfList() {
        return criteriaSelfList;
    }

    public void setCriteriaSelfList(List<CriteriaSelf> criteriaSelfList) {
        this.criteriaSelfList = criteriaSelfList;
    }

    public LazyDataModel<CriteriaSelf> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<CriteriaSelf> lazyModel) {
        this.lazyModel = lazyModel;
    }

    @PostConstruct
    public void init() {
        this.criteriaSelf = new CriteriaSelf();
        this.lazyModel = new LazyDataModel(criteriaSelfRepository);
    }

    public List<Criteria> getCriteriaDetails() {
        return criteriaRepository.findAll().stream().filter(x -> x.getCalculatedBy().equals(SELF))
                .collect(Collectors.toList());
    }

    public void beforeCreate() {
        this.criteriaSelf = new CriteriaSelf();
    }

    public void create() {
        criteriaSelfRepository.create(criteriaSelf);
        this.criteriaSelfList = criteriaSelfRepository.findAll();
        messageUtil.showInfo("CriteriaSelf Record Created Successfully!");
    }

    public void beforeEdit(CriteriaSelf crts) {
        this.criteriaSelf = criteriaSelfRepository.findById(crts.getId());
    }

    public void edit() {
        criteriaSelfRepository.edit(this.criteriaSelf);
        this.criteriaSelfList = criteriaSelfRepository.findAll();
        messageUtil.showInfo("CriteriaSelf Record Edited Successfully!");
    }

    public void findAll() {
        criteriaSelfRepository.findAll();
    }

    public void findById(Long id) {
        criteriaSelfRepository.findById(id);
    }

    public void delete(CriteriaSelf criteriaSelf) {
        criteriaSelfRepository.delete(criteriaSelf);
        this.criteriaSelfList = criteriaSelfRepository.findAll();
        messageUtil.showInfo("Criteria Self Removed");
    }

    public List<Employee> getEmployeeDetails() {
        return employeeRepository.findAll();
    }

    public List<Months> getMonthsDetails() {
        return monthsRepository.findAll();
    }

    public void saveIfNotInserted() {
        if (!criteriaSelfRepository.isAlreadyInserted(criteriaSelf.getEmployee(),
                criteriaSelf.getMonths(), criteriaSelf.getCriteria())) {
            criteriaSelfRepository.create(criteriaSelf);
            messageUtil.showInfo("Criteria Self Records Created Successfully !!!");
        } else {
            messageUtil.showInfo("Record is already inserted");
        }
    }
}
