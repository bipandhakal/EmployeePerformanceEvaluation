package com.syntech.repository;

import com.syntech.model.CalculatedBy;
import com.syntech.model.Criteria;
import java.math.BigDecimal;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 *
 * @author bipan
 */
public class CriteriaRepositoryTest {

    CriteriaRepository criteriaRepository = new CriteriaRepository();

    @Test
    public void createTest() {
        Criteria c1 = new Criteria(1L, 1L, "Number of completed tasks", 10.0, BigDecimal.valueOf(10.0), CalculatedBy.RANGE);
        criteriaRepository.create(c1);
        assertEquals(1, criteriaRepository.findAll().size());
    }

    @Test
    public void findAllTest() {
        Criteria c2 = new Criteria(2L, 1L, "Date of submission", 20.0, BigDecimal.valueOf(15.0), CalculatedBy.RANGE);
        criteriaRepository.create(c2);
        assertEquals(1, criteriaRepository.findAll().size());
    }

    @Test
    public void deleteTest() {
        Criteria c3 = new Criteria(3L, 1L, "Date of submission", 20.0, BigDecimal.valueOf(15.0), CalculatedBy.RANGE);
        criteriaRepository.create(c3);
        criteriaRepository.delete(c3);
        assertEquals(0, criteriaRepository.findAll().size());
    }

    @Test
    public void findByIdTest() {
        Criteria c4 = new Criteria(4L, 1L, "Fully completed or not", 20.0, BigDecimal.valueOf(15.0), CalculatedBy.RANGE);
        criteriaRepository.create(c4);
        assertEquals(c4, criteriaRepository.findById(4l));
        assertNotEquals(c4, criteriaRepository.findById(3l));
    }

    @Test
    public void editTest() {
        Criteria c4 = new Criteria(4L, 1L, "Fully completed or not", 20.0, BigDecimal.valueOf(20.0), CalculatedBy.RANGE);
        criteriaRepository.create(c4);
        c4.setName("Remaining task to complete");
        criteriaRepository.edit(c4);
        Criteria c6 = criteriaRepository.findById(4L);
        assertEquals("Remaining task to complete", c6.getName());
    }
}
