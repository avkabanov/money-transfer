package com.revolut.kabanov.configuration;

import java.io.IOException;
import java.util.Properties;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;
import com.revolut.kabanov.model.app_properties.ApplicationProperties;
import com.revolut.kabanov.model.app_properties.ApplicationPropertiesFactory;
import com.revolut.kabanov.model.exception.ValidationException;

/**
 * @author Kabanov Alexey
 */
public class ApplicationPropertiesModule extends AbstractModule {
    
    @Override
    protected void configure() {
        try {
            Properties props = new Properties();
            props.load(getClass()
                    .getClassLoader().getResourceAsStream("application.properties"));
            Names.bindProperties(binder(), props);
        } catch (IOException e) {
            throw new RuntimeException("Could not load config: ", e);
        }
    }

    @Provides
    public ApplicationProperties providesApplicationProperties(
            ApplicationPropertiesFactory applicationPropertiesFactory) throws ValidationException {
        return applicationPropertiesFactory.createApplicationProperties();
    }
}
    