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
                    <h1 class="page-header">Public Competitions Leader Board </h1>
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
                                    ArrayList<PublicLeagueProfile> publicLeagueProfileList = null;
                                    if (request.getAttribute("publicProfileList") != null) {
                                        publicLeagueProfileList = (ArrayList<PublicLeagueProfile>) request.getAttribute("publicProfileList");
                                    }
                                %>
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>User Name</th>
                                            <th>Country</th>
                                            <th>Competition ID</th>
                                            <th>Total Points</th>
                                            <th>Rank</th>


                                            <th></th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <%
                                            for (int i = 0; i < publicLeagueProfileList.size(); i++) {
                                                PublicLeagueProfile plf = publicLeagueProfileList.get(i);
                                        %>
                                        <tr>
                                            <td><%=i + 1%></td>
                                            <td><%=plf.getUsername()%></td>
                                            <td><%=plf.getCountry()%></td>
                                            <td><%=plf.getLeagueID()%></td>
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
