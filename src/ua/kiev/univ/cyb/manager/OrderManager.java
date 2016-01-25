package ua.kiev.univ.cyb.manager;

import ua.kiev.univ.cyb.dao.DaoFactory;
import ua.kiev.univ.cyb.dao.PersistException;
import ua.kiev.univ.cyb.dao.implement.MySqlDaoFactory;
import ua.kiev.univ.cyb.dao.implement.MySqlOrderDao;
import ua.kiev.univ.cyb.dao.implement.MySqlOrderMediatorDao;
import ua.kiev.univ.cyb.database.ConnectionPool;
import ua.kiev.univ.cyb.entity.Order;
import ua.kiev.univ.cyb.entity.OrderMediator;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Provides functionality to do any actions on objects of the Order class in persistent context.
 */
public class OrderManager {
    /**
     * Factory to create dao objects.
     */
    private static DaoFactory<Connection> factory = MySqlDaoFactory.getInstance();

    /**
     * Connection pool to create connections to datasource.
     */
    private static ConnectionPool connectionPool = ConnectionPool.getInstance();

    /**
     * Saves order into datasource.
     *
     * @param order object to save, has no id.
     * @return order with the same fields and set id.
     * @throws PersistException if error while saving occurs, rolls back all changes.
     */
    public Order create(Order order) throws PersistException {
        try (Connection connection = connectionPool.getConnection()) {
            Order insOrder;

            connection.setAutoCommit(false);
            try {
                MySqlOrderDao orderDao = (MySqlOrderDao) factory.getDao(Order.class, connection);
                MySqlOrderMediatorDao orderPartDao = (MySqlOrderMediatorDao) factory.getDao(OrderMediator.class, connection);

                insOrder = orderDao.persist(order);
                for (OrderMediator om : order.getOrderParts()) {
                    om.setOrder(insOrder);
                    insOrder.getOrderParts().add(orderPartDao.persist(om));
                }
                connection.commit();
            } catch (PersistException ex) {
                connection.rollback();
                connection.setAutoCommit(true);
                throw ex;
            }

            connection.setAutoCommit(true);
            return insOrder;
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    /**
     * Updates order that is already saved in datasource.
     *
     * @param order object to update.
     * @throws PersistException if error while updating occurs, rolls back all changes.
     */
    public void update(Order order) throws PersistException {
        try (Connection connection = connectionPool.getConnection()) {
            MySqlOrderDao orderDao = (MySqlOrderDao) factory.getDao(Order.class, connection);
            orderDao.update(order);
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }
}

