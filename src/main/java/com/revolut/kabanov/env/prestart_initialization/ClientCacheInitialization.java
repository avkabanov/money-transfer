package com.revolut.kabanov.env.prestart_initialization;

import com.google.inject.Inject;
import com.revolut.kabanov.service.client.ClientCache;

/**
 * @author Kabanov Alexey
 */
public class ClientCacheInitialization implements PrestartInitialization {
    
    private ClientCache clientCache;

    @Inject
    public ClientCacheInitialization(ClientCache clientCache) {
        this.clientCache = clientCache;
    }

    @Override
    public void initialize() {
        clientCache.initialize();
    }
}
