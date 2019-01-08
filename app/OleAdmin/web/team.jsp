<%@page import="java.util.ArrayList"%>
<%@page import="model.Team"%>
<%@page import="java.sql.ResultSet"%>
<html>
    <head>
        <title>Olé Teams</title>

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
                    <h1 class="page-header">Premier League Teams</h1>
                </div>
            </div>            

            <%
                ArrayList<Team> teamList = null;
                if (request.getAttribute("teams") != null) {
                    teamList = (ArrayList<Team>) request.getAttribute("teams");
                }
            %>
            <div class="row">
                <div class="col-lg-12">


                    <!-- Start of Team Table -->
                    <div class="panel panel-default">
                        <div class="panel-heading">Premier League Teams</div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Team Name</th>
                                        </tr>
                                    </thead>
                                    <%
                                        for (int i = 0; i < teamList.size(); i++) {
                                            Team team = teamList.get(i);
                                    %>
                                    <tbody>
                                        <tr>
                                            <td><%=i + 1%></td>
                                            <td><%=team.getTeamName()%></td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- End of Team Table-->
                </div>
            </div>
        </div>    
    </body>
</html>
