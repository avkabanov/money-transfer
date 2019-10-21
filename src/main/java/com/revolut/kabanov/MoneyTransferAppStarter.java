package com.revolut.kabanov;

import java.io.IOException;
import java.sql.SQLException;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.revolut.kabanov.configuration.ApplicationConfigurationModule;
import com.revolut.kabanov.configuration.ApplicationPropertiesModule;
import com.revolut.kabanov.configuration.EnvironmentModule;
import com.revolut.kabanov.configuration.PresortInitializationModule;

/**
 * @author Kabanov Alexey
 */
public class MoneyTransferAppStarter {

    public static void main(String[] args) throws IOException, SQLException {
        Injector injector = Guice.createInjector(new ApplicationConfigurationModule(),
                new EnvironmentModule(), 
                new PresortInitializationModule(), 
                new ApplicationPropertiesModule());

        MoneyTransferApp moneyTransferApp = injector.getInstance(MoneyTransferApp.class);
        moneyTransferApp.initAndRun();
    }
}
