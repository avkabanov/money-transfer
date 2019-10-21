package com.revolut.kabanov.service.client;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.revolut.kabanov.dao.ClientDao;
import com.revolut.kabanov.model.Client;
import com.revolut.kabanov.model.exception.ClientNotFoundException;

/**
 * @author Kabanov Alexey
 */
@RunWith(MockitoJUnitRunner.class)
public class ClientCacheImplTest {
    
    @Captor ArgumentCaptor<Client> argumentCaptor;
    
    @Mock private ClientDao clientDao;
    private ClientCache clientCache; 
    
    @Before 
    public void setup() {
        Mockito.when(clientDao.createOrUpdate(Mockito.any())).thenAnswer(invocation -> invocation.getArguments()[0]);
        clientCache = new ClientCacheImpl(clientDao);
    }

    @Test
    public void shouldSaveInDBWhenSaveAndUpdateInvoked() {
        Client client = new Client(1L, "client1", BigDecimal.valueOf(100));
        clientCache.createOrUpdateClient(client);
        Mockito.verify(clientDao, Mockito.times(1)).createOrUpdate(argumentCaptor.capture());
        Assert.assertEquals("client1", argumentCaptor.getValue().getName());
        Assert.assertEquals(BigDecimal.valueOf(100), argumentCaptor.getValue().getBalance());
    }

    @Test
    public void shouldReadAllClientsFromDatabaseOnInit() {
        clientCache.initialize();
        Mockito.verify(clientDao, Mockito.times(1)).getAllClients();
    }

    @Test
    public void shouldReturnClientWhenClientExist() throws ClientNotFoundException {
        Client expectedClient = new Client(1L, "client1", BigDecimal.valueOf(100));
        clientCache.createOrUpdateClient(expectedClient);
        Client actualClient = clientCache.getClient(1L);
        
        Assert.assertEquals(expectedClient, actualClient);
    }

    @Test(expected = ClientNotFoundException.class)
    public void shouldThrowExceptionWhenClientNotFound() throws ClientNotFoundException {
        Client expectedClient = new Client(1L, "client1", BigDecimal.valueOf(100));
        clientCache.createOrUpdateClient(expectedClient);
        clientCache.getClient(2L);
    }

    @Test
    public void shouldGetAllClientsWhenGetAllClientsInvoked() {
        Client client1 = new Client(1L, "client1", BigDecimal.valueOf(80));
        Client client2 = new Client(2L, "client2", BigDecimal.valueOf(100));
        Set<Client> expectedSet = new HashSet<>();
        expectedSet.add(client1);
        expectedSet.add(client2);
        
        clientCache.createOrUpdateClient(client1);
        clientCache.createOrUpdateClient(client2);
        Set<Client> allClients = new HashSet<>(clientCache.getAllClients());
        
        Assert.assertEquals(2, allClients.size());
        Assert.assertEquals(expectedSet, allClients);
    }

}