import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class Inputservlet extends HttpServlet
{
    
    public void doPost(HttpServletRequest request,HttpServletResponse response)throws
            IOException , ServletException
    {
        Integer left = Integer.valueOf(request.getParameter("left"));
        Integer right = Integer.valueOf(request.getParameter("right"));
        Integer sum = left + right;
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(String.valueOf(sum));
    }
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws
            IOException , ServletException
    {
        Integer left = Integer.valueOf(request.getParameter("left"));
        Integer right = Integer.valueOf(request.getParameter("right"));
        Integer sum = left + right;
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(String.valueOf(sum));
    }
}
