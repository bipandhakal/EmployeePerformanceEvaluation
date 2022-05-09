package com.syntech.controller;

import com.syntech.model.CalculatedBy;
import com.syntech.model.Category;
import com.syntech.model.Criteria;
import com.syntech.repository.CategoryRepository;
import com.syntech.repository.CriteriaRepository;
import com.syntech.util.MessageUtil;
import com.syntech.util.ValidationUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author bipan
 */
@ViewScoped
@Named
public class CriteriaController implements Serializable {

    private Criteria criteria;

    private List<Criteria> criteriaList;

    @Inject
    private CriteriaRepository criteriaRepository;

    @Inject
    private CategoryRepository categoryRepository;

    @Inject
    private ValidationUtil validationUtil;

    @Inject
    private MessageUtil messageUtil;

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public List<Criteria> getCriteriaList() {
        return criteriaList;
    }

    public void setCriteriaList(List<Criteria> criteriaList) {
        this.criteriaList = criteriaList;
    }

    @PostConstruct
    public void init() {
        this.criteria = new Criteria();
        this.criteriaList = criteriaRepository.findAll();
        System.out.println(criteriaList.size());
    }

    public SelectItem[] getCalculatedByEnumOptions() {
        SelectItem[] items = new SelectItem[CalculatedBy.values().length];
        for (int i = 0; i < CalculatedBy.values().length; i++) {
            items[i] = new SelectItem(CalculatedBy.values()[i], CalculatedBy.values()[i].name());
        }
        return items;
    }

    public List<Category> getCategoryDetails() {
        return categoryRepository.findAll();
    }

    public void beforeCreate() {
        this.criteria = new Criteria();
    }

    public void create() {
        criteriaRepository.create(criteria);
        this.criteriaList = criteriaRepository.findAll();
        messageUtil.showInfo("Criteria Created Successfully!");
    }

    public void beforeEdit(Criteria crt) {
        this.criteria = criteriaRepository.findById(crt.getId());
    }

    public void edit() {
        criteriaRepository.edit(this.criteria);
        this.criteriaList = criteriaRepository.findAll();
        messageUtil.showInfo("Criteria Edited Successfully");
    }

    public void findAll() {
        criteriaRepository.findAll();
    }

    public void findById(Long id) {
        criteriaRepository.findById(id);
    }

    public void delete(Criteria criteria) {
        criteriaRepository.delete(criteria);
        this.criteriaList = criteriaRepository.findAll();
        messageUtil.showInfo("Criteria Removed");
    }
}
