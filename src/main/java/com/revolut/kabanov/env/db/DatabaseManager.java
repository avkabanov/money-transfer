package com.revolut.kabanov.env.db;

import java.sql.SQLException;

/**
 * @author Kabanov Alexey
 */
public interface DatabaseManager {
    
    void initDatabase() throws SQLException;
}
