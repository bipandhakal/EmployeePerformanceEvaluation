package com.syntech.controller;

import com.syntech.model.Employee;
import com.syntech.repository.EmployeeRepository;
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
@FacesConverter(value = "employeeConverter", forClass = Employee.class)
public class EmployeeConverter implements Converter {

    @Inject
    EmployeeRepository employeeRepository;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value == null || value.isEmpty() || value.length() == 0 || value.equals("")) {
            return null;
        }
        return employeeRepository.findById(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null || o.equals("")) {
            return "";
        }
        return ((Employee) o).getId().toString();
    }
}
