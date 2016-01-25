package ua.kiev.univ.cyb.command;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import ua.kiev.univ.cyb.dao.PersistException;
import ua.kiev.univ.cyb.entity.User;
import ua.kiev.univ.cyb.manager.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class that encapsulates business logic. Provides functionality of registering user in system.
 */
public class RegisterCommand implements Command {
    /**
     * Names of parameter in request.
     */
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_FIRST_NAME = "firstName";
    private static final String PARAM_SECOND_NAME = "secondName";

    /**
     * Manager to work with users in persistent context.
     */
    private UserManager userManager = new UserManager();

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        boolean redirect = false;

        String email = request.getParameter(PARAM_EMAIL);
        String password = request.getParameter(PARAM_PASSWORD);
        String firstName = request.getParameter(PARAM_FIRST_NAME);
        String secondName = request.getParameter(PARAM_SECOND_NAME);

        password = BCrypt.hashpw(password, BCrypt.gensalt());

        String language = (String) request.getSession().getAttribute("language");

        String error = getResourceBundle(request).getString("input.error");
        try {
            User user = userManager.get(email);
            if (user == null) {                                 /* Email is unique */
                User newUser = userManager.create(firstName, secondName, email, password);
                if (newUser.getId() != null) {
                    request.setAttribute("message", getResourceBundle(request).getString("input.success"));
                    page = "index.jsp";
                    return new Page(page, redirect);
                } else {
                    error += getResourceBundle(request).getString("input.error.internal");
                }
            } else {
                error += getResourceBundle(request).getString("input.error.email");
            }
        } catch (PersistException e) {
            Logger.getLogger(this.getClass()).error(e.getMessage());
            error += getResourceBundle(request).getString("input.error.internal");
        }
        request.setAttribute("message", error);
        page = "index.jsp";
        return new Page(page, redirect);
    }


}
