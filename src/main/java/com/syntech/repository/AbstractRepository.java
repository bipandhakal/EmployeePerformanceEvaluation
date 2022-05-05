package com.syntech.repository;

import com.syntech.model.IEntity;
import com.syntech.model.IRepository;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author bipan
 */
public abstract class AbstractRepository<T extends IEntity> implements IRepository<T> {

    protected abstract EntityManager getEntityManager();
    private Class<T> entityClass;

    public AbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void create(T obj) {
        getEntityManager().persist(obj);
        getEntityManager().flush();
    }

    @Override
    public void edit(T obj) {
        getEntityManager().merge(obj);
        getEntityManager().flush();
    }

    @Override
    public List<T> findAll() {
        return getEntityManager().createQuery("Select t from " + entityClass.getName() + " t").getResultList();
    }

    @Override
    public T findById(Long id) {
        return getEntityManager().find(entityClass, id);
    }

    @Override
    public void delete(T obj) {
        getEntityManager().remove(findById(obj.getId()));
        getEntityManager().flush();
    }

//    public Connection establishConnection() {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/EPE", "root", "toor");
//            return con;
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return null;
//    }
}
