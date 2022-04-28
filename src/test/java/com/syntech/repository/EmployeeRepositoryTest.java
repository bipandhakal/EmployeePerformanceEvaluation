package com.syntech.repository;

import com.syntech.model.Employee;
import java.time.LocalDate;
import java.util.Date;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 *
 * @author bipan
 */
public class EmployeeRepositoryTest {

    EmployeeRepository employeeRepository = new EmployeeRepository();

    @Test
    public void createTest() {
        Employee e1 = new Employee(1L, "Bipan", "Dhakal", new Date(2020, 1, 8));
        employeeRepository.create(e1);
        assertEquals(1, employeeRepository.findAll().size());
    }

    @Test
    public void findAllTest() {
        Employee e2 = new Employee(2L, "Sanjeev", "Shrestha", new Date(2020, 2, 8));
        employeeRepository.create(e2);
        assertEquals(1, employeeRepository.findAll().size());
    }

    @Test
    public void deleteTest() {
        Employee e3 = new Employee(3L, "Ram", "Shrestha", new Date(2022, 3, 8));
        employeeRepository.create(e3);
        employeeRepository.delete(e3);
        assertEquals(0, employeeRepository.findAll().size());
    }

    @Test
    public void findByIdTest() {
        Employee e4 = new Employee(4L, "Shyam", "Shrestha", new Date(2019, 3, 8));
        employeeRepository.create(e4);
        assertEquals(e4, employeeRepository.findById(4l));
        assertNotEquals(e4, employeeRepository.findById(3l));
    }

    @Test
    public void editTest() {
        Employee e5 = new Employee(5L, "Shyam", "Sharma", new Date(2018, 3, 8));
        employeeRepository.create(e5);
        e5.setFirstName("Bipan");
        employeeRepository.edit(e5);
        Employee e6 = employeeRepository.findById(5L);
        assertEquals("Bipan", e6.getFirstName());
    }
}
