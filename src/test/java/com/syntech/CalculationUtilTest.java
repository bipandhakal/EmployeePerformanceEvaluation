package com.syntech;

import com.syntech.model.CalculatedBy;
import com.syntech.model.Criteria;
import com.syntech.model.CriteriaRange;
import com.syntech.model.CriteriaSelf;
import com.syntech.model.CriteriaTrueFalse;
import com.syntech.model.SupervisorEvaluation;
import com.syntech.util.CalculationUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 *
 * @author bipan
 */
public class CalculationUtilTest {

    CalculationUtil calculationUtil = new CalculationUtil();
    Criteria obj = new Criteria();
    CriteriaRange criteriaRange = new CriteriaRange();
    CriteriaSelf criteriaSelf = new CriteriaSelf();
    CriteriaTrueFalse criteriaTrueFalse = new CriteriaTrueFalse();

    @Test
    public void criteriaRangeCalculationTest() {
        ArrayList<CriteriaRange> list = new ArrayList<>();
        CriteriaRange cr = new CriteriaRange(1L, criteriaRange.getCriteria(), 1L, 10L, 5.0);
        CriteriaRange cr1 = new CriteriaRange(2L, criteriaRange.getCriteria(), 11L, 20L, 10.0);
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
        CriteriaTrueFalse ctf = new CriteriaTrueFalse(1L, criteriaTrueFalse.getCriteria(), "Present", 10.0);
        CriteriaTrueFalse ctf1 = new CriteriaTrueFalse(1L, criteriaTrueFalse.getCriteria(), "Absent", 1.0);
        list.add(ctf);
        list.add(ctf1);

        assertEquals(10.0, calculationUtil.criteriaTrueFalseCalculation(list, "Present"));
        assertEquals(1.0, calculationUtil.criteriaTrueFalseCalculation(list, "Absent"));
        assertEquals(0.0, calculationUtil.criteriaTrueFalseCalculation(list, "F"));
    }

    @Test
    public void criteriaAverageCalculationTest() {
        ArrayList<Criteria> list = new ArrayList<>();
        Criteria ctr = new Criteria(3L, obj.getCategory(), "Number of present Days", 10.0, BigDecimal.valueOf(25.0), CalculatedBy.AVERAGE);
        Criteria ctr1 = new Criteria(3L, obj.getCategory(), "Number of present Days", 10.0, BigDecimal.valueOf(20.0), CalculatedBy.AVERAGE);

        list.add(ctr);
        list.add(ctr1);

        assertEquals(8.0, calculationUtil.criteriaAverageCalculation(ctr, 20L));
        assertEquals(5.0, calculationUtil.criteriaAverageCalculation(ctr1, 10L));
        assertNotEquals(8.0, calculationUtil.criteriaAverageCalculation(ctr, 0L));
    }

    @Test
    public void finalMarksTest() {
        ArrayList<CriteriaRange> list = new ArrayList<>();
        CriteriaRange cr = new CriteriaRange(1L, criteriaRange.getCriteria(), 1L, 10L, 5.0);
        CriteriaRange cr1 = new CriteriaRange(2L, criteriaRange.getCriteria(), 11L, 20L, 10.0);
        list.add(cr);
        list.add(cr1);

        ArrayList<CriteriaTrueFalse> list1 = new ArrayList<>();
        CriteriaTrueFalse ctf = new CriteriaTrueFalse(1L, criteriaTrueFalse.getCriteria(), "Present", 10.0);
        CriteriaTrueFalse ctf1 = new CriteriaTrueFalse(1L, criteriaTrueFalse.getCriteria(), "Absent", 1.0);
        list1.add(ctf);
        list1.add(ctf1);

        Criteria ctr = new Criteria(3L, obj.getCategory(), "Number of present Days", 10.0, BigDecimal.valueOf(25.0), CalculatedBy.AVERAGE);
        Criteria ctr1 = new Criteria(3L, obj.getCategory(), "Number of present Days", 10.0, BigDecimal.valueOf(20.0), CalculatedBy.AVERAGE);

        SupervisorEvaluation sevaluation = new SupervisorEvaluation(1L, 1L, 1L, -3.0);

        CriteriaSelf cself = new CriteriaSelf(1L, criteriaSelf.getCriteria(), 20.0);

        assertNotEquals(2.0, calculationUtil.finalMarks(list, 10L, list1, "Present", ctr, 20L, cself, sevaluation));
        assertEquals(40.0, calculationUtil.finalMarks(list, 10L, list1, "Present", ctr, 20L, cself, sevaluation));
        assertEquals(35.0, calculationUtil.finalMarks(list, 25L, list1, "Present", ctr, 20L, cself, sevaluation));
    }
}
