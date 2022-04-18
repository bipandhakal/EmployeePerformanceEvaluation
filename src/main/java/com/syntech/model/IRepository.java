package com.syntech.model;

import java.util.List;

/**
 *
 * @author bipan
 */
public interface IRepository<T> {

    public void create(T e);

    public List<T> findAll();

    public T findById(Long id);

    public void delete(T employee);

    public void edit(T emp);

}
