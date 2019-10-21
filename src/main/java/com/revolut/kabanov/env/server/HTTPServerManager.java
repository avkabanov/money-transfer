package com.revolut.kabanov.env.server;

import java.io.IOException;

/**
 * @author Kabanov Alexey
 */
public interface HTTPServerManager {
    
    void startServer() throws IOException;
    
    void stopServer();
}
