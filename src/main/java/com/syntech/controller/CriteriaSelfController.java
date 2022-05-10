package com.syntech.controller;

import static com.syntech.model.CalculatedBy.SELF;
import com.syntech.model.Criteria;
import com.syntech.model.CriteriaSelf;
import com.syntech.repository.CriteriaRepository;
import com.syntech.repository.CriteriaSelfRepository;
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

    @Inject
    private CriteriaSelfRepository criteriaSelfRepository;

    @Inject
    private CriteriaRepository criteriaRepository;

    @Inject
    private MessageUtil messageUtil;

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

    @PostConstruct
    public void init() {
        this.criteriaSelf = new CriteriaSelf();
        this.criteriaSelfList = criteriaSelfRepository.findAll();
        System.out.println(criteriaSelfList.size());
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
        messageUtil.showInfo("CriteriaSelf Created Successfully!");
    }

    public void beforeEdit(CriteriaSelf crts) {
        this.criteriaSelf = criteriaSelfRepository.findById(crts.getId());
    }

    public void edit() {
        criteriaSelfRepository.edit(this.criteriaSelf);
        this.criteriaSelfList = criteriaSelfRepository.findAll();
        messageUtil.showInfo("CriteriaSelf Edited Successfully!");
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
}
