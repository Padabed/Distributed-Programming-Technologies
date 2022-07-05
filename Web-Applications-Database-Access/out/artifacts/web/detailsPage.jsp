<%--
  Created by IntelliJ IDEA.
  User: Weronika Wawrzyniak
  Date: 12.04.2020
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Details</title>
</head>
<body>
<form action="afterLoginPage.jsp">
<button type="submit">Go back</button>
</form>
<form action="LogoutServlet">
    <button type="submit">Logout</button>
</form>
</body>
</html>
<%
    out.print("<h4 style=\"text-align:left\">Session of user "+session.getAttribute("name")+"</h4>");
    out.print("<br>");
    out.print(session.getAttribute("all"));
%>
