package ua.kiev.univ.cyb.command;

import org.apache.log4j.Logger;
import ua.kiev.univ.cyb.dao.PersistException;
import ua.kiev.univ.cyb.entity.User;
import ua.kiev.univ.cyb.manager.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class encapsulates business logic. Provides an opportunity to log user into system.
 */
public class LoginCommand implements Command {
    /**
     * Names of parameters in request.
     */
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PASSWORD = "password";

    /**
     * Manager to work with users in persistent context.
     */
    private UserManager userManager = new UserManager();

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        String page = "index.jsp";
        boolean redirect = false;

        String email = request.getParameter(PARAM_EMAIL);
        String password = request.getParameter(PARAM_PASSWORD);

        String error = getResourceBundle(request).getString("input.error");
        try {
            User user = userManager.login(email, password);
            if (user != null) {
                request.getSession().setAttribute("currentUser", user);
                if (user.isAdmin()) {
                    page = "admin.jsp";
                    redirect = true;
                } else {
                    page = "books.jsp";
                    redirect = true;
                }
                Logger.getLogger(this.getClass()).info("User: " + user.getFirstName() + " " +
                        user.getSecondName() + "(id: " + user.getId() + ") has successfully logged in.");
            } else {
                error += getResourceBundle(request).getString("input.error.login");
                request.setAttribute("message", error);
            }
        } catch (PersistException e) {
            Logger.getLogger(this.getClass()).error(e.getMessage());
            error += getResourceBundle(request).getString("input.error.internal");
            request.setAttribute("message", error);
        }
        return new Page(page, redirect);
    }
}
