package com.revolut.kabanov.configuration;

import com.google.inject.AbstractModule;
import com.revolut.kabanov.dao.ClientDao;
import com.revolut.kabanov.dao.ClientDaoImpl;
import com.revolut.kabanov.service.client.ClientCache;
import com.revolut.kabanov.service.client.ClientCacheImpl;
import com.revolut.kabanov.service.client.ClientValidator;
import com.revolut.kabanov.service.client.ClientValidatorImpl;
import com.revolut.kabanov.service.transaction.TransactionService;
import com.revolut.kabanov.service.transaction.TransactionServiceImpl;
import com.revolut.kabanov.service.transaction.TransactionValidator;
import com.revolut.kabanov.service.transaction.TransactionValidatorImpl;

public class ApplicationConfigurationModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ClientValidator.class).to(ClientValidatorImpl.class);
        bind(ClientDao.class).to(ClientDaoImpl.class);
        bind(ClientCache.class).to(ClientCacheImpl.class);
        bind(TransactionService.class).to(TransactionServiceImpl.class);
        bind(TransactionValidator.class).to(TransactionValidatorImpl.class);
    }
}
