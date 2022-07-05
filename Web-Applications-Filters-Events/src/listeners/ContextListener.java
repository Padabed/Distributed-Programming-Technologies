package listeners;
import javax.servlet.*;

public class ContextListener extends Report implements ServletContextListener{

    public void contextInitialized(ServletContextEvent p0) {
        logger.severe("Context Listener: Context initalized");
        ServletContext context = p0.getServletContext();
        context.setAttribute("Liczba", 1);
    }

    public void contextDestroyed(ServletContextEvent p0) {

       logger.severe("Context Listener: Context destroyed");
    }
}