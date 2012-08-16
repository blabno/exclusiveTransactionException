package com.exclusiveTransactionException.security;

import org.jboss.seam.security.Authenticator;
import org.jboss.seam.security.BaseAuthenticator;
import org.picketlink.idm.impl.api.model.SimpleUser;

import javax.faces.application.ProjectStage;
import javax.faces.context.FacesContext;

public class UserAuthenticator extends BaseAuthenticator implements Authenticator {
// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface Authenticator ---------------------

    @Override
    public void authenticate()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null && ProjectStage.Development.equals(facesContext.getApplication().getProjectStage())) {
            setStatus(AuthenticationStatus.SUCCESS);
            setUser(new SimpleUser("developer"));
        } else {
            setStatus(AuthenticationStatus.FAILURE);
        }
    }
}
