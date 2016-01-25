package ua.kiev.univ.cyb.database;

import org.apache.commons.dbcp2.BasicDataSource;
import ua.kiev.univ.cyb.dao.PersistException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Pool of connections to database. Implementation with use of a Singleton pattern.
 */
public class ConnectionPool {
    /**
     * Datasource to get pooled connections.
     */
    private BasicDataSource dataSource;

    /**
     * Instance of this class.
     */
    private static ConnectionPool instance;

    /**
     * Private constructor. Initializes datasource and configures it.
     */
    private ConnectionPool() {
        dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:mysql://localhost:3306/library?autoReconnect=true&useUnicode=true&characterEncoding=Cp1251");

        dataSource.setInitialSize(2);
        dataSource.setMaxIdle(10);
        dataSource.setMaxTotal(15);
    }

    /**
     * @return instance of this class.
     */
    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    /**
     * @return connection with database, retrieved from datasource.
     * @throws PersistException if error while getting connection occurs.
     */
    public synchronized Connection getConnection() throws PersistException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }
}
