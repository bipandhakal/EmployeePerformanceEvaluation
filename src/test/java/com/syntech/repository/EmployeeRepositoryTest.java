package com.syntech.repository;

import com.syntech.model.EmployeeEntity;
import java.time.LocalDate;
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
        EmployeeEntity e1 = new EmployeeEntity(1L, "Bipan", "Dhakal", LocalDate.of(2020, 1, 8));
        employeeRepository.create(e1);
        assertEquals(1, employeeRepository.findAll().size());
    }

    @Test
    public void findAllTest() {
        EmployeeEntity e2 = new EmployeeEntity(2L, "Sanjeev", "Shrestha", LocalDate.of(2020, 2, 8));
        employeeRepository.create(e2);
        assertEquals(1, employeeRepository.findAll().size());
    }

    @Test
    public void deleteTest() {
        EmployeeEntity e3 = new EmployeeEntity(3L, "Ram", "Shrestha", LocalDate.of(2022, 3, 8));
        employeeRepository.create(e3);
        employeeRepository.delete(e3);
        assertEquals(0, employeeRepository.findAll().size());
    }

    @Test
    public void findByIdTest() {
        EmployeeEntity e4 = new EmployeeEntity(4L, "Shyam", "Shrestha", LocalDate.of(2019, 3, 8));
        employeeRepository.create(e4);
        assertEquals(e4, employeeRepository.findById(4l));
        assertNotEquals(e4, employeeRepository.findById(3l));
    }

    @Test
    public void editTest() {
        EmployeeEntity e5 = new EmployeeEntity(5L, "Shyam", "Sharma", LocalDate.of(2018, 3, 8));
        employeeRepository.create(e5);
        e5.setFirstName("Bipan");
        employeeRepository.edit(e5);
        EmployeeEntity e6 = employeeRepository.findById(5L);
        assertEquals("Bipan", e6.getFirstName());
    }
}
