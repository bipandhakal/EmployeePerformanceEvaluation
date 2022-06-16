//package com.syntech.practice;
//
//import com.syntech.model.Employee;
//import java.util.List;
//import java.util.Map;
//import org.primefaces.model.LazyDataModel;
//import org.primefaces.model.SortOrder;
//
///**
// *
// * @author bipan
// */
//public class EmployeeLazyDataModel extends LazyDataModel<Employee> {
//
//    public EmployeeLazyDataModel() {
//        this.setRowCount(DataService.INSTANCE.getEmployeeTotalCount());
//    }
//
//    @Override
//    public List<Employee> load(int first, int pageSize, String sortField,
//            SortOrder sortOrder, Map<String, Object> filters) {
//
//        List<Employee> list = DataService.INSTANCE.getEmployeeList(first, pageSize, filters);
//        if (filters != null && filters.size() > 0) {
//            //otherwise it will still show all page links; pages at end will be empty
//            this.setRowCount(DataService.INSTANCE.getFilteredRowCount(filters));
//        }
//        return list;
//    }
//}
