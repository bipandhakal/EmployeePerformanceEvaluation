package com.syntech.repository;

import com.syntech.model.CalculatedBy;
import com.syntech.model.Category;
import com.syntech.model.Criteria;
import com.syntech.model.Employee;
import com.syntech.model.Months;
import com.syntech.model.SupervisorEvaluation;
import java.math.BigDecimal;
import java.util.Date;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 *
 * @author bipan
 */
public class SupervisorEvaluationRepositoryTest {

    SupervisorEvaluationRepository supervisorEvaluationRepository = new SupervisorEvaluationRepository();
    Category category = new Category(1L, "Task", 35d);
    Months months = new Months(1L, "January", 1);
    Employee employee = new Employee(1L, "Bipan", "Dhakal", new Date(2022 - 04 - 02), "9845676438", "bipan.dhakal@syntechnepal.com");
    Criteria criteria = new Criteria(1L, category, "Number of completed task", 25d, BigDecimal.valueOf(20.0), CalculatedBy.RANGE);

    @Test
    public void createTest() {
        SupervisorEvaluation supervisorEvaluation = new SupervisorEvaluation(1L, months, employee, criteria, +10.0);
        supervisorEvaluationRepository.create(supervisorEvaluation);
        assertEquals(1, supervisorEvaluationRepository.findAll().size());
    }

    @Test
    public void findAllTest() {
        SupervisorEvaluation supervisorEvaluation = new SupervisorEvaluation(1L, months, employee, criteria, +10.0);
        supervisorEvaluationRepository.create(supervisorEvaluation);
        assertEquals(1, supervisorEvaluationRepository.findAll().size());
    }

    @Test
    public void deleteTest() {
        SupervisorEvaluation supervisorEvaluation = new SupervisorEvaluation(1L, months, employee, criteria, +10.0);
        supervisorEvaluationRepository.create(supervisorEvaluation);
        supervisorEvaluationRepository.delete(supervisorEvaluation);
        assertEquals(0, supervisorEvaluationRepository.findAll().size());
    }

    @Test
    public void findByIdTest() {
        SupervisorEvaluation supervisorEvaluation = new SupervisorEvaluation(1L, months, employee, criteria, +10.0);
        supervisorEvaluationRepository.create(supervisorEvaluation);
        assertEquals(supervisorEvaluation, supervisorEvaluationRepository.findById(1L));
        assertNotEquals(supervisorEvaluation, supervisorEvaluationRepository.findById(4l));
    }

    @Test
    public void editTest() {
        SupervisorEvaluation supervisorEvaluation = new SupervisorEvaluation(1L, months, employee, criteria, +10.0);
        supervisorEvaluationRepository.create(supervisorEvaluation);
        supervisorEvaluation.setMarks(+16.0);
        supervisorEvaluationRepository.edit(supervisorEvaluation);
        SupervisorEvaluation sre = supervisorEvaluationRepository.findById(1L);
        assertEquals(+16.0, sre.getMarks());
    }
}
