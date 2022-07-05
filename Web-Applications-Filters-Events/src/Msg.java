import listeners.Report;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Msg extends HttpServlet {
    private static final String NUMBER_PATTERN = "[0-9]+";
    private static final Pattern NUMBER_REGEX = Pattern.compile(NUMBER_PATTERN);
    public void init() {
        Report.add("Servlet: Servlet initialized");
    }

    private static BigInteger parameters(HttpServletRequest req, String name) {
        String parameter = (String) req.getParameter(name);
        return parse(parameter);
    }

    private static BigInteger parse(String input) {
        if (input == null) return null;
        Matcher matcher = NUMBER_REGEX.matcher(input);
        if (matcher.matches()) {
            return new BigInteger(input);
        }
        return null;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=windows-1250");
        PrintWriter out = response.getWriter();
        System.out.print("IN THA GET");
        try {
            BigInteger x = parameters(request, "text1");
            BigInteger y = parameters(request, "text2");

            if (x == null || y == null) {
            } else {
                BigInteger z = x.add(y);
                out.write(String.valueOf(z));
            }
        } catch (Exception e) {
            out.write("Invalid");
        }
        out.write("");
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            IOException, ServletException {
        PrintWriter out = response.getWriter();
        out.write("");
        out.close();
    }
}
