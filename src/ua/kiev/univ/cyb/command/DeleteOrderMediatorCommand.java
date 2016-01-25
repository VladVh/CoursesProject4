package ua.kiev.univ.cyb.command;

import ua.kiev.univ.cyb.entity.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class encapsulates business logic. Provides an opportunity to delete book from the order.
 */
public class DeleteOrderMediatorCommand implements Command {

    /**
     * Name of parameter in request.
     */
    private static final String PARAM_ORDER_PART = "id";

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        String page = "order.jsp";
        boolean redirect = true;

        int orderPartPos = Integer.valueOf(request.getParameter(PARAM_ORDER_PART));
        Order order = (Order) request.getSession().getAttribute("currentOrder");
        order.getOrderParts().remove(orderPartPos);

        return new Page(page, redirect);
    }
}
