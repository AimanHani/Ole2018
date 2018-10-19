<%@page import="model.Users"%>
<%@page import="java.sql.ResultSet"%>
<html>
    <head>
        <title>OLE User Details</title>
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
        <h1>OLE User Details</h1>                  

        <%
            Users user = null;
            if (request.getAttribute("userObj") != null) {
                user = (Users)request.getAttribute("userObj");
            }
        %>
        <table border="0">
            <tr>
                <td>Username:</td>
                <td><%=user.getUsername()%></td>
            </tr>   

            <tr>
                <td>Name</td>
                <td><%=user.getName()%></td>
            </tr>   

            <tr>
                <td>Password</td>
                <td><%=user.getPassword()%></td>
            </tr>   

            <tr>
                <td>Date of Birth</td>
                <td><%=user.getDob()%></td>
            </tr>   

            <tr>
                <td>Country</td>
                <td><%=user.getCountry()%></td>
            </tr>   

            <tr>
                <td>Contact No</td>
                <td><%=user.getContactNo()%></td>
            </tr>   

            <tr>
                <td>Email</td>
                <td><%=user.getEmail()%></td>
            </tr>   
            
            <tr>
                <td>Favourite Team</td>
                <td><%=user.getFavoriteTeam()%></td>
            </tr>
        </table>
        <br>
        <form action="UsersServlet" method="post">
            <input type="hidden" name="param" value="loadAll"/>
            <input type = "submit" value = "Back" />
        </form>
        
        <form action="UsersServlet" method="post">
            <input type="hidden" name="param" value="delete"/>
            <input type="hidden" name="username" value=<%=user.getUsername()%> />
            <input type = "submit" value = "delete" />
        </form>

</html>
