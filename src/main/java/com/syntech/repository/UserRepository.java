package com.syntech.repository;

import com.syntech.model.User;
import com.syntech.model.User_;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

/**
 *
 * @author bipan
 */
@Stateless
public class UserRepository extends AbstractRepository<User> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;

    public UserRepository() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserRepository filterByUserName(String username) {
        Predicate userNamePredicates = criteriaBuilder.equal(root.get(User_.username), username);
        this.addCriteria(userNamePredicates);
        return this;
    }

    public UserRepository filterByPassword(String password) {
        Predicate passwordPredicates = criteriaBuilder.equal(root.get(User_.password), password);
        this.addCriteria(passwordPredicates);
        return this;
    }

    public User findByUserNameNPassword(String uname, String pass) {
        User u = null;
        try {
            u = ((UserRepository) this.startQuery()).filterByUserName(uname).filterByPassword(pass).getSingleResult();

//            Query query = em.createQuery("SELECT s FROM User s WHERE s.username=:u AND s.password=:p", User.class);
//            query.setParameter("u", uname).setParameter("p", pass);
//            u = (User) query.getSingleResult();
        } catch (NoResultException e) {
            u = null;
        }
        return u;
    }

    public User findByUserName(String uname) {
        User u;
        try {
            u = ((UserRepository) this.startQuery()).filterByUserName(uname).getSingleResult();
//            Query query = em.createQuery("SELECT s FROM User s WHERE s.username=:u", User.class);
//            query.setParameter("u", uname);
//            u = (User) query.getSingleResult();
        } catch (NoResultException e) {
            u = null;
        }
        return u;
    }

    public String getPassword(String uname) {
        User u;
        try {
//            Query query = em.createQuery("SELECT s FROM User s WHERE s.username=:u", User.class);
//            query.setParameter("u", uname);
//            u = (User) query.getSingleResult();
            u = ((UserRepository) this.startQuery()).filterByUserName(uname).getSingleResult();
            return u.getPassword();

        } catch (NoResultException e) {

        }
        return "";
    }
}
