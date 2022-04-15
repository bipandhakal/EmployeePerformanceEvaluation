package com.syntech.repository;

import com.syntech.model.IEntity;
import com.syntech.model.IRepository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bipan
 */
public abstract class AbstractRepository<T extends IEntity> implements IRepository<T> {

    private List<T> list;

    public AbstractRepository() {
        list = new ArrayList<>();
    }

    @Override
    public void create(T obj) {
        this.list.add(obj);
    }

    @Override
    public List<T> findAll() {
        return list;
    }

    @Override
    public T findById(Long id) {
        for (T obj : list) {
            if (obj.getId().equals(id)) {
                return obj;
            }
        }
        return null;
    }

    @Override
    public void delete(T obj) {
        this.list.remove(obj);
    }

    @Override
    public void establishConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/EPE", "root", "toor");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
