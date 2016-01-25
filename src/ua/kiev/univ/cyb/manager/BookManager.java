package ua.kiev.univ.cyb.manager;

import ua.kiev.univ.cyb.dao.DaoFactory;
import ua.kiev.univ.cyb.dao.PersistException;
import ua.kiev.univ.cyb.dao.implement.MySqlBookDao;
import ua.kiev.univ.cyb.dao.implement.MySqlDaoFactory;
import ua.kiev.univ.cyb.database.ConnectionPool;
import ua.kiev.univ.cyb.entity.Book;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Provides functionality to do any actions on objects of the Book class in persistent context.
 */
public class BookManager {
    /**
     * Factory to create dao objects.
     */
    private static DaoFactory<Connection> factory = MySqlDaoFactory.getInstance();

    /**
     * Connection pool to create connections to datasource.
     */
    private static ConnectionPool connectionPool = ConnectionPool.getInstance();

    /**
     * Saves new product with described characteristics in datasource.
     *
     * @param name      name of a new book.
     * @param author    author of new book.
     * @param published date when book was published
     * @return created book with set id.
     * @throws PersistException if error while saving occurs, rolls back all changes.
     */
    public Book create(String name, String author, String description, Date published, String image)
            throws PersistException {
        try (Connection connection = connectionPool.getConnection()) {
            MySqlBookDao bookDao = (MySqlBookDao) factory.getDao(Book.class, connection);

            Book book = new Book();
            book.setName(name);
            book.setAuthor(author);
            book.setDescription(description);
            book.setPublished(new java.sql.Date(published.getTime()));
            book.setImage(image);

            return bookDao.persist(book);
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    /**
     * Returns book by it's id in datasource.
     *
     * @param id identificator of object.
     * @return book, saved in datasource.
     * @throws PersistException if error while getting occurs.
     */
    public Book get(Integer id) throws PersistException {
        try (Connection connection = connectionPool.getConnection()) {
            MySqlBookDao bookDao = (MySqlBookDao) factory.getDao(Book.class, connection);
            return bookDao.getByPK(id);
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    /**
     * Returns all the books from the database
     *
     * @return list of books
     * @throws PersistException if error while getting occurs
     */
    public List<Book> getBooks() throws PersistException {
        try (Connection connection = connectionPool.getConnection()) {
            try {
                MySqlBookDao bookDao = (MySqlBookDao) factory.getDao(Book.class, connection);
                List<Book> books = bookDao.getAll();
                return books;
            } catch (PersistException e) {
                throw e;
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }
}
