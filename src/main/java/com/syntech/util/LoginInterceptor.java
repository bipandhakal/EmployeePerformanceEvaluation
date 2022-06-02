package com.syntech.util;

import com.syntech.bean.UserBean;
import com.syntech.model.User;
import java.io.Serializable;
import javax.enterprise.inject.Vetoed;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundConstruct;
import javax.interceptor.InvocationContext;

/**
 *
 * @author bipan
 */
@Vetoed
public class LoginInterceptor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private UserBean userBean;

    @AroundConstruct
    private Object init(InvocationContext ic) throws Exception {
        User user = userBean.getUser();
        if (user == null) {
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
                    .handleNavigation(FacesContext.getCurrentInstance(), null, "/login.xhtml?faces-redirect=true");
        }
        ic.proceed();
        return user;
    }
}
