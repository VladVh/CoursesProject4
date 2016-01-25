package ua.kiev.univ.cyb.dao.implement;


import ua.kiev.univ.cyb.dao.AbstractJDBCDao;
import ua.kiev.univ.cyb.dao.PersistException;
import ua.kiev.univ.cyb.entity.Order;
import ua.kiev.univ.cyb.entity.OrderMediator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Data access object pattern implementation for OrderMediator class.
 */
public class MySqlOrderMediatorDao extends AbstractJDBCDao<OrderMediator, Integer> {
    /**
     * Resource bundle with SQL queries.
     */
    private ResourceBundle queries = ResourceBundle.getBundle("query");

    /**
     * Private inner class, allows to set id field inside this class.
     */
    private class PersistOrderPart extends OrderMediator {
        @Override
        public void setId(Integer id) {
            super.setId(id);
        }
    }

    /**
     * @param connection context to work with datasource.
     */
    public MySqlOrderMediatorDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return queries.getString("ORDER_MEDIATOR.SELECT");
    }

    @Override
    public String getCreateQuery() {
        return queries.getString("ORDER_MEDIATOR.INSERT");
    }

    @Override
    public String getUpdateQuery() {
        return queries.getString("ORDER_MEDIATOR.UPDATE");
    }

    @Override
    public String getDeleteQuery() {
        return queries.getString("ORDER_MEDIATOR.DELETE");
    }

    @Override
    protected List<OrderMediator> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<OrderMediator> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistOrderPart orderPart = new PersistOrderPart();
                orderPart.setId(rs.getInt("id"));
                orderPart.setCount(rs.getInt("count"));
                result.add(orderPart);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, OrderMediator object) throws PersistException {
        try {
            statement.setInt(1, object.getOrder().getId());
            statement.setInt(2, object.getBook().getId());
            statement.setInt(3, object.getCount());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, OrderMediator object) throws PersistException {
        try {
            statement.setInt(1, object.getOrder().getId());
            statement.setInt(2, object.getBook().getId());
            statement.setInt(3, object.getCount());
            statement.setInt(4, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }


    @Override
    public OrderMediator create() throws PersistException {
        return persist(new OrderMediator());
    }

    /**
     * Get all parts of the order
     *
     * @param order object, representing order,
     * @return list of order parts, that order contains.
     * @throws PersistException
     */
    public List<OrderMediator> getAllBelonging(Order order) throws PersistException {
        List<OrderMediator> list;
        String sql = queries.getString("ORDER_MEDIATOR.ALLBELONGING");
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, order.getId());
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
