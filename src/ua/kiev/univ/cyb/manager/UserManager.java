package ua.kiev.univ.cyb.manager;

import org.mindrot.jbcrypt.BCrypt;
import ua.kiev.univ.cyb.dao.DaoFactory;
import ua.kiev.univ.cyb.dao.PersistException;
import ua.kiev.univ.cyb.dao.implement.MySqlDaoFactory;
import ua.kiev.univ.cyb.dao.implement.MySqlOrderDao;
import ua.kiev.univ.cyb.dao.implement.MySqlOrderMediatorDao;
import ua.kiev.univ.cyb.dao.implement.MySqlUserDao;
import ua.kiev.univ.cyb.database.ConnectionPool;
import ua.kiev.univ.cyb.entity.Order;
import ua.kiev.univ.cyb.entity.OrderMediator;
import ua.kiev.univ.cyb.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Provides functionality to do actions on objects from User class in persistent context.
 */
public class UserManager {

    /**
     * Factory to create dao objects
     */
    private static DaoFactory<Connection> factory = MySqlDaoFactory.getInstance();

    /**
     * Connection pool to create connections
     */
    private static ConnectionPool connectionPool = ConnectionPool.getInstance();

    /**
     * Returns user by his email
     *
     * @param email email of a user
     * @return user with such email
     * @throws PersistException if error occurs while retrieving
     */
    public User get(String email) throws PersistException {
        try (Connection connection = connectionPool.getConnection()) {
            try {
                MySqlUserDao userDao = (MySqlUserDao) factory.getDao(User.class, connection);
                MySqlOrderDao orderDao = (MySqlOrderDao) factory.getDao(Order.class, connection);
                MySqlOrderMediatorDao orderPartDao = (MySqlOrderMediatorDao) factory.getDao(OrderMediator.class, connection);

                User user = userDao.getByEmail(email);

                if (user != null) {
                    user.setOrders(orderDao.getAllBelonging(user));
                    for (Order o : user.getOrders()) {
                        o.setUser(user);
                        o.setOrderParts(orderPartDao.getAllBelonging(o));
                        for (OrderMediator om : o.getOrderParts()) {
                            om.setOrder(o);
                        }
                    }
                }
                return user;
            } catch (PersistException e) {
                throw e;
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    /**
     * Tries to log user into the system.
     *
     * @param email    email of user.
     * @param password
     * @return
     * @throws PersistException if any error while logging in occurs.
     */
    public User login(String email, String password) throws PersistException {
        User user = get(email);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    /**
     * Saves new user in datasource.
     *
     * @param firName  first name of user
     * @param secName  last name of user
     * @param email    email of user
     * @param password user password
     * @return created user with set id
     * @throws PersistException if any error while creating new user occurs.
     */
    public User create(String firName, String secName, String email, String password) throws PersistException {
        try (Connection connection = connectionPool.getConnection()) {
            try {
                MySqlUserDao userDao = (MySqlUserDao) factory.getDao(User.class, connection);

                User user = new User();
                user.setFirstName(firName);
                user.setSecondName(secName);
                user.setEmail(email);
                user.setPassword(password);

                return userDao.persist(user);
            } catch (PersistException e) {
                throw e;
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    /**
     * Returns all the users from the database
     *
     * @return list of users
     * @throws PersistException if any error occurs.
     */
    public List<User> getDefaulters() throws PersistException {
        try (Connection connection = connectionPool.getConnection()) {
            try {
                MySqlUserDao userDao = (MySqlUserDao) factory.getDao(User.class, connection);
                MySqlOrderDao orderDao = (MySqlOrderDao) factory.getDao(Order.class, connection);
                MySqlOrderMediatorDao orderPartDao = (MySqlOrderMediatorDao) factory.getDao(OrderMediator.class, connection);

                List<User> users = userDao.getAll();
                for (User user : users) {
                    user.setOrders(orderDao.getAllBelonging(user));
                    for (Order o : user.getOrders()) {
                        o.setUser(user);
                        o.setOrderParts(orderPartDao.getAllBelonging(o));
                        for (OrderMediator om : o.getOrderParts()) {
                            om.setOrder(o);
                        }
                    }
                }

                return users;
            } catch (PersistException e) {
                throw e;
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }
}
