package com.revolut.kabanov.service.client;

import java.util.Collection;

import com.revolut.kabanov.model.Client;
import com.revolut.kabanov.model.exception.ClientNotFoundException;

/**
 * @author Алексей
 * 
 */
public interface ClientCache {

    Client getClient(long id) throws ClientNotFoundException;

    Collection<Client> getAllClients();

    /**
     * @return newly created client
     */
    Client createOrUpdateClient(Client client);

    void initialize();
}
