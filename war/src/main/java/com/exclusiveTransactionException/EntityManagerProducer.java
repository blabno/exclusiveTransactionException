package com.exclusiveTransactionException;

import org.jboss.solder.core.ExtensionManaged;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class EntityManagerProducer {
// ------------------------------ FIELDS ------------------------------

    private static final String PERSISTENCE_UNIT_NAME = "exclusiveTransactionException-unit";

    @ExtensionManaged
    @ConversationScoped
    @Produces
    @PersistenceUnit(unitName = PERSISTENCE_UNIT_NAME)
    private EntityManagerFactory emf;
}
