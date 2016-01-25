package ua.kiev.univ.cyb.command;

import org.apache.log4j.Logger;
import ua.kiev.univ.cyb.dao.PersistException;
import ua.kiev.univ.cyb.entity.Order;
import ua.kiev.univ.cyb.entity.User;
import ua.kiev.univ.cyb.manager.OrderManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class encapsulates business logic. Provides an opportunity to return books to the library.
 */
public class ReturnBooksCommand implements Command {

    private OrderManager orderManager = new OrderManager();
    /**
     * Name of parameter in request. Identifier of the order.
     */
    private static final String PARAM_ORDER_ID = "id";

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = "profile.jsp";
        boolean redirect = true;

        Integer orderId = Integer.valueOf(request.getParameter(PARAM_ORDER_ID));
        User user = (User) request.getSession().getAttribute("currentUser");
        try {
            for (int i = 0; i < user.getOrders().size(); i++) {
                Order order = user.getOrders().get(i);
                if (order.getId() == orderId) {
                    order.setReturned(true);
                    orderManager.update(order);
                }
            }
        } catch (PersistException e) {
            Logger.getLogger(getClass()).error(e);
            page = "index.jsp";
            redirect = false;
            request.setAttribute("message", getResourceBundle(request).getString("book.error.return"));
        }

        return new Page(page, redirect);
    }
}
