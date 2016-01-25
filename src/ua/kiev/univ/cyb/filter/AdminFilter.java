package ua.kiev.univ.cyb.filter;

import org.apache.log4j.Logger;
import ua.kiev.univ.cyb.command.Page;
import ua.kiev.univ.cyb.entity.User;
import ua.kiev.univ.cyb.manager.UserManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Filter, that only allows users with administrator role to enter admin.jsp page.
 */
public class AdminFilter implements Filter {
    private UserManager userManager;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userManager = new UserManager();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        Page nextPage = new Page("admin.jsp", false);

        HttpServletRequest req = (HttpServletRequest) request;
        User user = (User) req.getSession().getAttribute("currentUser");
        if (user == null || !user.isAdmin()) {
            nextPage.setPage("home.jsp");
            nextPage.setRedirected(true);
        } else {
            try {
                List<User> users = userManager.getDefaulters();
                request.setAttribute("users", users);
            } catch (Exception e) {
                Logger.getLogger(getClass()).error(e.getMessage());
                request.setAttribute("message", "Something went wrong. Please, try again later");
                nextPage.setPage("index.jsp");
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
