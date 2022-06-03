package com.syntech;

import com.syntech.exception.CustomMessageException;
import static com.syntech.model.CalculatedBy.AVERAGE;
import static com.syntech.model.CalculatedBy.RANGE;
import static com.syntech.model.CalculatedBy.SELF;
import static com.syntech.model.CalculatedBy.TRUEORFALSE;
import com.syntech.util.ValidationUtil;
import java.math.BigDecimal;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.rules.ExpectedException;

/**
 *
 * @author bipan
 */
public class ValidationUtilTest {

    ValidationUtil v = new ValidationUtil();

    @Test
    public void dateValidateTest() {

        Assertions.assertTrue(v.validateDate("2022-01-02"));
        Assertions.assertTrue(v.validateDate("2022-01-02"));
        Assertions.assertFalse(v.validateDate("2022/01-02"));
        Assertions.assertFalse(v.validateDate("gfgyuu"));
        Assertions.assertFalse(v.validateDate("2022/01/02"));

    }

    @Test
    public void validateStringTest() {
        Assertions.assertTrue(v.validateString("Bipan"));
        Assertions.assertFalse(v.validateString(""));
        Assertions.assertTrue(v.validateString(" "));
        Assertions.assertFalse(v.validateString(null));
        Assertions.assertTrue(v.validateString("123"));

        Assertions.assertTrue(v.validateString("###fghbj"));
        Assertions.assertTrue(v.validateString("%%$#$@"));
    }

    @Test
    public void validateLongTest() {
        assertEquals(1, v.validateLong("1"));
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void test1() throws Exception {
        thrown.expect(CustomMessageException.class);
        thrown.expectMessage("Invalid input");
        v.validateLong("jk");
    }

    @Test
    public void validatesLongTest() {
        Assertions.assertTrue(v.validatesLong(2L));
        Assertions.assertFalse(v.validatesLong(0L));
        Assertions.assertFalse(v.validatesLong(null));
    }

    @Test
    public void validatesDoubleTest() {
        Assertions.assertTrue(v.validatesDouble(10.0));
        Assertions.assertFalse(v.validatesDouble(0.0d));
        Assertions.assertFalse(v.validatesDouble(null));
    }

    @Test
    public void validateBigDecimalTest() {
        Assertions.assertTrue(v.validateBigDecimal(BigDecimal.valueOf(10.0)));
        Assertions.assertFalse(v.validateBigDecimal(null));
    }

    @Test
    public void validateCalculatedByTest() {
        assertEquals(RANGE, v.validateCalculatedBy("RANGE"));
        assertEquals(AVERAGE, v.validateCalculatedBy("AVERAGE"));
        assertEquals(SELF, v.validateCalculatedBy("SELF"));
        assertEquals(TRUEORFALSE, v.validateCalculatedBy("TRUEORFALSE"));
        assertEquals(null, v.validateCalculatedBy("fghj"));
    }
}
