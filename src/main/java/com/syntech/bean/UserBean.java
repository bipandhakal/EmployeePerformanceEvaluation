package com.syntech.bean;

import com.syntech.model.User;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author bipan
 */
@Named
@SessionScoped
public class UserBean implements Serializable {
    
    private User user;
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public boolean isLoggedIn() {
        return user.getId() != null;
    }
    
    public boolean isAdmin() {
        return user != null && user.getRole().equals("admin");
    }
}
