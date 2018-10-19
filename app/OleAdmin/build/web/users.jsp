<%@page import="java.util.ArrayList"%>
<%@page import="model.Users"%>
<%@page import="java.sql.ResultSet"%>
<html>
    <head>
        <title>OLE Users</title>
        <jsp:include page="header.jsp" />
        <%
            if (session.getAttribute("admin") == null || (boolean) session.getAttribute("admin") == false) {
                String message = ("page can only be accessed by admin").toUpperCase();
                System.out.println(message);
                session.removeAttribute("admin");
                response.sendRedirect("login.jsp");
                return;
            }
        %>

    </head>
    <body>
        <h1>OLE Members</h1>                  

        <%
            ArrayList<Users> usersList = null;
            if (request.getAttribute("users") != null) {
                usersList = (ArrayList<Users>) request.getAttribute("users");
            }
        %>
        
        <form action="UsersServlet" method="post">
            <input type="hidden" name="param" value="search"/>
            Search by username: <input type = "text" name = "username" />
            <input type = "submit" value = "Submit" />
        </form>
        
        <table border="1">
            <tr>
                <th>Username</th>
                <th>Name</th>
                <th>Country</th>
                <th></th>
            </tr>
            <%
                for (int i = 0; i < usersList.size(); i++) { 
                    Users user = usersList.get(i);
            %>
            <tr>
                <td><%=user.getUsername()%></td>
                <td><%=user.getName()%></td>
                <td><%=user.getCountry()%></td>
                <td><a href="./UsersServlet?param=details&index=<%=i%>">View</a></td>
            </tr>
            <%
                }
            %>

        </table>



</html>
