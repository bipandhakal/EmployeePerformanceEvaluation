package com.syntech.controller;

import static com.syntech.model.CalculatedBy.RANGE;
import com.syntech.model.Criteria;
import com.syntech.model.CriteriaRange;
import com.syntech.repository.CriteriaRangeRepository;
import com.syntech.repository.CriteriaRepository;
import com.syntech.util.MessageUtil;
import com.syntech.util.ValidationUtil;
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
public class CriteriaRangeController implements Serializable {

    private CriteriaRange criteriaRange;

    private List<CriteriaRange> criteriaRangeList;

    @Inject
    private CriteriaRangeRepository criteriaRangeRepository;

    @Inject
    private CriteriaRepository criteriaRepository;

    @Inject
    private ValidationUtil validationUtil;

    @Inject
    private MessageUtil messageUtil;

    public CriteriaRange getCriteriaRange() {
        return criteriaRange;
    }

    public void setCriteriaRange(CriteriaRange criteriaRange) {
        this.criteriaRange = criteriaRange;
    }

    public List<CriteriaRange> getCriteriaRangeList() {
        return criteriaRangeList;
    }

    public void setCriteriaRangeList(List<CriteriaRange> criteriaRangeList) {
        this.criteriaRangeList = criteriaRangeList;
    }

    @PostConstruct
    public void init() {
        this.criteriaRange = new CriteriaRange();
        this.criteriaRangeList = criteriaRangeRepository.findAll();
        System.out.println(criteriaRangeList.size());
    }

    public List<Criteria> getCriteriaDetails() {
        return criteriaRepository.findAll().stream().filter(x -> x.getCalculatedBy().equals(RANGE))
                .collect(Collectors.toList());
    }

    public void beforeCreate() {
        this.criteriaRange = new CriteriaRange();
    }

    public void create() {
        criteriaRangeRepository.create(criteriaRange);
        this.criteriaRangeList = criteriaRangeRepository.findAll();
        messageUtil.showInfo("CriteriaRange Created Successfully!");
    }

    public void beforeEdit(CriteriaRange crtr) {
        this.criteriaRange = criteriaRangeRepository.findById(crtr.getId());
    }

    public void edit() {
        criteriaRangeRepository.edit(this.criteriaRange);
        this.criteriaRangeList = criteriaRangeRepository.findAll();
        messageUtil.showInfo("CriteriaRange Edited Successfully!");
    }

    public void findAll() {
        criteriaRangeRepository.findAll();
    }

    public void findById(Long id) {
        criteriaRangeRepository.findById(id);
    }

    public void delete(CriteriaRange criteriaRange) {
        criteriaRangeRepository.delete(criteriaRange);
        this.criteriaRangeList = criteriaRangeRepository.findAll();
        messageUtil.showInfo("Criteria Range Removed");
    }
}
