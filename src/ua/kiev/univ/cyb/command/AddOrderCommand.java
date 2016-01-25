package ua.kiev.univ.cyb.command;


import org.apache.log4j.Logger;
import ua.kiev.univ.cyb.dao.PersistException;
import ua.kiev.univ.cyb.entity.Order;
import ua.kiev.univ.cyb.entity.User;
import ua.kiev.univ.cyb.manager.OrderManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class encapsulates business logic. Gives functionality to make a new order by user.
 */
public class AddOrderCommand implements Command {

    /**
     * Manager to work with orders in persistent context.
     */
    private OrderManager orderManager = new OrderManager();

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        String page = "profile.jsp";
        boolean redirect = true;

        try {
            Order order = (Order) request.getSession().getAttribute("currentOrder");
            User currentUser = (User) request.getSession().getAttribute("currentUser");
            order = orderManager.create(order);

            String atLibrary = request.getParameter("options");
            if (atLibrary.equals("1")) {
                order.setAtLibrary(true);
            } else {
                order.setAtLibrary(false);
            }
            order.setUser(currentUser);
            currentUser.getOrders().add(order);

            request.getSession().setAttribute("currentOrder", null);
        } catch (PersistException e) {
            Logger.getLogger(getClass()).error(e);
            page = "index.jsp";
            redirect = false;
            request.setAttribute("message", getResourceBundle(request).getString("order.error"));
        }
        return new Page(page, redirect);
    }
}
