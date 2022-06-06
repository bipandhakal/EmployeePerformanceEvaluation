package com.syntech.converter;

import com.syntech.model.Months;
import com.syntech.repository.MonthsRepository;
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
@FacesConverter(value = "monthsConverter", forClass = Months.class)
public class MonthsConverter implements Converter {

    @Inject
    MonthsRepository monthsRepository;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value == null || value.isEmpty() || value.length() == 0 || value.equals("")) {
            return null;
        }
        return monthsRepository.findById(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null || o.equals("")) {
            return "";
        }
        return ((Months) o).getId().toString();
    }
}
