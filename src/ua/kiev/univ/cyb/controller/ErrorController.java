package ua.kiev.univ.cyb.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet that manages and works with errors, that appear on pages.
 */
public class ErrorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Helper method, forwards user to error page.
     *
     * @param request  request from a view
     * @param response response from servlet
     */
    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("message", "Something went wrong.");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}
