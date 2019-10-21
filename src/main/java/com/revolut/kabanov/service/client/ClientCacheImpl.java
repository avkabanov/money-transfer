package com.revolut.kabanov.service.client;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnull;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.revolut.kabanov.dao.ClientDao;
import com.revolut.kabanov.model.Client;
import com.revolut.kabanov.model.exception.ClientNotFoundException;

/**
 * @author Алексей
 */
@Singleton
public class ClientCacheImpl implements ClientCache {

    private final ClientDao clientDao;
    private final Map<Long, Client> clientCache = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    @Inject
    public ClientCacheImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Nonnull
    @Override
    public Client getClient(long id) throws ClientNotFoundException {
        readLock.lock();
        try {
            Client client = clientCache.get(id);
            if (client == null) {
                throw new ClientNotFoundException("Unable to find client with id: " + id);
            }
            return client;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Collection<Client> getAllClients() {
        readLock.lock();
        try {
            return clientCache.values();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Client createOrUpdateClient(Client client) {
        writeLock.lock();
        try {
            Client databaseClient = clientDao.createOrUpdate(client);
            clientCache.put(databaseClient.getId(), databaseClient);
            return databaseClient;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void initialize() {
        writeLock.lock();
        try {
            Collection<Client> clients = clientDao.getAllClients();
            for (Client client : clients) {
                clientCache.put(client.getId(), client);
            }
        } finally {
            writeLock.unlock();
        }
    }
}
