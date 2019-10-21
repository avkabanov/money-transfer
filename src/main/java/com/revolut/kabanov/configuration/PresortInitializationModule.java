package com.revolut.kabanov.configuration;

import com.google.inject.AbstractModule;
import com.revolut.kabanov.env.prestart_initialization.ClientCacheInitialization;
import com.revolut.kabanov.env.prestart_initialization.PrestartInitialization;

/**
 * @author Kabanov Alexey
 */
public class PresortInitializationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PrestartInitialization.class).to(ClientCacheInitialization.class);
    }
}
