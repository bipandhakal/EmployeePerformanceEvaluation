package com.syntech.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.syntech.bean.UserBean;
import com.syntech.model.User;
import com.syntech.repository.UserRepository;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

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

    @Inject
    private UserBean userBean;

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
        User u = userRepository.findByUserName(user.getUsername());
        if (u == null) {
            return "/login.xhtml?faces-redirect=true";
        }

        String hashedPassword = u.getPassword();
        String inPass = user.getPassword();
        if (isHashingMatched(inPass, hashedPassword)) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

            Map<String, Object> appMap = FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
            appMap.put(u.getId().toString(), session);
            userBean.setUser(u);
            return "/faces/index.xhtml?faces-redirect=true";
        }
        return "/login.xhtml?faces-redirect=true";
    }

    public String hashString(String unHashedPassword) {
        return BCrypt.withDefaults().hashToString(12, unHashedPassword.toCharArray());
    }

    public boolean isHashingMatched(String password, String hashString) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashString);
        return result.verified;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }

    public void forwardToDashboard(ComponentSystemEvent cse) {
        if (userBean.getUser() != null) {
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
                    .handleNavigation(FacesContext.getCurrentInstance(), null, "/index.xhtml?faces-redirect=true");
        }
    }
}
