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
            
        int users = (Integer)request.getAttribute("users");
        int publicleague = (Integer)request.getAttribute("publicleague");
        int privateleague = (Integer)request.getAttribute("privateleague");
        int specials = (Integer)request.getAttribute("specials");
        %>
    </head>
    <body>
        <%=users%>
        <%=publicleague%>
        <%=privateleague%>
        <%=specials%>
    </body>
</html>
