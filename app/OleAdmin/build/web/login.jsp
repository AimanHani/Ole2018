<html>
    <head>
        <title>OLDE Admin</title>        
        <%
            if (request.getAttribute("errMsg") != null) {
                System.out.println(request.getAttribute("errMsg"));
            }
        %>
    </head>
    <body>
        <h1>OLE Admin</h1>
        <form action="LoginServlet" method="post">
            Username:<input type="text" name="username"/><br>		
            Password:<input type="password" name="password"/>
            <input type="submit" value="Login">			
        </form>

        <%
            if (request.getAttribute("errMsg") != null) {
                out.println("Error with Login!");
            }
        %>
    </body>
</html>
