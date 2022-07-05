package listeners;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class RequestListener extends Report implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent event) {
        event.getServletRequest().getServletContext();
        logger.severe("Request listener : request "+event.toString()+" initialized");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent event) {
      logger.severe("Request listener : request "+event.toString()+" destroyed");
    }

}