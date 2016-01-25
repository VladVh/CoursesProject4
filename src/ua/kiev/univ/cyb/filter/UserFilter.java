package ua.kiev.univ.cyb.filter;

import ua.kiev.univ.cyb.command.Page;
import ua.kiev.univ.cyb.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter, that doesn't allow users with proper rights to enter specific pages.
 */
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        Page nextPage = new Page(req.getRequestURI(), false);
        User currentUser = (User) req.getSession().getAttribute("currentUser");
        switch (req.getRequestURI()) {
            case "/order.jsp":
            case "/profile.jsp": {
                if (currentUser == null) {
                    request.setAttribute("message", "You need to login first.");
                    nextPage.setPage("index.jsp");
                }
                break;
            }
            case "/index.jsp": {
                request.setAttribute("message", "You don't have permission to visit this page");
                nextPage.setPage("index.jsp");
                break;
            }
        }

        if (nextPage.isRedirected()) {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendRedirect(nextPage.getPage());
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage.getPage());
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
