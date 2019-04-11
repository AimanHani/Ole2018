<%@page import="model.Specials"%>
<%@page import="java.util.ArrayList"%>
<html>
    <head>
        <title>Specials</title>

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
                    <h1 class="page-header">Specials Summary</h1>
                </div>
            </div>

            <%
                ArrayList<Specials> specialsList = null;
                if (request.getAttribute("specials") != null) {
                    specialsList = (ArrayList<Specials>) request.getAttribute("specials");
                }
            %>

            <div class="row">
                <div class="col-lg-12">
                    <!-- Start of Add Special  -->
                    <form action="SpecialsServlet" method="post" style="display:inline;">
                        <input type="hidden" name="param" value="add"/>
                        <input class="form-control" type="text" name="description" placeholder="Add a New Special Description..." style="width:50%; margin-bottom: 15px; display:inline;"/>
                        <button type="submit" class="btn btn-outline btn-primary">Add Special</button>
                    </form>
                    <!-- End of Add Special -->

                    <!-- Start of Special Table -->
                    <div class="panel panel-default">
                        <div class="panel-heading">View all Specials</div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <form action="SpecialsServlet" method="post">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th>Include specials</th>
                                                <th>#</th>
                                                <th>Description</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <%
                                            for (int i = 0; i < specialsList.size(); i++) {
                                                Specials special = specialsList.get(i);
                                                String status = special.getStatus();
                                        %>
                                        <tbody>
                                            <tr>
                                                <% if (status.equals("Y")) {%>
                                                <td><input type="checkbox" class="checkbox" name="specials" value=<%=special.getSpecialsId()%> checked/></td>
                                                    <% } else {%>
                                                <td><input type="checkbox" class="checkbox" name="specials" value=<%=special.getSpecialsId()%> /></td>
                                                    <% }%>
                                                <td><%=i + 1%></td>
                                                <td><%=special.getDescription()%></td>
                                                <%-- <td><a href="./SpecialsServlet?param=delete&id1=<%=special.getSpecialsId()%>">Delete</a></td> --%>
                                                <td><a href="./SpecialsServlet?param=details&index=<%=i%>">View</a></td>
                                            </tr>
                                            <%
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                    <input type="hidden" name="param" value="update"/>
                                    <button type="submit" class="btn btn-outline btn-warning">Update Specials</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- End of Special Table -->
                </div>
            </div>
        </div>
    </body>
</html>