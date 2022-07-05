import Repositories.ResourceRepository;
import Repositories.UserRepository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private DataSource dataSource;
    private UserRepository _repository;
    private ResourceRepository _resRepository;
    private Connection con;

    public void init() throws ServletException {
        try {
            Context init = new InitialContext();
            Context contx = (Context) init.lookup("java:comp/env");
            dataSource = (DataSource) contx.lookup("jdbc/test");
            _repository = new UserRepository(dataSource);
            _resRepository = new ResourceRepository(dataSource);
            try {
                con = dataSource.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NamingException exc) {
            throw new ServletException(
                    "Nie mogę uzyskać źródła java:comp/env", exc);
        }
    }

    //verifies user and shows available resources
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        String name = request.getParameter("login");
        String password = request.getParameter("password");
        try {
            if (_repository.validateUser(name, password)) {
                session.setAttribute("name", name);
                List<String> resNames = _resRepository.getAllRecourseNames(name, password);
                session.setAttribute("count", resNames.size());
                for (int i = 0; i < resNames.size(); i++) {
                    session.setAttribute(String.valueOf(i), String.valueOf(resNames.get(i)));
                }
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("afterLoginPage.jsp");
                requestDispatcher.forward(request, response);
            } else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("LogoutServlet");
                requestDispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Shows content and resource name
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        String allResourceDetails = "";
        String resourceName = req.getParameter("button");
        try {
            allResourceDetails = _resRepository.getAllRecourceDetails(resourceName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        session.setAttribute("all", allResourceDetails);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("detailsPage.jsp");
        requestDispatcher.forward(req, resp);
    }
}  