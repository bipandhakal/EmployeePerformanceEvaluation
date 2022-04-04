package com.syntech;

import com.syntech.exception.CustomeMessageException;
import com.syntech.util.ValidationUtil;
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
        thrown.expect(CustomeMessageException.class);
        thrown.expectMessage("Invalid input");
        v.validateLong("jk");
    }
}
