package ua.kiev.univ.cyb.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Interface for classes implementing business logic.
 */
public interface Command {

    /**
     * Main function of business logic.
     *
     * @param request  request from servlet.
     * @param response response from servlet.
     * @return next view page and a method of showing it.
     * @throws ServletException
     * @throws IOException
     */
    public Page execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    public default ResourceBundle getResourceBundle(HttpServletRequest request) {
        ResourceBundle resourceBundle;
        String language = (String) request.getSession().getAttribute("language");
        if (language.equals("ua")) {
            resourceBundle = ResourceBundle.getBundle("i18n.text", new Locale("ua"));
        } else {
            resourceBundle = ResourceBundle.getBundle("i18n.text", new Locale("en"));
        }
        return resourceBundle;
    }
}
