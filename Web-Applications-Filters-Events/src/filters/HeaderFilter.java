package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@WebFilter(filterName = "HeaderFilter", urlPatterns = {"*.jsp"})
public class HeaderFilter implements Filter {
    //HEADER TEMPLATE
    private String szablon =
            "  <html>\n" +
                    "  <head>\n" +
                    "    <script src=\"http://code.jquery.com/jquery-latest.min.js\"></script>\n" +
                    "    <script type=\"text/javascript\" language=\"javascript\" src=\"index.js\"></script>\n" +
                    " </head> <body><table cellpadding=2 cellspacing=2 border=1 width=100%>" +
                    "<tbody><tr><td valign=Top bgcolor=#FF69B4/>" +
                    "<div align=Center><font color=#ffffff>@</font></div></td>" +
                    "</tr></tbody></table>";
    //header content
    private String oglosz;

    private Properties encodings = new Properties();

    public void init(FilterConfig fc) throws ServletException {
        oglosz = "Task 7 by Weronika Wawrzyniak";
        oglosz = szablon.replaceFirst("@", oglosz);
    }


    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain)
            throws IOException, ServletException {
        Locale locale = req.getLocale();
        String charset = (String) encodings.get(locale);
        if (charset == null) charset = "windows-1250";
        resp.setContentType("text/html; charset=" + charset);
        PrintWriter out = ((HttpServletResponse) resp).getWriter();
        out.println(oglosz);
        chain.doFilter(req, resp);
    }

    public void destroy() {

    }
}