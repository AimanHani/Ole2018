<%@page import="model.Faq"%>
<%@page import="java.sql.ResultSet"%>
<html>
    <head>
        <title>OLE FAQ Details</title>
        <%
            if (session.getAttribute("admin") == null || (boolean) session.getAttribute("admin") == false) {
                String message = ("page can only be accessed by admin").toUpperCase();
                System.out.println(message);
                session.removeAttribute("admin");
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <jsp:include page="WebDesignResources/pages/homeDesign.jsp" />

    </head>
    <body>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Olé FAQ</h1>
                </div>
            </div>

            <form action="FaqServlet" method="post" style="display:inline;">
                <input type="hidden" name="param" value="add"/>
                Question: <input type="text" name="question"/><br>
                Answer <input type="text" name="answer"/><br>
                Category: <select name="category">
                    <option value="Public League">Public League</option>
                    <option value="Private League">Private League</option>
                    <option value="General">General</option>
                </select><br>
                <button class="btn btn-outline btn-primary" type="submit" value = "Submit">Add</button>
            </form>  

        </div> 
    </body>
</html>
