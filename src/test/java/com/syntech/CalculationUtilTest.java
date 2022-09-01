package com.syntech;

import com.syntech.model.CalculatedBy;
import com.syntech.model.Category;
import com.syntech.model.Criteria;
import com.syntech.model.CriteriaRange;
import com.syntech.model.CriteriaSelf;
import com.syntech.model.CriteriaTrueFalse;
import com.syntech.model.Employee;
import com.syntech.model.Months;
import com.syntech.model.SupervisorEvaluation;
import com.syntech.util.CalculationUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 *
 * @author bipan
 */
public class CalculationUtilTest {

    CalculationUtil calculationUtil = new CalculationUtil();

    @Test
    public void criteriaRangeCalculationTest() {
        ArrayList<CriteriaRange> list = new ArrayList<>();
        Category category = new Category(1L, "Task", 35d);
        Criteria criteria = new Criteria(1L, category, "Number of completed task", 25d, BigDecimal.valueOf(20.0), CalculatedBy.RANGE);
        CriteriaRange cr = new CriteriaRange(1L, criteria, 1L, 10L, 5.0);
        CriteriaRange cr1 = new CriteriaRange(2L, criteria, 11L, 20L, 10.0);
        list.add(cr);
        list.add(cr1);

        assertEquals(5.0, calculationUtil.criteriaRangeCalculation(list, 10L));
        assertEquals(5.0, calculationUtil.criteriaRangeCalculation(list, 1L));
        assertEquals(10.0, calculationUtil.criteriaRangeCalculation(list, 15L));
        assertEquals(0.0, calculationUtil.criteriaRangeCalculation(list, 25L));
        assertEquals(10.0, calculationUtil.criteriaRangeCalculation(list, 20L));
        assertEquals(10.0, calculationUtil.criteriaRangeCalculation(list, 11L));
        assertNotEquals(10.0, calculationUtil.criteriaRangeCalculation(list, 0L));
    }

    @Test
    public void criteriaTrueFalseCalculationTest() {

        ArrayList<CriteriaTrueFalse> list = new ArrayList<>();
        Category category = new Category(2L, "Attendance", 35d);
        Criteria criteria = new Criteria(2L, category, "Present or Absent", 10d, null, CalculatedBy.TRUEORFALSE);
        CriteriaTrueFalse ctf = new CriteriaTrueFalse(1L, criteria, "Present", 10.0);
        CriteriaTrueFalse ctf1 = new CriteriaTrueFalse(1L, criteria, "Absent", 1.0);
        list.add(ctf);
        list.add(ctf1);

        assertEquals(10.0, calculationUtil.criteriaTrueFalseCalculation(list, "Present"));
        assertEquals(1.0, calculationUtil.criteriaTrueFalseCalculation(list, "Absent"));
        assertEquals(0.0, calculationUtil.criteriaTrueFalseCalculation(list, "F"));
    }

    @Test
    public void criteriaAverageCalculationTest() {
        Category category = new Category(3L, "Attendance", 20d);
        Criteria ctr = new Criteria(3L, category, "Number of present Days", 10.0, BigDecimal.valueOf(25.0), CalculatedBy.AVERAGE);
        Criteria ctr1 = new Criteria(3L, category, "Number of present Days", 10.0, BigDecimal.valueOf(20.0), CalculatedBy.AVERAGE);

        assertEquals(8.0, calculationUtil.criteriaAverageCalculation(ctr, 20L));
        assertEquals(5.0, calculationUtil.criteriaAverageCalculation(ctr1, 10L));
        assertNotEquals(8.0, calculationUtil.criteriaAverageCalculation(ctr, 0L));
    }

    @Test
    public void finalMarksTest() {
        ArrayList<CriteriaRange> list = new ArrayList<>();
        Category category = new Category(1L, "Task", 35d);
        Criteria criteria = new Criteria(1L, category, "Number of completed task", 25d, BigDecimal.valueOf(20.0), CalculatedBy.RANGE);
        CriteriaRange cr = new CriteriaRange(1L, criteria, 1L, 10L, 5.0);
        CriteriaRange cr1 = new CriteriaRange(2L, criteria, 11L, 20L, 10.0);
        list.add(cr);
        list.add(cr1);

        ArrayList<CriteriaTrueFalse> list1 = new ArrayList<>();
        Category category1 = new Category(2L, "Attendance", 35d);
        Criteria criteria1 = new Criteria(2L, category1, "Present or Absent", 10d, null, CalculatedBy.TRUEORFALSE);
        CriteriaTrueFalse ctf = new CriteriaTrueFalse(1L, criteria1, "Present", 10.0);
        CriteriaTrueFalse ctf1 = new CriteriaTrueFalse(1L, criteria1, "Absent", 1.0);
        list1.add(ctf);
        list1.add(ctf1);

        Criteria ctr = new Criteria(3L, category1, "Number of present Days", 10.0, BigDecimal.valueOf(25.0), CalculatedBy.AVERAGE);
        Criteria ctr1 = new Criteria(3L, category1, "Number of present Days", 10.0, BigDecimal.valueOf(20.0), CalculatedBy.AVERAGE);

        Months months = new Months(1L, "January", 1);
        Employee employee = new Employee(1L, "Bipan", "Dhakal", new Date(2022 - 04 - 02), "9845676438", "bipan.dhakal@syntechnepal.com");
        SupervisorEvaluation sevaluation = new SupervisorEvaluation(1L, months, employee, criteria, -3.0);

        Category category2 = new Category(3L, "Skills", 25d);
        Criteria criteria2 = new Criteria(1L, category2, "Presentation skills", 20d, null, CalculatedBy.SELF);
        CriteriaSelf cself = new CriteriaSelf(1L, employee, months, criteria2, 20.0);

        assertNotEquals(2.0, calculationUtil.finalMarks(list, 10L, list1, "Present", ctr, 20L, cself, sevaluation));
        assertEquals(40.0, calculationUtil.finalMarks(list, 10L, list1, "Present", ctr, 20L, cself, sevaluation));
        assertEquals(35.0, calculationUtil.finalMarks(list, 25L, list1, "Present", ctr, 20L, cself, sevaluation));
    }
}
