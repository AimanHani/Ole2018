<html>
    <head>
        <title>Admin Home</title>
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
        <h1>Welcome, Admin!</h1>
        <h3>Manage:</h3>
        <a href="./UsersServlet?param=loadAll">OLE Users</a><br>
        <a href="publicLeague.jsp">Public League</a><br>
        <a href="privateLeague.jsp">Private League</a><br>
        <a href="./SpecialsServlet?param=loadAll">Specials</a><br>

    </body>
</html>
