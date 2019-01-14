<%@page import="model.Specials"%>
<%@page import="java.sql.ResultSet"%>
<html>
    <head>
        <title>OLE User Details</title>

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
    <body>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Olé Special</h1>
                </div>
            </div>

            <%
                Specials special = null;
                if (request.getAttribute("specialObj") != null) {
                    special = (Specials) request.getAttribute("specialObj");
                }
            %>

            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">View Special Details</div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>Description</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>
                                                <form action="SpecialsServlet" method="post" style="display:inline;">
                                                    <textarea name="description" rows="4" cols="50" ><%=special.getDescription()%></textarea>
                                                    <input type="hidden" name="param" value="updateOneSpecial" />
                                                    <input type="hidden" name="id1" value=<%=special.getSpecialsId()%> />
                                                    <button type="submit" class="btn btn-outline btn-primary">Update</button>
                                                </form>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>

                            <form action="SpecialsServlet" method="post" style="display:inline;">
                                <input type="hidden" name="param" value="loadAll"/>
                                <button type="submit" class="btn btn-outline btn-primary">Back to Main Table</button>
                            </form>

                            <form action="SpecialsServlet" method="post" style="display:inline;">
                                <input type="hidden" name="param" value="delete"/>
                                <input type="hidden" name="id1" value=<%=special.getSpecialsId()%> />
                                <button type="submit" class="btn btn-outline btn-danger">Delete this Special</button>
                            </form>    

                        </div>
                    </div>
                </div>
            </div>
        </div> 
    </body>
</html>
