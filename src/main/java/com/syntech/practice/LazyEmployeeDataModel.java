package com.syntech.practice;

import com.syntech.model.Employee;
import com.syntech.repository.EmployeeRepository;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 *
 * @author bipan
 */
public class LazyEmployeeDataModel extends LazyDataModel<Employee> {

    @Inject
    private EmployeeRepository employeeRepository;

    private static final long serialVersionUID = 1L;

    private List<Employee> employeeList;

    public LazyEmployeeDataModel(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee getRowData(String rowKey) {
        for (Employee employee : employeeList) {
            if (String.valueOf(employee.getId()).equals(rowKey)) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public String getRowKey(Employee employee) {
        return employee.getId().toString();
    }

    @Override
    public void setRowIndex(int rowIndex) {
        if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
        } else {
            super.setRowIndex(rowIndex % getPageSize());
        }
    }

//    public int count(Map<String, FilterMeta> filterBy) {
////        return (int) employeeList.stream()
////                .filter(o -> filter(FacesContext.getCurrentInstance(), filterBy.values(), o))
////                .count();
//        return 100;
//    }
    @Override
    public List<Employee> load(int offset, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        // apply offset & filters
        List<Employee> employees = employeeRepository.lazyLoad(offset, pageSize, sortBy, filterBy);
        System.out.println("size:" + employees.size());
        setRowCount(employeeRepository.lazyCount());

//             this.employeeList = employees;
//           employeeList.stream()
//                .skip(offset)
//                .filter(o -> filter(FacesContext.getCurrentInstance(), filterBy.values(), o))
//                .limit(pageSize)
//                .collect(Collectors.toList());
        //sort
//        if (!sortBy.isEmpty()) {
//            List<Comparator<Employee>> comparators = sortBy.values().stream()
//                    .map(o -> new LazySorter(o.getField(), o.getOrder()))
//                    .collect(Collectors.toList());
//            Comparator<Employee> cp = ComparatorUtils.chainedComparator(comparators); // from apache
//            employees.sort(cp);
//        }
        return employees;
    }

//    private boolean filter(FacesContext context, Collection<FilterMeta> filterBy, Object o) {
//        boolean matching = true;
//
//        for (FilterMeta filter : filterBy) {
//            FilterConstraint constraint = filter.getConstraint();
//            Object filterValue = filter.getFilterValue();
//
//            try {
//                //  Object columnValue = String.valueOf(ShowcaseUtil.getPropertyValueViaReflection(o, filter.getField()));
//                //   matching = constraint.isMatching(context, columnValue, filterValue, LocaleUtils.getCurrentLocale());
//                matching = false;
//            } catch (Exception e) {
//                matching = false;
//            }
//
//            if (!matching) {
//                break;
//            }
//        }
//
//        return matching;
//    }
}
