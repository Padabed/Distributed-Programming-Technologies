package listeners;
import java.util.*;
import java.util.logging.Logger;

public abstract class Report {
    protected static Logger logger;
    private static List rep = new ArrayList();

    static {
        logger=Logger.getLogger(RequestListener.class.getName());
    }
    public static void add(String s) {
        rep.add(s);
    }

    public static List get() { return rep; }
}