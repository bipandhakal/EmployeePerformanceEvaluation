package com.syntech.repository;

import com.syntech.model.EmployeeAchievements;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 *
 * @author bipan
 */
public class EmployeeAchievementsRepositoryTest {

    EmployeeAchievementsRepository employeeAchievementsRepository = new EmployeeAchievementsRepository();

    @Test
    public void createTest() {
        EmployeeAchievements ea = new EmployeeAchievements(1L, 1L, 2L, "10");
        employeeAchievementsRepository.create(ea);
        assertEquals(1, employeeAchievementsRepository.findAll().size());
    }

    @Test
    public void findAllTest() {
        EmployeeAchievements ea = new EmployeeAchievements(1L, 1L, 2L, "Present");
        employeeAchievementsRepository.create(ea);
        assertEquals(1, employeeAchievementsRepository.findAll().size());
    }

    @Test
    public void deleteTest() {
        EmployeeAchievements ea = new EmployeeAchievements(1L, 1L, 2L, "20");
        employeeAchievementsRepository.create(ea);
        employeeAchievementsRepository.delete(ea);
        assertEquals(0, employeeAchievementsRepository.findAll().size());
    }

    @Test
    public void findByIdTest() {
        EmployeeAchievements ea = new EmployeeAchievements(2L, 3L, 2L, "Absent");
        employeeAchievementsRepository.create(ea);
        assertEquals(ea, employeeAchievementsRepository.findById(2l));
        assertNotEquals(ea, employeeAchievementsRepository.findById(3l));
    }

    @Test
    public void editTest() {
        EmployeeAchievements ea = new EmployeeAchievements(3L, 3L, 2L, "15");

        employeeAchievementsRepository.create(ea);
        ea.setAchievement("Present");
        employeeAchievementsRepository.edit(ea);
        EmployeeAchievements ea1 = employeeAchievementsRepository.findById(3L);
        assertEquals("Present", ea1.getAchievement());
    }
}
