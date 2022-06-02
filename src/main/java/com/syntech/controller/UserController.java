package com.syntech.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.syntech.model.User;
import com.syntech.repository.UserRepository;
import java.io.Serializable;
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

    public String validateLogin() {
        String username = userRepository.checkUserName(user.getUsername());
        if (!username.isEmpty()) {
            String hashedPassword = userRepository.getPassword(user.getUsername());
            String inPass = user.getPassword();
            Boolean b1 = isHashingMatched(inPass, hashedPassword);
            if (b1 == true) {
                return "/faces/index.xhtml?faces-redirect=true";
            }
        }
        return "/login.xhtml?faces-redirect=true";

//        User userinfo = userRepository.findByUserNameNPassword(user.getUsername(), user.getPassword());
//        if (userinfo == null) {
//            return "/login.xhtml?faces-redirect=true";
//        }
//        return "/faces/index.xhtml?faces-redirect=true";
    }

    public String hashString(String unHashedPassword) {
        return BCrypt.withDefaults().hashToString(12, unHashedPassword.toCharArray());
    }

    public boolean isHashingMatched(String password, String hashString) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashString);
        return result.verified;
    }
}
