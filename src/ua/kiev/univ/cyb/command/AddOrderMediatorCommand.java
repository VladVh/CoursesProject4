package ua.kiev.univ.cyb.command;


import org.apache.log4j.Logger;
import ua.kiev.univ.cyb.dao.PersistException;
import ua.kiev.univ.cyb.entity.Order;
import ua.kiev.univ.cyb.entity.OrderMediator;
import ua.kiev.univ.cyb.entity.User;
import ua.kiev.univ.cyb.manager.BookManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class encapsulates business logic. Provides an opportunity to add a book to the order by user.
 */
public class AddOrderMediatorCommand implements Command {

    /**
     * Manager to work with products in persistent context.
     */
    private BookManager bookManager = new BookManager();

    /**
     * Name of parameter in request.
     */
    private static final String PARAM_BOOK_ID = "id";

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        String page = "books.jsp";
        boolean redirect = true;

        Integer bookId = Integer.valueOf(request.getParameter(PARAM_BOOK_ID));

        Order order = (Order) request.getSession().getAttribute("currentOrder");
        User user = (User) request.getSession().getAttribute("currentUser");

        if (order == null) {
            order = new Order();
            order.setUser(user);
        }

        boolean found = false;
        for (OrderMediator om : order.getOrderParts()) {
            if (om.getBook().getId().equals(bookId)) {
                found = true;
                om.setCount(om.getCount() + 1);
            }
        }

        if (!found) {
            try {
                OrderMediator newPart = new OrderMediator();
                newPart.setCount(1);
                newPart.setBook(bookManager.get(bookId));
                newPart.setOrder(order);

                order.addOrderMediator(newPart);
                request.getSession().setAttribute("currentOrder", order);
            } catch (PersistException e) {
                Logger.getLogger(this.getClass()).error(e.getMessage());
                String error = getResourceBundle(request).getString("book.error");
                request.setAttribute("message", error);
                page = "index.jsp";
            }

        }
        return new Page(page, redirect);
    }
}
