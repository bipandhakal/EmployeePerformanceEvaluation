package com.syntech.controller;

import com.syntech.model.Criteria;
import com.syntech.repository.CriteriaRepository;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author bipan
 */
@RequestScoped
@FacesConverter(value = "criteriaConverter", forClass = Criteria.class)
public class CriteriaConverter implements Converter {

    @Inject
    CriteriaRepository criteriaRepository;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value == null || value.isEmpty() || value.length() == 0 || value.equals("")) {
            return null;
        }
        return criteriaRepository.findById(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null || o.equals("")) {
            return "";
        }
        return ((Criteria) o).getId().toString();
    }
}
