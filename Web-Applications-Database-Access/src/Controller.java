//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet({"/Controller"})
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    DataSource dataSource;  // źrodło danych

    public void init() throws ServletException {
        try {
            Context init = new InitialContext();
            Context contx = (Context) init.lookup("java:comp/env");
            dataSource = (DataSource) contx.lookup("jdbc/ksidb");
        } catch (NamingException exc) {
            throw new ServletException(
                    "Nie mogę uzyskać źródła java:comp/env/jdbc/ksidb", exc);
        }
    }

    public void serviceRequest(HttpServletRequest req,
                               HttpServletResponse resp)
            throws ServletException, IOException
    {
        resp.setContentType("text/html; charset=windows-1250");
        PrintWriter out = resp.getWriter();
        out.println("<h2>Lista dostępnych książek</h2>");

        Connection con = null;
        try {
            synchronized (dataSource) {
                con = dataSource.getConnection();
            }
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from pozycje");
            out.println("<ol>");
            while (rs.next())  {
                String tytul = rs.getString("tytul");
                float cena  = rs.getFloat("cena");
                out.println("<li>" + tytul + " - cena: " + cena + "</li>");
            }
            rs.close();
            stmt.close();
        } catch (Exception exc)  {
            out.println(exc.getMessage());
        } finally {
            try { con.close(); } catch (Exception exc) {}
        }

        out.close();
    }


        }

    }
}
