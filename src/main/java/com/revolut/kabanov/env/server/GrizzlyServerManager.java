package com.revolut.kabanov.env.server;

import java.io.IOException;

import org.glassfish.grizzly.http.server.HttpServer;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.revolut.kabanov.model.app_properties.ApplicationProperties;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.core.spi.component.ioc.IoCComponentProviderFactory;
import com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory;

/**
 * @author Kabanov Alexey
 */
public class GrizzlyServerManager implements HTTPServerManager {

    private HttpServer server;
    private Injector guiceInjector;
    private ApplicationProperties applicationProperties;

    @Inject
    public GrizzlyServerManager(Injector guiceInjector, ApplicationProperties applicationProperties) {
        this.guiceInjector = guiceInjector;
        this.applicationProperties = applicationProperties;
    }

    @Override
    public void startServer() throws IOException {
        ResourceConfig rc = new PackagesResourceConfig("com.revolut.kabanov");
        IoCComponentProviderFactory ioc = new GuiceComponentProviderFactory(rc, guiceInjector);
        server = GrizzlyServerFactory.createHttpServer(applicationProperties.getBaseUrl(), rc, ioc);
    }

    @Override
    public void stopServer() {
        if (server == null) {
            throw new IllegalStateException("Server was not started");
        }
        server.shutdown();
    }
}
