package com.solar.calculator.config;

import jakarta.annotation.PostConstruct;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GlobalDataSource implements DataSource {


    private final int MAX_POOL_SIZE = 10;
    private final int MAX_TIMEOUT = 5;

    private String url;
    private String user;
    private String password;

    public GlobalDataSource(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    private final List<Connection> connectionPool = new ArrayList<>();
    private final List<Connection> usedConnections = new ArrayList<>();

    @PostConstruct
    public void initializePool() throws SQLException {
        for (int i = 0; i < MAX_POOL_SIZE / 2; i++) {
            connectionPool.add(createConnection());
        }
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public synchronized Connection getConnection() throws SQLException {
        if (connectionPool.isEmpty()) {
            if (usedConnections.size() < MAX_POOL_SIZE) {
                connectionPool.add(createConnection());
            } else {
                throw new RuntimeException("Maximum pool size reached, no available connections!");
            }
        }

        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        if (!connection.isValid(MAX_TIMEOUT)) {
            connection = createConnection();
        }
        usedConnections.add(connection);
        return connection;
    }

    public synchronized void releaseConnection(Connection connection) {
        usedConnections.remove(connection);
        connectionPool.add(connection);
    }

    @Override
    public Connection getConnection(String username, String password) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <T> T unwrap(Class<T> iface) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public java.io.PrintWriter getLogWriter() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void setLogWriter(java.io.PrintWriter out) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void setLoginTimeout(int seconds) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public int getLoginTimeout() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public java.util.logging.Logger getParentLogger() {
        throw new UnsupportedOperationException("Not implemented");
    }
}