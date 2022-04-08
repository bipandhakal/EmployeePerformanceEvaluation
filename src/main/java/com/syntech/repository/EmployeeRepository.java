package com.syntech.repository;

import com.syntech.model.Employee;

/**
 *
 * @author bipan
 */
public class EmployeeRepository extends AbstractRepository<Employee> {

    @Override
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
        super.findAll().stream().filter(x -> x.getId().equals(emp.getId()))
                .forEach(eee -> {
                    eee.setFirstName(emp.getFirstName());
                    eee.setLastName(emp.getLastName());
                    eee.setJoinDate(emp.getJoinDate());
                });
    }
}
