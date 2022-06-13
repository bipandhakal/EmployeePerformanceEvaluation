package com.syntech.repository;

import com.syntech.model.CalculatedBy;
import com.syntech.model.Category;
import com.syntech.model.Criteria;
import com.syntech.model.CriteriaTrueFalse;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 *
 * @author bipan
 */
public class CriteriaTrueFalseRepositoryTest {

    CriteriaTrueFalseRepository criteriaTrueFalseRepository = new CriteriaTrueFalseRepository();
    Category category = new Category(2L, "Attendance", 35d);
    Criteria criteria = new Criteria(2L, category, "Present or Absent", 10d, null, CalculatedBy.TRUEORFALSE);

    @Test
    public void createTest() {
        CriteriaTrueFalse crtf = new CriteriaTrueFalse(1L, criteria, "Present", 10.0);
        criteriaTrueFalseRepository.create(crtf);
        assertEquals(1, criteriaTrueFalseRepository.findAll().size());
    }

    @Test
    public void findAllTest() {
        CriteriaTrueFalse crtf = new CriteriaTrueFalse(2L, criteria, "Absent", 1.0);
        criteriaTrueFalseRepository.create(crtf);
        assertEquals(1, criteriaTrueFalseRepository.findAll().size());
    }

    @Test
    public void deleteTest() {
        CriteriaTrueFalse crtf = new CriteriaTrueFalse(2L, criteria, "Absent", 1.0);
        criteriaTrueFalseRepository.create(crtf);
        criteriaTrueFalseRepository.delete(crtf);
        assertEquals(0, criteriaTrueFalseRepository.findAll().size());
    }

    @Test
    public void findByIdTest() {
        CriteriaTrueFalse crtf = new CriteriaTrueFalse(3L, criteria, "Present", 10.0);
        criteriaTrueFalseRepository.create(crtf);
        assertEquals(crtf, criteriaTrueFalseRepository.findById(3l));
        assertNotEquals(crtf, criteriaTrueFalseRepository.findById(4l));
    }

    @Test
    public void editTest() {
        CriteriaTrueFalse crtf = new CriteriaTrueFalse(3L, criteria, "Present", 10.0);
        criteriaTrueFalseRepository.create(crtf);
        crtf.setStatus("Absent");
        criteriaTrueFalseRepository.edit(crtf);
        CriteriaTrueFalse crtf1 = criteriaTrueFalseRepository.findById(3L);
        assertEquals("Absent", crtf1.getStatus());
    }
}
