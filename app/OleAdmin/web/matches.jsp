<%@page import="java.util.ArrayList"%>
<%@page import="model.Matches"%>
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
                    <h1 class="page-header">Premier League Matches</h1>
                </div>
            </div>            

            <%
                ArrayList<Matches> matchesList = null;
                if (request.getAttribute("matches") != null) {
                    matchesList = (ArrayList<Matches>) request.getAttribute("matches");
                }
            %>

            <div class="row">
                <div class="col-lg-12">


                    <!-- Start of User Table -->
                    <div class="panel panel-default">
                        <div class="panel-heading">View all Premier League Matches</div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Date</th>
                                            <th>Time</th>
                                            <th>Team 1</th>
                                            <th>Score</th>
                                            <th>Team 2</th>
                                            <th>Score</th>
                                        </tr>
                                    </thead>
                                    <%
                                        for (int i = 0; i < matchesList.size(); i++) {
                                            Matches match = matchesList.get(i);
                                    %>
                                    <tbody>
                                        <tr>
                                            <td><%=i + 1%></td>
                                            <td><%=match.getDate()%></td>
                                            <td><%=match.getTime()%></td>
                                            <td><%=match.getTeam1()%></td>
                                            <td><%=match.getTeam1_score()%></td>
                                            <td><%=match.getTeam2()%></td>
                                            <td><%=match.getTeam2_score()%></td>
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
