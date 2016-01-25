package ua.kiev.univ.cyb.controller;

import org.apache.log4j.Logger;
import ua.kiev.univ.cyb.command.Command;
import ua.kiev.univ.cyb.command.Page;
import ua.kiev.univ.cyb.command.RequestHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main servlet that controls and manages all actions.
 */
public class LibraryController extends HttpServlet {
    private RequestHelper requestHelper = RequestHelper.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    /**
     * Helper method, determines next actions from request data.
     *
     * @param request  request from a view.
     * @param response response from servlet.
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        Page nextPage = new Page("home.jsp", false);

        try {
            Command command = requestHelper.getCommand(request);
            nextPage = command.execute(request, response);
        } catch (ServletException e) {
            Logger.getLogger(this.getClass()).error(e.getMessage());
        } catch (IOException e) {
            Logger.getLogger(this.getClass()).error(e.getMessage());
        }


        if (nextPage.isRedirected()) {
            response.sendRedirect(nextPage.getPage());
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage.getPage());
            dispatcher.forward(request, response);
        }
    }
}
