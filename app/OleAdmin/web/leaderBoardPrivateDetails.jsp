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

        <%
            ArrayList<PrivateLeagueProfile> privateLeagueProfileList = null;
            if (request.getAttribute("privateProfileList") != null) {
                privateLeagueProfileList = (ArrayList<PrivateLeagueProfile>) request.getAttribute("privateProfileList");
            }
        %>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"><%="\""+privateLeagueProfileList.get(0).getLeagueName()+"\""%> Leader Board </h1>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">View All Rankings</div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">

                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>Rank</th>
                                            <th>Username</th>
                                            <th>Country</th>
                                            <th>Total Points</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <%
                                            for (int i = 0; i < privateLeagueProfileList.size(); i++) {
                                                PrivateLeagueProfile plf = privateLeagueProfileList.get(i);
                                        %>
                                        <tr>
                                            <td><%=plf.getRank()%></td>
                                            <td><%=plf.getUsername()%></td>
                                            <td><%=plf.getCountry()%></td>
                                            <td><%=plf.getTotalPoints()%></td>
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
