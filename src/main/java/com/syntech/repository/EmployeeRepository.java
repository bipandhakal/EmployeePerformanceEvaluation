package com.syntech.repository;

import com.syntech.model.Employee;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bipan
 */
public class EmployeeRepository {

    private List<Employee> employeeList;

    public EmployeeRepository() {
        employeeList = new ArrayList<>();
    }

    public void create(Employee e) {
        this.employeeList.add(e);
    }

    public List<Employee> findAll() {
        return employeeList;
    }

    public Employee findById(Long id) {
        for (Employee e : employeeList) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

}
