<%@page import="model.Specials"%>
<%@page import="java.util.ArrayList"%>
<html>
    <head>
        <title>Specials</title>
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
        <h1>Specials Summary</h1>
        <%
            ArrayList<Specials> specialsList = null;
            if (request.getAttribute("specials") != null) {
                specialsList = (ArrayList<Specials>) request.getAttribute("specials");
            }
        %>


        <form action="SpecialsServlet" method="post">
            <table border="1">
                <tr>
                    <th>Include specials</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th></th>
                </tr>
                <%
                    for (int i = 0; i < specialsList.size(); i++) {
                        Specials special = specialsList.get(i);
                %>
                <tr>
                    <td><input type="checkbox" name="specials" value=<%=special.getSpecialsId()%> /></td>
                    <td><%=special.getDescription()%></td>
                    <td><%=special.getStatus()%></td>
                    <td><a href="./SpecialsServlet?param=delete&id=<%=special.getSpecialsId()%>">Delete</a></td>
                </tr>
                <%
                    }
                %>
            </table>
            <br>
            <input type="hidden" name="param" value="update"/>
            <input type = "submit" value = "Update Specials" />
        </form> 

        <hr>
        <h1>Add Specials</h1>    
        <form action="SpecialsServlet" method="post">
            <input type="hidden" name="param" value="add"/>
            Description: <input type="text" name="description"/>
            <input type = "submit" value = "Add Specials" />
        </form>
    </body>
</html>
