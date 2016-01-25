package ua.kiev.univ.cyb.filter;


import org.apache.log4j.Logger;
import ua.kiev.univ.cyb.command.Page;
import ua.kiev.univ.cyb.dao.PersistException;
import ua.kiev.univ.cyb.entity.Book;
import ua.kiev.univ.cyb.manager.BookManager;

import javax.servlet.*;
import java.io.IOException;
import java.util.List;


/**
 * Filter, that loads and attaches products list to request before entering products page
 */
public class BookFilter implements Filter {
    private BookManager bookManager;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        bookManager = new BookManager();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        Page nextPage = new Page("books.jsp", false);

        try {
            List<Book> books = bookManager.getBooks();
            request.setAttribute("books", books);
        } catch (PersistException e) {
            Logger.getLogger(getClass()).error(e.getMessage());
            request.setAttribute("message", "Something went wrong. Please, try again later");
            nextPage.setPage("index.jsp");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage.getPage());
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {

    }
}
