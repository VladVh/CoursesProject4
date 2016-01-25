package ua.kiev.univ.cyb.dao.implement;


import ua.kiev.univ.cyb.dao.DaoFactory;
import ua.kiev.univ.cyb.dao.GenericDao;
import ua.kiev.univ.cyb.dao.PersistException;
import ua.kiev.univ.cyb.entity.Book;
import ua.kiev.univ.cyb.entity.Order;
import ua.kiev.univ.cyb.entity.OrderMediator;
import ua.kiev.univ.cyb.entity.User;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * Object factory to work with MySql database.
 * Implementation using Singleton pattern.
 */
public class MySqlDaoFactory implements DaoFactory<Connection> {
    /**
     * Instance of this class object.
     */
    private static MySqlDaoFactory instance;

    /**
     * Mappings to determine dao creator object for specified class.
     */
    private Map<Class, DaoCreator> creators;

    @Override
    public GenericDao getDao(Class dtoClass, Connection connection) throws PersistException {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new PersistException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(connection);
    }

    /**
     * Private constructor, initializes mappings.
     */
    private MySqlDaoFactory() {
        creators = new HashMap<>();

        creators.put(Order.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlOrderDao(connection);
            }
        });
        creators.put(User.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlUserDao(connection);
            }
        });
        creators.put(Book.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlBookDao(connection);
            }
        });
        creators.put(OrderMediator.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlOrderMediatorDao(connection);
            }
        });
    }

    /**
     * Get instance of MySqlDaoFactory class.
     *
     * @return instance of MySqlDaoFactory class.
     */
    public synchronized static MySqlDaoFactory getInstance() {
        if (instance == null) {
            instance = new MySqlDaoFactory();
        }
        return instance;
    }
}
