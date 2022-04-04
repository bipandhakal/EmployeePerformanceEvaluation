package com.syntech.repository;

import com.syntech.model.Category;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 *
 * @author bipan
 */
public class CategoryRepositoryTest {

    CategoryRepository categoryRepository = new CategoryRepository();

    @Test
    public void createTest() {
        Category c1 = new Category(1L, "Task", 40.0);
        categoryRepository.create(c1);
        assertEquals(1, categoryRepository.findAll().size());
    }

    @Test
    public void findAllTest() {
        Category c2 = new Category(2L, "Attendance", 30.0);
        categoryRepository.create(c2);
        assertEquals(1, categoryRepository.findAll().size());
    }

    @Test
    public void deleteTest() {
        Category c3 = new Category(3L, "Attendance", 30.0);
        categoryRepository.create(c3);
        categoryRepository.delete(c3);
        assertEquals(0, categoryRepository.findAll().size());
    }

    @Test
    public void findByIdTest() {
        Category c4 = new Category(4L, "Presentation", 20.0);
        categoryRepository.create(c4);
        assertEquals(c4, categoryRepository.findById(4l));
        assertNotEquals(c4, categoryRepository.findById(3l));
    }

    @Test
    public void editTest() {
        Category c5 = new Category(5L, "Presentation", 50.0);
        categoryRepository.create(c5);
        c5.setName("Attitude");
        categoryRepository.edit(c5);
        Category c6 = categoryRepository.findById(5L);
        assertEquals("Attitude", c6.getName());
    }
}
