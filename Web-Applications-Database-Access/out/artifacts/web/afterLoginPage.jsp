<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Verified</title>
</head>
<body>
<form action="LogoutServlet">
    <button type="submit" style="align:right">Logout</button>
</form>
</body>
</html>
<%
    out.print("<h4 style=\"text-align:left\">Session of user "+session.getAttribute("name")+"</h4>");
    out.print("<br>");
    int ilosc = (int) session.getAttribute("count");
    out.print("<form action=\"LoginServlet\" method=\"get\">");
    out.print("<label name=\"label\" id=\"label\" value=\"label\">  Number of available resources : "+ilosc+"</label>");
    for (int i=0; i<ilosc; i++)
    {
        out.print("<br>");
        out.print("<button name=\"button\" type=\"submit\" value=\""+session.getAttribute(String.valueOf(i))+"\">"+session.getAttribute(String.valueOf(i))+"</button>");
    }

    out.print("</form>");
%>