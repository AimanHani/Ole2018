<html>
    <head>
        <title>OLE Admin</title>        

        <%
            String msg = "";

            if (request.getParameter("status") != null) {
                String loginStatus = request.getParameter("status");
                if (loginStatus.equals("success")) {
                    response.sendRedirect("home.jsp");
                } else {
                    msg = "<font color='red'>Invalid username/password</font>";
                }
            }

        %>

    </head>
    <body>
        <h1>OLE Admin</h1>
        <form action="LoginServlet" method="post">
            Username:<input type="text" name="username"/><br>		
            Password:<input type="password" name="password"/>
            <input type="submit" value="Login">	
            <%=msg%>
        </form>
    </body>
</html>
