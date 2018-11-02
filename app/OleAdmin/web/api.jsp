<!DOCTYPE html>
<html>
    <head>
        <title>OLE API</title>
         <%
            String msg = "";

            if (request.getParameter("status") != null) {
                msg=request.getParameter("status");
            }else{
                //msg="false";
            }
        %>
    </head>
    <body>
        <h1>Load API</h1>
        <form action="APIServlet" method="post">
            
            <input type="submit" name="team" value="Team"><br><br>
            <input type="submit" name="tournament" value="Tournament"><br><br>
            <input type="submit" name="match" value="Match"><br><br>
            <%=msg%>
        </form>
    </body>
</html>
