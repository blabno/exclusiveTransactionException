package com.exclusiveTransactionException.test;

import com.exclusiveTransactionException.test.mocks.ConversationMock;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.descriptor.api.beans10.BeansDescriptor;
import pl.com.it_crowd.utils.test.LibraryResolver;

import java.io.File;

public class Packager {
// ------------------------------ FIELDS ------------------------------

    private WebArchive archive;

// --------------------------- CONSTRUCTORS ---------------------------

    public Packager(Class testClass)
    {
        archive = ShrinkWrap.create(WebArchive.class, testClass.getSimpleName() + ".war");
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public WebArchive getArchive()
    {
        return archive;
    }

// -------------------------- OTHER METHODS --------------------------

    public Packager addAllClasses()
    {
        archive.addPackages(true, "com.exclusiveTransactionException");
        archive.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml");
        archive.addAsResource("META-INF/seam-beans.xml", "META-INF/seam-beans.xml");
        return this;
    }

    public Packager addAllLibraries()
    {
        archive.addAsLibraries(LibraryResolver.resolve("commons-logging"));
        archive.addAsLibraries(LibraryResolver.resolve("commons-pool"));
        archive.addAsLibraries(LibraryResolver.resolve("freemarker"));
        archive.addAsLibraries(LibraryResolver.resolve("joda-time"));
        archive.addAsLibraries(LibraryResolver.resolve("junit"));
        archive.addAsLibraries(LibraryResolver.resolve("picketlink-idm-api"));
        archive.addAsLibraries(LibraryResolver.resolve("picketlink-idm-common"));
        archive.addAsLibraries(LibraryResolver.resolve("picketlink-idm-core"));
        archive.addAsLibraries(LibraryResolver.resolve("picketlink-idm-spi"));
        archive.addAsLibraries(LibraryResolver.resolve("poi"));
        archive.addAsLibraries(LibraryResolver.resolve("prettyfaces-jsf2"));
        archive.addAsLibraries(LibraryResolver.resolve("richfaces-components-api"));
        archive.addAsLibraries(LibraryResolver.resolve("richfaces-components-ui"));
        archive.addAsLibraries(LibraryResolver.resolve("richfaces-core-api"));
        archive.addAsLibraries(LibraryResolver.resolve("richfaces-core-impl"));
        archive.addAsLibraries(LibraryResolver.resolve("seam-conversation-spi"));
        archive.addAsLibraries(LibraryResolver.resolve("seam-conversation-weld"));
        archive.addAsLibraries(LibraryResolver.resolve("seam-faces"));
        archive.addAsLibraries(LibraryResolver.resolve("seam-faces-api"));
        archive.addAsLibraries(LibraryResolver.resolve("seam-international"));
        archive.addAsLibraries(LibraryResolver.resolve("seam-international-api"));
        archive.addAsLibraries(LibraryResolver.resolve("seam-mail"));
        archive.addAsLibraries(LibraryResolver.resolve("seam-mail-api"));
        archive.addAsLibraries(LibraryResolver.resolve("seam-persistence"));
        archive.addAsLibraries(LibraryResolver.resolve("seam-persistence-api"));
        archive.addAsLibraries(LibraryResolver.resolve("seam-security"));
        archive.addAsLibraries(LibraryResolver.resolve("seam-security-api"));
        archive.addAsLibraries(LibraryResolver.resolve("seam-transaction"));
        archive.addAsLibraries(LibraryResolver.resolve("seam-transaction-api"));
        archive.addAsLibraries(LibraryResolver.resolve("solder-api"));
        archive.addAsLibraries(LibraryResolver.resolve("solder-impl"));
        archive.addAsLibraries(LibraryResolver.resolve("solder-logging"));
        return this;
    }

    public Packager addAllTemplateResources()
    {
        return this;
    }

    public Packager addAllViews()
    {
        return this;
    }

    public Packager addCITLibraries()
    {
        archive.addAsLibraries(LibraryResolver.resolve("freemarker"));
        archive.addAsLibraries(LibraryResolver.resolve("picketlink-idm-api"));
        archive.addAsLibraries(LibraryResolver.resolve("picketlink-idm-common"));
        archive.addAsLibraries(LibraryResolver.resolve("picketlink-idm-core"));
        archive.addAsLibraries(LibraryResolver.resolve("picketlink-idm-spi"));
        archive.addAsLibraries(LibraryResolver.resolve("poi"));
        archive.addAsLibraries(LibraryResolver.resolve("richfaces-components-api"));
        archive.addAsLibraries(LibraryResolver.resolve("richfaces-core-api"));
        archive.addAsLibraries(LibraryResolver.resolve("seam-faces"));
        archive.addAsLibraries(LibraryResolver.resolve("seam-faces-api"));
        archive.addAsLibraries(LibraryResolver.resolve("seam-international"));
        archive.addAsLibraries(LibraryResolver.resolve("seam-international-api"));
        archive.addAsLibraries(LibraryResolver.resolve("seam-jms"));
        archive.addAsLibraries(LibraryResolver.resolve("seam-jms-api"));
        archive.addAsLibraries(LibraryResolver.resolve("seam-mail"));
        archive.addAsLibraries(LibraryResolver.resolve("seam-mail-api"));
        archive.addAsLibraries(LibraryResolver.resolve("seam-persistence"));
        archive.addAsLibraries(LibraryResolver.resolve("seam-persistence-api"));
        archive.addAsLibraries(LibraryResolver.resolve("seam-security"));
        archive.addAsLibraries(LibraryResolver.resolve("seam-security-api"));
        archive.addAsLibraries(LibraryResolver.resolve("seam-transaction"));
        archive.addAsLibraries(LibraryResolver.resolve("seam-transaction-api"));
        archive.addAsLibraries(LibraryResolver.resolve("solder-api"));
        archive.addAsLibraries(LibraryResolver.resolve("solder-impl"));
        archive.addAsLibraries(LibraryResolver.resolve("solder-logging"));
        archive.addAsLibraries(LibraryResolver.resolve("mockito-all"));
        return this;
    }

    public Packager addClass(Class<?> clazz)
    {
        archive.addClass(clazz);
        return this;
    }

    public Packager addPropertyFiles()
    {
        return this;
    }

    public WebArchive buildCITPackage()
    {
        final BeansDescriptor beansDescriptor = getBeansDescriptor();
        beansDescriptor.getOrCreateAlternatives().clazz(ConversationMock.class.getCanonicalName());
        addClass(ConversationMock.class);
        this.addCITLibraries()
            .addAllClasses()
            .addJRebelIfExists()
            .getArchive()
            .addAsWebInfResource(new StringAsset(beansDescriptor.exportAsString()), "beans.xml");
        archive.delete("WEB-INF/classes/com/exclusiveTransactionException/web/SessionInvalidator.class");
        return archive;
    }

    public WebArchive buildPackage()
    {
        return this.addAllLibraries()
            .addAllClasses()
            .addAllTemplateResources()
            .addPropertyFiles()
            .addAllViews()
            .addRichFacesRelatedAssets()
            .addJRebelIfExists()
            .getArchive()
            .addAsWebInfResource(new File("src/main/webapp/WEB-INF/faces-config.xml"), "faces-config.xml")
            .addAsWebInfResource(new File("src/main/webapp/WEB-INF/pretty-config.xml"), "pretty-config.xml")
            .setWebXML(new File("target/exclusiveTransactionException/WEB-INF/web.xml"))
            .addAsWebInfResource(new StringAsset(getBeansDescriptor().exportAsString()), "beans.xml");
    }

    private Packager addJRebelIfExists()
    {
        final File jrebelFile = new File("target/classes/rebel.xml");
        if (jrebelFile.exists()) {
            archive.addAsResource(jrebelFile, "rebel.xml");
        } else {
            System.err.println(jrebelFile.getAbsolutePath() + " does not exist and won't be packaged");
        }
        return this;
    }

    private Packager addRichFacesRelatedAssets()
    {
        return this;
    }

    private BeansDescriptor getBeansDescriptor()
    {
        return Descriptors.create(BeansDescriptor.class).getOrCreateInterceptors().up().getOrCreateAlternatives().up();
    }
}

