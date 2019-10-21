package com.revolut.kabanov.configuration;

import com.google.inject.AbstractModule;
import com.revolut.kabanov.env.db.DatabaseManager;
import com.revolut.kabanov.env.db.HikariDatabaseManager;
import com.revolut.kabanov.env.server.GrizzlyServerManager;
import com.revolut.kabanov.env.server.HTTPServerManager;

/**
 * @author Kabanov Alexey
 */
public class EnvironmentModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DatabaseManager.class).to(HikariDatabaseManager.class);
        bind(HTTPServerManager.class).to(GrizzlyServerManager.class);
    }
}
