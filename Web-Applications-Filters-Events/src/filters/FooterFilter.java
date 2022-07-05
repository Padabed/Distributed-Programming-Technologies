package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
@WebFilter(filterName = "FooterFilter", urlPatterns = {"*.jsp","*.servlet"})
public class FooterFilter implements Filter{

    public void init(FilterConfig p0) throws ServletException {
    }

    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException,ServletException {

        Locale locale = req.getLocale();
        StringResponseWrapper newResp = new StringResponseWrapper(
                (HttpServletResponse) resp);
        chain.doFilter(req, newResp);
        StringWriter sw = newResp.getStringWriter();
        String cont = sw.toString();
        PrintWriter out = resp.getWriter();
        out.println(cont);
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.MEDIUM,
                locale);

      //  out.println("<b><div id=\"date\"> {\""+DATE+"\":\""+ df.format(new Date()) +"\"}; </div>  </body>\n" +

        //     "</html>");

      //  out.println("{\""+DATE+"\":\""+ df.format(new Date()) +"\"};");
        out.println("<b><div id=\"date\">"+df.format(new Date()) +"</div>  </body>\n" + "</html>");
        //out.write("<b><div id=\"date\">"+df.format(new Date()) +"</div>  </body>\n" + "</html>");
        out.close();
    }
private static final String DATE ="date";
    public void destroy() {
    }
}