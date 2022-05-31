package com.syntech.controller;

import com.syntech.model.User;
import com.syntech.repository.UserRepository;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author bipan
 */
@ViewScoped
@Named
public class UserController implements Serializable {

    private User user;

    @Inject
    private UserRepository userRepository;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @PostConstruct
    public void init() {
        this.user = new User();
    }

    public void create() {
        userRepository.create(user);
    }

    public String validateUser() {

        User userinfo = userRepository.findByUserNameNPassword(user.getUsername(), user.getPassword());
        if (userinfo == null) {
          return "/loginUserForm.xhtml?faces-redirect=true";
        }
        return "/faces/index.xhtml?faces-redirect=true";
    }
}
