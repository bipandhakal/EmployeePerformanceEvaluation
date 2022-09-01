package com.syntech.repository;

import com.syntech.model.CalculatedBy;
import com.syntech.model.Category;
import com.syntech.model.Criteria;
import com.syntech.model.CriteriaSelf;
import com.syntech.model.Employee;
import com.syntech.model.Months;
import java.util.Date;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 *
 * @author bipan
 */
public class CriteriaSelfRepositoryTest {

    CriteriaSelfRepository criteriaSelfRepository = new CriteriaSelfRepository();
    Category category = new Category(1L, "Skills", 25d);
    Criteria criteria = new Criteria(1L, category, "Presentation skills", 22d, null, CalculatedBy.SELF);
    Months months = new Months(1L, "January", 1);
    Employee employee = new Employee(1L, "Bipan", "Dhakal", new Date(2022 - 04 - 02), "9845676438", "bipan.dhakal@syntechnepal.com");

    @Test
    public void createTest() {
        CriteriaSelf crs1 = new CriteriaSelf(1L, employee, months, criteria, 15.0);
        criteriaSelfRepository.create(crs1);
        assertEquals(1, criteriaSelfRepository.findAll().size());
    }

    @Test
    public void findAllTest() {
        CriteriaSelf crs1 = new CriteriaSelf(2L, employee, months, criteria, 10.0);
        criteriaSelfRepository.create(crs1);
        assertEquals(1, criteriaSelfRepository.findAll().size());
    }

    @Test
    public void deleteTest() {
        CriteriaSelf crs2 = new CriteriaSelf(2L, employee, months, criteria, 20.0);
        criteriaSelfRepository.create(crs2);
        criteriaSelfRepository.delete(crs2);
        assertEquals(0, criteriaSelfRepository.findAll().size());
    }

    @Test
    public void findByIdTest() {
        CriteriaSelf crs3 = new CriteriaSelf(3L, employee, months, criteria, 20.0);
        criteriaSelfRepository.create(crs3);
        assertEquals(crs3, criteriaSelfRepository.findById(3l));
        assertNotEquals(crs3, criteriaSelfRepository.findById(4l));
    }

    @Test
    public void editTest() {
        CriteriaSelf crs4 = new CriteriaSelf(4L, employee, months, criteria, 21.0);
        criteriaSelfRepository.create(crs4);
        crs4.setMarks(16.0);
        criteriaSelfRepository.edit(crs4);
        CriteriaSelf crs = criteriaSelfRepository.findById(4L);
        assertEquals(16.0, crs.getMarks());
    }
}
