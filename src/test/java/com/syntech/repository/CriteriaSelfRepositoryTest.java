package com.syntech.repository;

import com.syntech.model.CriteriaSelf;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 *
 * @author bipan
 */
public class CriteriaSelfRepositoryTest {

    CriteriaSelfRepository criteriaSelfRepository = new CriteriaSelfRepository();

    @Test
    public void createTest() {
        CriteriaSelf crs1 = new CriteriaSelf(1L, 15.0);
        criteriaSelfRepository.create(crs1);
        assertEquals(1, criteriaSelfRepository.findAll().size());
    }

    @Test
    public void findAllTest() {
        CriteriaSelf crs1 = new CriteriaSelf(2L, 10.0);
        criteriaSelfRepository.create(crs1);
        assertEquals(1, criteriaSelfRepository.findAll().size());
    }

    @Test
    public void deleteTest() {
        CriteriaSelf crs2 = new CriteriaSelf(2L, 20.0);
        criteriaSelfRepository.create(crs2);
        criteriaSelfRepository.delete(crs2);
        assertEquals(0, criteriaSelfRepository.findAll().size());
    }

    @Test
    public void findByIdTest() {
        CriteriaSelf crs3 = new CriteriaSelf(3L, 20.0);
        criteriaSelfRepository.create(crs3);
        assertEquals(crs3, criteriaSelfRepository.findById(3l));
        assertNotEquals(crs3, criteriaSelfRepository.findById(4l));
    }

    @Test
    public void editTest() {
        CriteriaSelf crs4 = new CriteriaSelf(4L, 21.0);
        criteriaSelfRepository.create(crs4);
        crs4.setMarks(16.0);
        criteriaSelfRepository.edit(crs4);
        CriteriaSelf crs = criteriaSelfRepository.findById(4L);
        assertEquals(16.0, crs.getMarks());
    }

}
