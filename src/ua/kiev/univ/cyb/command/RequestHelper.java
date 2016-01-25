package ua.kiev.univ.cyb.command;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Helper class to use in servlet. Provides mapping for commands mentioned in views
 * to the business logic classes.
 */
public class RequestHelper {
    /**
     * Instance of this class
     */
    private static RequestHelper instance = null;

    /**
     * HashMap containing command mappings
     */
    private Map<String, Command> commands = new HashMap<>();

    /**
     * Default constructor, creates mappings.
     */
    private RequestHelper() {
        commands.put("logout", new NoCommand());
        commands.put("register", new RegisterCommand());
        commands.put("login", new LoginCommand());
        commands.put("addOrderMediator", new AddOrderMediatorCommand());
        commands.put("deleteOrderMediator", new DeleteOrderMediatorCommand());
        commands.put("addOrder", new AddOrderCommand());
        commands.put("addBook", new AddBookCommand());
        commands.put("returnBooks", new ReturnBooksCommand());
    }

    /**
     * @param request client request to determine command from.
     * @return according Command implementation.
     */
    public synchronized Command getCommand(HttpServletRequest request) {
        String action = request.getParameter("command");
        Command command = commands.get(action);

        if (command == null) {
            command = new NoCommand();
        }
        return command;
    }

    /**
     * The only way to get instance of RequestHelper class.
     *
     * @return instance of RequestHelper class.
     */
    public synchronized static RequestHelper getInstance() {
        if (instance == null) {
            instance = new RequestHelper();
        }
        return instance;
    }
}
