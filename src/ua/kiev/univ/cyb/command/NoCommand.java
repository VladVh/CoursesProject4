package ua.kiev.univ.cyb.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class encapsulates business logic. Works as a logout for user
 */
public class NoCommand implements Command {
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        String page = "login.jsp";
        boolean redirect = true;

        return new Page(page, redirect);
    }
}
