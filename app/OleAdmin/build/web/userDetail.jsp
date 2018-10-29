<%@page import="model.Users"%>
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
                    <h1 class="page-header">Ol� User Details</h1>
                </div>
            </div>

            <%
                Users user = null;
                if (request.getAttribute("userObj") != null) {
                    user = (Users) request.getAttribute("userObj");
                }
            %>

            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">View all Ol� Users</div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>Username</th>
                                            <th>Name</th>
                                            <th>Country</th>
                                            <th>Contact No</th>
                                            <th>Email</th>
                                            <th>Favorite Team</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td><%=user.getUsername()%></td>
                                            <td><%=user.getName()%></td>
                                            <td><%=user.getPassword()%></td>
                                            <td><%=user.getDob()%></td>
                                            <td><%=user.getCountry()%></td>
                                            <td><%=user.getContactNo()%></td>
                                            <td><%=user.getEmail()%></td>
                                            <td><%=user.getFavoriteTeam()%></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="row">
                                <form action="UsersServlet" method="post">
                                    <input type="hidden" name="param" value="loadAll"/>
                                    <button type="submit" class="btn btn-primary">Back</button>
                                </form>
                                <form action="UsersServlet" method="post">
                                    <input type="hidden" name="param" value="delete"/>
                                    <input type="hidden" name="username" value=<%=user.getUsername()%> />
                                    <button type="submit" class="btn btn-danger">Delete</button>
                                </form>              
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> 
    </body>
</html>
