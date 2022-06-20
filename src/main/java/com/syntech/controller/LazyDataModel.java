package com.syntech.controller;

import com.syntech.model.IEntity;
import com.syntech.repository.LazyRepository;
import java.util.List;
import java.util.Map;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

/**
 *
 * @author bipan
 */
public class LazyDataModel<T extends IEntity> extends org.primefaces.model.LazyDataModel<T> {

    private LazyRepository<T> lazyRepository;

    private static final long serialVersionUID = 1L;

    private List<T> List;

    public LazyDataModel(LazyRepository<T> lazyRepository) {
        this.lazyRepository = lazyRepository;
    }

    @Override
    public T getRowData(String rowKey) {
        for (T obj : List) {
            if (String.valueOf(obj.getId()).equals(rowKey)) {
                return obj;
            }
        }
        return null;
    }

    @Override
    public String getRowKey(T obj) {
        return obj.getId().toString();
    }

    @Override
    public void setRowIndex(int rowIndex) {
        if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
        } else {
            super.setRowIndex(rowIndex % getPageSize());
        }
    }

    @Override
    public List<T> load(int offset, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        // apply offset & filters
        List<T> totalList = lazyRepository.lazyLoad(offset, pageSize, sortBy, filterBy);
        System.out.println("size:" + totalList.size());
        setRowCount(lazyRepository.lazyCount());
        return totalList;
    }
}
