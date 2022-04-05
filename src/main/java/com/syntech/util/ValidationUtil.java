package com.syntech.util;

import com.syntech.exception.CustomeMessageException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author bipan
 */
public class ValidationUtil {

    public Boolean validateDate(String joinedDate) {

        String p = "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$";
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(joinedDate);
        if (matcher.matches()) {
            System.out.println(joinedDate + " is valid date format");
            return true;
        } else {
            System.out.println(joinedDate + " is invalid date format");
            return false;
        }
    }

    public Boolean validateString(String str) {
        return (str != null && !str.isEmpty());
    }

    public Long validateLong(String num) {
        try {
            return Long.parseLong(num);
        } catch (Exception e) {
            throw new CustomeMessageException("Invalid input");
        }
    }

    public Boolean validatesLong(Long number) {
        return number != null && number != 0L;
    }

}
