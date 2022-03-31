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

    public void delete(Employee employee) {
        this.employeeList.remove(employee);
    }

    public void edit(Employee emp) {

//        Employee e = findById(emp.getId());
//        employeeList.remove(e);
//        employeeList.add(emp);
//        for (Employee eee : employeeList) {
//            if (eee.getId().equals(emp.getId())) {
//                eee.setFirstName(emp.getFirstName());
//                eee.setLastName(emp.getLastName());
//                eee.setJoinDate(emp.getJoinDate());
//                break;
//            }
//        }
        employeeList.stream().filter(x -> x.getId().equals(emp.getId()))
                .forEach(eee -> {
                    eee.setFirstName(emp.getFirstName());
                    eee.setLastName(emp.getLastName());
                    eee.setJoinDate(emp.getJoinDate());
                });
    }
}
