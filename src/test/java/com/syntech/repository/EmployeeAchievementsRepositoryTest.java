package com.syntech.repository;

import com.syntech.model.CalculatedBy;
import com.syntech.model.Category;
import com.syntech.model.Criteria;
import com.syntech.model.Employee;
import com.syntech.model.EmployeeAchievements;
import com.syntech.model.Months;
import java.math.BigDecimal;
import java.util.Date;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 *
 * @author bipan
 */
public class EmployeeAchievementsRepositoryTest {

    EmployeeAchievementsRepository employeeAchievementsRepository = new EmployeeAchievementsRepository();
    Category category = new Category(1L, "Task", 35d);
    Months months = new Months(1L, "January", 1);
    Employee employee = new Employee(1L, "Bipan", "Dhakal", new Date(2022 - 04 - 02), "9845676438", "bipan.dhakal@syntechnepal.com");
    Criteria criteria = new Criteria(1L, category, "Number of completed task", 25d, BigDecimal.valueOf(20.0), CalculatedBy.RANGE);

    @Test
    public void createTest() {
        EmployeeAchievements ea = new EmployeeAchievements(1L, employee, criteria, months, "10");
        employeeAchievementsRepository.create(ea);
        assertEquals(1, employeeAchievementsRepository.findAll().size());
    }

    @Test
    public void findAllTest() {
        EmployeeAchievements ea = new EmployeeAchievements(1L, employee, criteria, months, "10");

        employeeAchievementsRepository.create(ea);
        assertEquals(1, employeeAchievementsRepository.findAll().size());
    }

    @Test
    public void deleteTest() {
        EmployeeAchievements ea = new EmployeeAchievements(1L, employee, criteria, months, "10");

        employeeAchievementsRepository.create(ea);
        employeeAchievementsRepository.delete(ea);
        assertEquals(0, employeeAchievementsRepository.findAll().size());
    }

    @Test
    public void findByIdTest() {
        EmployeeAchievements ea = new EmployeeAchievements(1L, employee, criteria, months, "10");

        employeeAchievementsRepository.create(ea);
        assertEquals(ea, employeeAchievementsRepository.findById(1l));
        assertNotEquals(ea, employeeAchievementsRepository.findById(3l));
    }

    @Test
    public void editTest() {
        EmployeeAchievements ea = new EmployeeAchievements(1L, employee, criteria, months, "10");
        employeeAchievementsRepository.create(ea);
        ea.setAchievement("15");
        employeeAchievementsRepository.edit(ea);
        EmployeeAchievements ea1 = employeeAchievementsRepository.findById(1L);
        assertEquals("15", ea1.getAchievement());
    }
}
