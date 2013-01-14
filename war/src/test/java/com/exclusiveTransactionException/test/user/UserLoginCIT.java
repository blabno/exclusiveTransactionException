package com.exclusiveTransactionException.test.user;

import com.exclusiveTransactionException.test.Packager;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.PersistenceTest;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.seam.security.CredentialsImpl;
import org.jboss.seam.security.IdentityImpl;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import pl.com.it_crowd.arquillian.mock_contexts.ConversationScopeRequired;
import pl.com.it_crowd.arquillian.mock_contexts.FacesContextRequired;
import pl.com.it_crowd.arquillian.mock_contexts.MockFacesContextProducer;

import javax.faces.application.Application;
import javax.faces.application.ProjectStage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@PersistenceTest
@RunWith(Arquillian.class)
public class UserLoginCIT {
// ------------------------------ FIELDS ------------------------------

    @Inject
    private CredentialsImpl credentials;

    @Inject
    private IdentityImpl identity;

// -------------------------- STATIC METHODS --------------------------

    @Deployment
    public static WebArchive createDeployment()
    {
        return new Packager(UserLoginCIT.class).buildCITPackage();
    }

// -------------------------- OTHER METHODS --------------------------

    @FacesContextRequired
    @ConversationScopeRequired
    @Test
    @UsingDataSet("com/exclusiveTransactionException/test/user/UserLoginCIT.xml")
    public void invalidCredentials()
    {
        credentials.setUsername("jack@it-crowd.com.pl");
        credentials.setPassword("bbb");
        Assert.assertFalse(identity.isLoggedIn());
        identity.login();
        Assert.assertFalse(identity.isLoggedIn());
    }

    @MockFacesContextProducer
    public FacesContext mockFacesContext()
    {
        final Application application = Mockito.mock(Application.class);
        Mockito.when(application.getProjectStage()).thenReturn(ProjectStage.Development);
        final FacesContext facesContext = Mockito.mock(FacesContext.class);
        Mockito.when(facesContext.getApplication()).thenReturn(application);
        return facesContext;
    }

    @FacesContextRequired
    @ConversationScopeRequired
    @Test
    @UsingDataSet("com/exclusiveTransactionException/test/user/UserLoginCIT.xml")
    public void userLogIn()
    {
        credentials.setUsername("jack@it-crowd.com.pl");
        credentials.setPassword("aaaaa");
        Assert.assertFalse(identity.isLoggedIn());
        identity.login();
        Assert.assertTrue(identity.isLoggedIn());
    }
}

