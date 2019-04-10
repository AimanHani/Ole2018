<%@page import="model.PrivateLeagueProfile"%>
<%@page import="model.PublicLeagueProfile"%>
<%@page import="model.PrivateLeague"%>
<%@page import="java.util.ArrayList"%>

<html>
    <head>
        <title>Leader Board</title>
        <jsp:include page="WebDesignResources/pages/homeDesign.jsp" />
    </head>
    <body>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Private Competitions Leader Board </h1>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">View All Rankings</div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <%
                                    ArrayList<PrivateLeagueProfile> privateLeagueProfileList = null;
                                    if (request.getAttribute("privateProfileList") != null) {
                                        privateLeagueProfileList = (ArrayList<PrivateLeagueProfile>) request.getAttribute("privateProfileList");
                                    }
                                %>
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>User Name</th>
                                            <th>Competition ID</th>
                                            <th>Competition Name</th>

                                            <th>Country</th>

                                            <th>Total Points</th>
                                            <th>Rank</th>


                                            <th></th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <%
                                            for (int i = 0; i < privateLeagueProfileList.size(); i++) {
                                                PrivateLeagueProfile plf = privateLeagueProfileList.get(i);
                                        %>
                                        <tr>
                                            <td><%=i + 1%></td>
                                            <td><%=plf.getUsername()%></td>
                                            <td><%=plf.getLeagueID()%></td>
                                            <td><%=plf.getLeagueName()%></td>
                                            <td><%=plf.getCountry()%></td>

                                            <td><%=plf.getTotalPoints()%></td>
                                            <td><%=plf.getRank()%></td>


                                        </tr>
                                        <%
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
