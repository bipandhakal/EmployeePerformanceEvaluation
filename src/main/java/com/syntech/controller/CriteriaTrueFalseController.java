package com.syntech.controller;

import static com.syntech.model.CalculatedBy.TRUEORFALSE;
import com.syntech.model.Criteria;
import com.syntech.model.CriteriaTrueFalse;
import com.syntech.repository.CriteriaRepository;
import com.syntech.repository.CriteriaTrueFalseRepository;
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
public class CriteriaTrueFalseController implements Serializable {

    private CriteriaTrueFalse criteriaTrueFalse;

    private List<CriteriaTrueFalse> criteriaTrueFalseList;

    @Inject
    private CriteriaTrueFalseRepository criteriaTrueFalseRepository;

    @Inject
    private CriteriaRepository criteriaRepository;

    @Inject
    private MessageUtil messageUtil;

    public CriteriaTrueFalse getCriteriaTrueFalse() {
        return criteriaTrueFalse;
    }

    public void setCriteriaTrueFalse(CriteriaTrueFalse criteriaTrueFalse) {
        this.criteriaTrueFalse = criteriaTrueFalse;
    }

    public List<CriteriaTrueFalse> getCriteriaTrueFalseList() {
        return criteriaTrueFalseList;
    }

    public void setCriteriaTrueFalseList(List<CriteriaTrueFalse> criteriaTrueFalseList) {
        this.criteriaTrueFalseList = criteriaTrueFalseList;
    }

    @PostConstruct
    public void init() {
        this.criteriaTrueFalse = new CriteriaTrueFalse();
        this.criteriaTrueFalseList = criteriaTrueFalseRepository.findAll();
        System.out.println(criteriaTrueFalseList.size());
    }

    public List<Criteria> getCriteriaDetails() {
        return criteriaRepository.findAll().stream().filter(x -> x.getCalculatedBy().equals(TRUEORFALSE))
                .collect(Collectors.toList());
    }

    public void beforeCreate() {
        this.criteriaTrueFalse = new CriteriaTrueFalse();
    }

    public void create() {
        criteriaTrueFalseRepository.create(criteriaTrueFalse);
        this.criteriaTrueFalseList = criteriaTrueFalseRepository.findAll();
        messageUtil.showInfo("CriteriaTrueFalse Created Successfully!");
    }

    public void beforeEdit(CriteriaTrueFalse criteriaTrueFalse) {
        this.criteriaTrueFalse = criteriaTrueFalseRepository.findById(criteriaTrueFalse.getId());
    }

    public void edit() {
        criteriaTrueFalseRepository.edit(this.criteriaTrueFalse);
        this.criteriaTrueFalseList = criteriaTrueFalseRepository.findAll();
        messageUtil.showInfo("CriteriaTrueFalse Edited Successfully!");
    }

    public void findAll() {
        criteriaTrueFalseRepository.findAll();
    }

    public void findById(Long id) {
        criteriaTrueFalseRepository.findById(id);
    }

    public void delete(CriteriaTrueFalse criteriaTrueFalse) {
        criteriaTrueFalseRepository.delete(criteriaTrueFalse);
        this.criteriaTrueFalseList = criteriaTrueFalseRepository.findAll();
        messageUtil.showInfo("CriteriaTrueFalse Deleted Successfully!");
    }
}
