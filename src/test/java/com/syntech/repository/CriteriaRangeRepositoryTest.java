package com.syntech.repository;

import com.syntech.model.CriteriaRange;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 *
 * @author bipan
 */
public class CriteriaRangeRepositoryTest {

    CriteriaRangeRepository criteriaRangeRepository = new CriteriaRangeRepository();

    @Test
    public void createTest() {
        CriteriaRange cr1 = new CriteriaRange(1L, 10L, 20L, 15.0);
        criteriaRangeRepository.create(cr1);
        assertEquals(1, criteriaRangeRepository.findAll().size());
    }

    @Test
    public void findAllTest() {
        CriteriaRange cr2 = new CriteriaRange(2L, 10L, 20L, 15.0);
        criteriaRangeRepository.create(cr2);
        assertEquals(1, criteriaRangeRepository.findAll().size());
    }

    @Test
    public void deleteTest() {
        CriteriaRange cr3 = new CriteriaRange(3L, 10L, 20L, 18.0);
        criteriaRangeRepository.create(cr3);
        criteriaRangeRepository.delete(cr3);
        assertEquals(0, criteriaRangeRepository.findAll().size());
    }

    @Test
    public void findByIdTest() {
        CriteriaRange cr4 = new CriteriaRange(4L, 11L, 20L, 18.0);
        criteriaRangeRepository.create(cr4);
        assertEquals(cr4, criteriaRangeRepository.findById(4l));
        assertNotEquals(cr4, criteriaRangeRepository.findById(3l));
    }

    @Test
    public void editTest() {
        CriteriaRange cr5 = new CriteriaRange(5L, 15L, 20L, 15.0);
        criteriaRangeRepository.create(cr5);
        cr5.setFrom(10L);
        criteriaRangeRepository.edit(cr5);
        CriteriaRange cr6 = criteriaRangeRepository.findById(5L);
        assertEquals(10L, cr6.getFrom());
    }
}
