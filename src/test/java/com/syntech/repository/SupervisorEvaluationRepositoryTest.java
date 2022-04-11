package com.syntech.repository;

import com.syntech.model.SupervisorEvaluation;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 *
 * @author bipan
 */
public class SupervisorEvaluationRepositoryTest {

    SupervisorEvaluationRepository supervisorEvaluationRepository = new SupervisorEvaluationRepository();

    @Test
    public void createTest() {
        SupervisorEvaluation supervisorEvaluation = new SupervisorEvaluation(1L, 2L, 1L, +10.0);
        supervisorEvaluationRepository.create(supervisorEvaluation);
        assertEquals(1, supervisorEvaluationRepository.findAll().size());
    }

    @Test
    public void findAllTest() {
        SupervisorEvaluation supervisorEvaluation = new SupervisorEvaluation(1L, 2L, 1L, -10.0);
        supervisorEvaluationRepository.create(supervisorEvaluation);
        assertEquals(1, supervisorEvaluationRepository.findAll().size());
    }

    @Test
    public void deleteTest() {
        SupervisorEvaluation supervisorEvaluation = new SupervisorEvaluation(2L, 2L, 1L, -10.0);
        supervisorEvaluationRepository.create(supervisorEvaluation);
        supervisorEvaluationRepository.delete(supervisorEvaluation);
        assertEquals(0, supervisorEvaluationRepository.findAll().size());
    }

    @Test
    public void findByIdTest() {
        SupervisorEvaluation supervisorEvaluation = new SupervisorEvaluation(3L, 2L, 1L, -2.0);
        supervisorEvaluationRepository.create(supervisorEvaluation);
        assertEquals(supervisorEvaluation, supervisorEvaluationRepository.findById(3L));
        assertNotEquals(supervisorEvaluation, supervisorEvaluationRepository.findById(4l));
    }

    @Test
    public void editTest() {
        SupervisorEvaluation supervisorEvaluation = new SupervisorEvaluation(4L, 2L, 1L, +2.0);
        supervisorEvaluationRepository.create(supervisorEvaluation);
        supervisorEvaluation.setMarks(+16.0);
        supervisorEvaluationRepository.edit(supervisorEvaluation);
        SupervisorEvaluation sre = supervisorEvaluationRepository.findById(4L);
        assertEquals(+16.0, sre.getMarks());
    }
}
