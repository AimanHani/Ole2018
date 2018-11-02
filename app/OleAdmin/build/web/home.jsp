<html>
    <head>
        <title>Olé Admin Home</title>
        
        <jsp:include page="WebDesignResources/pages/homeDesign.jsp" />
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
        <a href="./UsersServlet?param=loadAll">OLE Users</a><br>
        <a href="./PublicLeagueServlet?param=loadAll">Public League</a><br>
        <a href="privateLeague.jsp">Private League</a><br>
        <a href="./SpecialsServlet?param=loadAll">Specials</a><br>
    </body>
</html>
