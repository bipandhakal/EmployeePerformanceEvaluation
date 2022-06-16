package com.syntech.practice;

import com.syntech.model.Employee;
import java.util.Comparator;
import org.primefaces.model.SortOrder;

/**
 *
 * @author bipan
 */
public class LazySorter implements Comparator<Employee> {

    private String sortField;
    private SortOrder sortOrder;

    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    @Override
    public int compare(Employee employee1, Employee employee2) {
        try {
            //  Object value1 = ShowcaseUtil.getPropertyValueViaReflection(employee1, sortField);
            //     Object value2 = ShowcaseUtil.getPropertyValueViaReflection(employee2, sortField);

            Object value1 = null;
            Object value2 = null;
            if (sortField.equals("firstName")) {
                value1 = employee1.getFirstName();
                value2 = employee2.getFirstName();
            }

            int value = ((Comparable) value1).compareTo(value2);

            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
