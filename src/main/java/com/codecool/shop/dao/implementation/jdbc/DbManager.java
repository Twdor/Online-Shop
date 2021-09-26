package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.config.ApplicationProperties;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DbManager {

    public DbManager(){}

    protected static DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        ApplicationProperties properties = new ApplicationProperties();

        String dbName = properties.readProperty("DATABASE");
        String user = properties.readProperty("USERNAME");
        String password = properties.readProperty("PASSWORD");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
