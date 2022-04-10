package com.syntech.repository;

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

    @Test
    public void createTest() {
        CriteriaTrueFalse crtf = new CriteriaTrueFalse(1L, "Present", 10.0);
        criteriaTrueFalseRepository.create(crtf);
        assertEquals(1, criteriaTrueFalseRepository.findAll().size());
    }

    @Test
    public void findAllTest() {
        CriteriaTrueFalse crtf = new CriteriaTrueFalse(2L, "Absent", 1.0);
        criteriaTrueFalseRepository.create(crtf);
        assertEquals(1, criteriaTrueFalseRepository.findAll().size());
    }

    @Test
    public void deleteTest() {
        CriteriaTrueFalse crtf = new CriteriaTrueFalse(2L, "Absent", 1.0);
        criteriaTrueFalseRepository.create(crtf);
        criteriaTrueFalseRepository.delete(crtf);
        assertEquals(0, criteriaTrueFalseRepository.findAll().size());
    }

    @Test
    public void findByIdTest() {
        CriteriaTrueFalse crtf = new CriteriaTrueFalse(3L, "Present", 10.0);
        criteriaTrueFalseRepository.create(crtf);
        assertEquals(crtf, criteriaTrueFalseRepository.findById(3l));
        assertNotEquals(crtf, criteriaTrueFalseRepository.findById(4l));
    }

    @Test
    public void editTest() {
        CriteriaTrueFalse crtf = new CriteriaTrueFalse(3L, "Present", 10.0);
        criteriaTrueFalseRepository.create(crtf);
        crtf.setStatus("Absent");
        criteriaTrueFalseRepository.edit(crtf);
        CriteriaTrueFalse crtf1 = criteriaTrueFalseRepository.findById(3L);
        assertEquals("Absent", crtf1.getStatus());
    }
}
