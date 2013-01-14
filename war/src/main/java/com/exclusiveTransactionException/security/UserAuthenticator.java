package com.exclusiveTransactionException.security;

import org.jboss.seam.security.Authenticator;
import org.jboss.seam.security.BaseAuthenticator;
import org.jboss.seam.security.CredentialsImpl;
import org.picketlink.idm.impl.api.model.SimpleUser;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserAuthenticator extends BaseAuthenticator implements Authenticator {
// ------------------------ INTERFACE METHODS ------------------------

    @Inject
    private CredentialsImpl credentials;

    @SuppressWarnings("CdiInjectionPointsInspection")
    @Inject
    private EntityManager entityManager;
// --------------------- Interface Authenticator ---------------------

    @Override
    public void authenticate()
    {
        final List resultList = entityManager.createQuery("from User where email=:email and password=:password")
            .setParameter("email", credentials.getUsername())
            .setParameter("password", credentials.getPassword())
            .getResultList();
        if (resultList.isEmpty()) {
            setStatus(AuthenticationStatus.FAILURE);
        } else {
            setStatus(AuthenticationStatus.SUCCESS);
            setUser(new SimpleUser("developer"));
        }
    }
}
