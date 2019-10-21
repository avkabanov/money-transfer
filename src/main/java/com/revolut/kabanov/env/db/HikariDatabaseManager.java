package com.revolut.kabanov.env.db;

import java.sql.SQLException;

import com.zaxxer.hikari.HikariDataSource;

/**
 * @author Kabanov Alexey
 */
public class HikariDatabaseManager implements DatabaseManager {

    @Override
    public void initDatabase() throws SQLException {
        HikariDataSource ds = new HikariDataSource();
        //initializing the in-memry H2 database and initialize it by the schema and some initial data
        ds.setJdbcUrl("jdbc:h2:mem:test;" + "INIT=RUNSCRIPT FROM 'classpath:schema.sql'");
        ds.getConnection();
    }

}
