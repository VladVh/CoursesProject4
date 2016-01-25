package ua.kiev.univ.cyb.dao.implement;

import ua.kiev.univ.cyb.dao.AbstractJDBCDao;
import ua.kiev.univ.cyb.dao.PersistException;
import ua.kiev.univ.cyb.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Data access object pattern implementation for Book class.
 */
public class MySqlBookDao extends AbstractJDBCDao<Book, Integer> {
    /**
     * Resource bundle with SQL queries.
     */
    private ResourceBundle queries = ResourceBundle.getBundle("query");

    /**
     * Private inner class, allows to set id field inside this class.
     */
    private class PersistBook extends Book {
        @Override
        public void setId(Integer id) {
            super.setId(id);
        }
    }

    /**
     * @param connection context to work with datasource.
     */
    public MySqlBookDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return queries.getString("BOOK.SELECT");
    }

    @Override
    public String getCreateQuery() {
        return queries.getString("BOOK.INSERT");
    }

    @Override
    public String getUpdateQuery() {
        return queries.getString("BOOK.UPDATE");
    }

    @Override
    public String getDeleteQuery() {
        return queries.getString("BOOK.DELETE");
    }

    @Override
    protected List<Book> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<Book> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistBook book = new PersistBook();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setDescription(rs.getString("description"));
                book.setImage(rs.getString("image"));
                book.setPublished(rs.getDate("published"));
                result.add(book);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Book object) throws PersistException {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getAuthor());
            statement.setString(3, object.getDescription());
            statement.setDate(4, convert(object.getPublished()));
            statement.setString(5, object.getImage());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Book object) throws PersistException {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getAuthor());
            statement.setString(3, object.getDescription());
            statement.setDate(4, convert(object.getPublished()));
            statement.setString(5, object.getImage());
            statement.setInt(6, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    protected java.sql.Date convert(java.util.Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }

    @Override
    public Book create() throws PersistException {
        return persist(new Book());
    }

}
