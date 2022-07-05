import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.Enumeration;

public class Msg extends HttpServlet {
    int z = 0;

    private void loadTemplate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String charset = "ISO8859-2";
        req.setCharacterEncoding(charset);
        resp.setContentType("text/html; charset=" + charset);
        PrintWriter out = resp.getWriter();
        String formFile = getInitParameter("formFormFile");
        ServletContext context = getServletContext();
        InputStream in = context.getResourceAsStream("/WEB-INF/"+formFile);
        BufferedReader br = new BufferedReader( new InputStreamReader(in));
        String line;
        while ((line = br.readLine()) != null) out.println(line);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //getting parameters values from the URI
        //I know I could have done it with getParameter() but I wanted to take advantage of what doGet offers
        PrintWriter out = response.getWriter();
        //load html template
        loadTemplate(request,response);
        Integer[] values = new Integer[2];
        int i = 0;
        try {
            Enumeration pnams = request.getParameterNames();
            while (pnams.hasMoreElements()) {
                String name = (String) pnams.nextElement();
                if (name.contains("text")) { //in the form text fields are called text1, text2
                    String value = request.getParameter(name);
                    values[i] = Integer.valueOf(value); //add the values of each text field to the array
                    i++;
                }
            }
            z = values[0] + values[1];
            out.println("The sum in GET is "+z);
        } catch (Exception e) {
            out.println("Invalid Input");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            IOException, ServletException {
        //getting parameters values with request.getParameter(name)
        PrintWriter out = response.getWriter();
        //load html template
        loadTemplate(request,response);
        try {
            int x = Integer.parseInt(request.getParameter("text1"));
            int y = Integer.parseInt(request.getParameter("text2"));
            z = x + y;
            out.println("The sum in POST is "+z);
        } catch (Exception e) {
            out.println("Invalid Input");
        }
    }
}
