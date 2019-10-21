package com.revolut.kabanov.dao;

import java.util.Collection;

import javax.annotation.Nullable;

import com.revolut.kabanov.model.Client;

/**
 * @author Алексей
 * 
 */
public interface ClientDao {
    
    /**
     * @return client instance persisted to database 
     */
    Client createOrUpdate(Client client);
    
    Collection<Client> getAllClients();
    
    @Nullable
    Client getClient(long id);
}
