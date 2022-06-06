package com.syntech.controller;

import com.syntech.model.Months;
import com.syntech.repository.MonthsRepository;
import com.syntech.util.MessageUtil;
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
public class MonthsController implements Serializable {

    private Months months;

    private List<Months> monthsList;

    @Inject
    private MonthsRepository monthsRepository;

    @Inject
    private MessageUtil messageUtil;

    public Months getMonths() {
        return months;
    }

    public void setMonths(Months months) {
        this.months = months;
    }

    public List<Months> getMonthsList() {
        return monthsList;
    }

    public void setMonthsList(List<Months> monthsList) {
        this.monthsList = monthsList;
    }

    @PostConstruct
    public void init() {
        this.months = new Months();
        this.monthsList = monthsRepository.findAll();
        System.out.println(monthsList.size());
    }

    public void beforeCreate() {
        this.months = new Months();
    }

    public void create() {
        monthsRepository.create(months);
        this.monthsList = monthsRepository.findAll();
        messageUtil.showInfo("Months Created Successfully!");
    }

    public void beforeEdit(Months m) {
        this.months = monthsRepository.findById(m.getId());
    }

    public void edit() {
        monthsRepository.edit(this.months);
        this.monthsList = monthsRepository.findAll();
        messageUtil.showInfo("Months Edited Successfully");
    }

    public void delete(Months months) {
        monthsRepository.delete(months);
        this.monthsList = monthsRepository.findAll();
        messageUtil.showInfo("Months removed");
    }

    public void findAll() {
        monthsRepository.findAll();
    }

    public void findById(Long id) {
        monthsRepository.findById(id);
    }
}
