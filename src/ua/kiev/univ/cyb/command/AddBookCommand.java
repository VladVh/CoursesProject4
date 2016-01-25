package ua.kiev.univ.cyb.command;


import org.apache.log4j.Logger;
import ua.kiev.univ.cyb.dao.PersistException;
import ua.kiev.univ.cyb.manager.BookManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Class encapsulates business logic. Provides an opportunity to add a new book by administrator.
 */
public class AddBookCommand implements Command {
    /**
     * Manager to work with books in persistent context.
     */
    private BookManager bookManager = new BookManager();

    /**
     * Names of parameters in request.
     */
    private static final String PARAM_BOOK_NAME = "name";
    private static final String PARAM_BOOK_AUTHOR = "author";
    private static final String PARAM_BOOK_DESCRIPTION = "description";
    private static final String PARAM_BOOK_IMAGE = "image";


    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        String page = "books.jsp";
        boolean redirect = true;

        String name = request.getParameter(PARAM_BOOK_NAME);
        String author = request.getParameter(PARAM_BOOK_AUTHOR);
        String description = request.getParameter(PARAM_BOOK_DESCRIPTION);
        Date date = new Date();
        String image = request.getParameter(PARAM_BOOK_IMAGE);

        try {
            bookManager.create(name, author, description, date, image);
        } catch (PersistException e) {
            Logger.getLogger(getClass()).error(e);
            page = "index.jsp";
            redirect = false;
            request.setAttribute("message", getResourceBundle(request).getString("book.error"));
        }
        return new Page(page, redirect);
    }
}
