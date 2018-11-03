<%@page import="java.util.ArrayList"%>
<%@page import="model.Users"%>
<%@page import="java.sql.ResultSet"%>
<html>
    <head>
        <title>Olé Users</title>

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
                    <h1 class="page-header">Olé Members</h1>
                </div>
            </div>            

            <%
                ArrayList<Users> usersList = null;
                if (request.getAttribute("users") != null) {
                    usersList = (ArrayList<Users>) request.getAttribute("users");
                }
            %>

            <div class="row">
                <div class="col-lg-12">
                    <!-- Start of Search by Username -->
                    <form action="UsersServlet" method="post" style="display:inline;">
                        <!-- for servlet to know it's searching for users --> 
                        <input type="hidden" name="param" value="search"/>
                        <input class="form-control" type = "text" name = "username" placeholder="Search by Username..." style="width:50%; margin-bottom: 15px; display:inline;"/>
                        <button class="btn btn-outline btn-primary" type="submit" value = "Submit">Search</button>
                    </form>
                    <!-- End of Search by Username -->

                    <!-- Start of User Table -->
                    <div class="panel panel-default">
                        <div class="panel-heading">View all Olé Users</div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Username</th>
                                            <th>Name</th>
                                            <th>Country</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <%
                                        for (int i = 0; i < usersList.size(); i++) {
                                            Users user = usersList.get(i);
                                    %>
                                    <tbody>
                                        <tr>
                                            <td><%=i + 1%></td>
                                            <td><%=user.getUsername()%></td>
                                            <td><%=user.getName()%></td>
                                            <td><%=user.getCountry()%></td>
                                            <td><a href="./UsersServlet?param=details&index=<%=i%>">View</a></td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- End of User Table-->
                </div>
            </div>
        </div>    
    </body>
</html>
