<%@page import="model.PrivateLeague"%>
<%@page import="java.util.ArrayList"%>

<html>
    <head>
        <title>Private League Leader Board</title>
        <jsp:include page="WebDesignResources/pages/homeDesign.jsp" />
    </head>
    <body>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
<<<<<<< HEAD
                    <h1 class="page-header">Click View Leader Board to View Ranking in Each Private League</h1>
=======
                    <h1 class="page-header">Private Competitions</h1>
>>>>>>> 7136171927fdb8c93531ac2e3a7cf5e0bd5b3250
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">View all Available Private Competitions</div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <%
                                    ArrayList<PrivateLeague> privateLeagueList = null;
                                    if (request.getAttribute("privateleague") != null) {
                                        privateLeagueList = (ArrayList<PrivateLeague>) request.getAttribute("privateleague");
                                    }
                                %>
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Tournament Name</th>
                                            <th>Competition Name</th>
                                            <th>Created By</th>
                                            
                                            <th>Start Date</th>
                                            <th>End Date</th>
                                            
                                          
                                            <th></th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <%
                                            for (int i = 0; i < privateLeagueList.size(); i++) {
                                                PrivateLeague pl = privateLeagueList.get(i);
                                        %>
                                        <tr>
                                            <td><%=i + 1%></td>
                                            <td><%=pl.getTournamentName()%></td>
                                            <td><%=pl.getLeagueName()%></td>
                                            <td><%=pl.getUsername()%></td>
                                            
                                            <td><%=pl.getStartDate()%></td>
                                            <td><%=pl.getEndDate()%></td>

                                                          

                                            <td><a href="./LeaderBoardPrivateServlet?index=<%=i%>">View Leader Board</a></td>
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
