/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.IEntity;
import java.util.List;
import java.util.Map;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

/**
 *
 * @author bipan
 */
public abstract class LazyRepository<T extends IEntity> extends AbstractRepository<T> {

    public LazyRepository(Class<T> entityClass) {
        super(entityClass);
    }

    public abstract List<T> lazyLoad(int offset, int pagesize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy);

    public abstract Integer lazyCount();
}
