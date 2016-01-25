package ua.kiev.univ.cyb.dao.implement;

import ua.kiev.univ.cyb.dao.AbstractJDBCDao;
import ua.kiev.univ.cyb.dao.PersistException;
import ua.kiev.univ.cyb.entity.Order;
import ua.kiev.univ.cyb.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Data access object pattern implementation for Order class.
 */
public class MySqlOrderDao extends AbstractJDBCDao<Order, Integer> {
    /**
     * Resource bundle with SQL queries.
     */
    private ResourceBundle queries = ResourceBundle.getBundle("query");

    /**
     * Private inner class, allows to set id field inside this class.
     */
    private class PersistOrder extends Order {
        public void setId(int id) {
            super.setId(id);
        }
    }

    /**
     * @param connection context to work with datasource.
     */
    public MySqlOrderDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return queries.getString("ORDER.SELECT");
    }

    @Override
    public String getCreateQuery() {
        return queries.getString("ORDER.INSERT");
    }

    @Override
    public String getUpdateQuery() {
        return queries.getString("ORDER.UPDATE");
    }

    @Override
    public String getDeleteQuery() {
        return queries.getString("ORDER.DELETE");
    }

    @Override
    public Order create() throws PersistException {
        Order order = new Order();
        return persist(order);
    }

    @Override
    protected List<Order> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<Order> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistOrder order = new PersistOrder();
                order.setId(rs.getInt("id"));
                order.setDate(rs.getDate("date"));
                order.setAtLibrary(rs.getBoolean("type"));
                order.setReturned(rs.getBoolean("returned"));
                result.add(order);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Order object) throws PersistException {
        try {
            statement.setInt(1, object.getUser().getId());
            statement.setBoolean(2, object.isAtLibrary());
            statement.setDate(3, convert(object.getDate()));
            statement.setBoolean(4, object.isReturned());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Order object) throws PersistException {
        try {
            statement.setInt(1, object.getUser().getId());
            statement.setBoolean(2, object.isAtLibrary());
            statement.setDate(3, convert(object.getDate()));
            statement.setBoolean(4, object.isReturned());
            statement.setInt(5, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    /**
     * Utility method to convert date formats in order to correctly save it into database.
     *
     * @param date date to convert.
     * @return converted date.
     */
    protected java.sql.Date convert(java.util.Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }

    /**
     * @param user object, representing user
     * @return list of orders, that user has made.
     * @throws PersistException
     */
    public List<Order> getAllBelonging(User user) throws PersistException {
        List<Order> list;
        String sql = queries.getString("ORDER.SELECT.ALLBELONGING");
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getId());
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }
}
