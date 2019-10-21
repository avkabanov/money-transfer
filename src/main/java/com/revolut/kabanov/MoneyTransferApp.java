package com.revolut.kabanov;

import java.io.IOException;
import java.sql.SQLException;

import com.google.inject.Inject;
import com.revolut.kabanov.env.db.DatabaseManager;
import com.revolut.kabanov.env.prestart_initialization.PrestartInitialization;
import com.revolut.kabanov.env.server.HTTPServerManager;
import com.revolut.kabanov.model.app_properties.ApplicationProperties;

/**
 * @author Алексей
 */
public class MoneyTransferApp {

    private DatabaseManager databaseManager;
    private HTTPServerManager httpServerManager;
    private PrestartInitialization prestartInitialization;
    private ApplicationProperties applicationProperties;

    @Inject
    public MoneyTransferApp(DatabaseManager databaseManager,
                            HTTPServerManager httpServerManager,
                            PrestartInitialization prestartInitialization,
                            ApplicationProperties applicationProperties) {
        this.databaseManager = databaseManager;
        this.httpServerManager = httpServerManager;
        this.prestartInitialization = prestartInitialization;
        this.applicationProperties = applicationProperties;
    }

    public void initAndRun() throws IOException, SQLException {
        databaseManager.initDatabase();
        prestartInitialization.initialize();
        httpServerManager.startServer();
        System.out.println("Money Transfer Service started and available at " + applicationProperties.getBaseUrl() +
                "\nHit enter to stop it...");
        System.in.read();
        httpServerManager.stopServer();
    }
}
