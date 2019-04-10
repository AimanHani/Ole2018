<%@page import="model.PublicLeague"%>
<%@page import="java.util.ArrayList"%>

<html>
    <head>
        <title>Public Competitions</title>
        <jsp:include page="WebDesignResources/pages/homeDesign.jsp" />
    </head>
    <body>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Public Competition Summary</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">View all Available Public Competitions</div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <%
                                    ArrayList<PublicLeague> publicLeagueList = null;
                                    if (request.getAttribute("publicleague") != null) {
                                        publicLeagueList = (ArrayList<PublicLeague>) request.getAttribute("publicleague");
                                    }
                                %>
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Tournament Name</th>
                                            <th>Competition Name</th>
                                            <th>Points Allocated</th>
                                            <th>Grand Prize</th>
                                            <th></th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <%
                                            for (int i = 0; i < publicLeagueList.size(); i++) {
                                                PublicLeague pl = publicLeagueList.get(i);
                                        %>
                                        <tr>
                                            <td><%=i + 1%></td>
                                            <td><%=pl.getTournamentName()%></td>
                                            <td><%=pl.getLeagueName()%></td>
                                            <td><%=pl.getPointsAllocated()%></td>
                                            <td><%=pl.getPrize()%></td>                

                                            <td><a href="./PublicLeagueServlet?param=details&index=<%=i%>">Edit</a></td>
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
