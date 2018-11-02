<%@page import="model.Specials"%>
<%@page import="java.util.ArrayList"%>
<html>
    <head>
        <title>Specials</title>
    </head>
    <body>
        <h1>Specials Summary</h1>
        <%
            ArrayList<Specials> specialsList = null;
            String id = null;
            if (request.getAttribute("specials") != null) {
                specialsList = (ArrayList<Specials>) request.getAttribute("specials");
            }
            if(!request.getParameter("id").equals("") && request.getParameter("id")!=null){
                id = request.getParameter("id");
            }
        %>


        <form action="SpecialsServlet" method="post">
            <table border="1">
                <tr>
                    <th>Include specials</th>
                    <th>Description</th>
                    <th>Status</th>
                </tr>
                <%
                    for (int i = 0; i < specialsList.size(); i++) {
                        Specials special = specialsList.get(i);
                %>
                <tr>
                    <td><input type="checkbox" name="specials" value=<%=special.getSpecialsId()%> /></td>
                    <td><%=special.getDescription()%></td>
                    <td><%=special.getStatus()%></td>
                </tr>
                <%
                    }
                %>
            </table>
            <br>
            <input type="hidden" name="param" value="updateLeague"/>
            <input type="hidden" name="id" value=<%=id%> />
            <input type = "submit" value = "Choose Specials" />
            <%=id%>
        </form> 
    </body>
</html>
