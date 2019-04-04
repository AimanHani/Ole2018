<%@page import="model.PrivateLeague"%>
<%@page import="java.util.ArrayList"%>
<html>
    <head>
        <title>Private League</title>
        <jsp:include page="WebDesignResources/pages/homeDesign.jsp" />
    </head>
    <body>   
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Private League Details</h1>
                </div>
            </div>

            <%
                PrivateLeague pl = null;
                if (request.getAttribute("privateLeague") != null) {
                    pl = (PrivateLeague) request.getAttribute("privateLeague");
                }
            %>

            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">View Public League Details</div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>Tournament Name</th>
                                            <th>League Name</th>
                                            <th>Olé Points</th>
                                            <th>Grand Prize</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td><%=pl.getTournamentName()%></td>
                                            <td><%=pl.getLeagueName()%></td>
                                            <td>
                                                <form action="PrivateLeagueServlet" method="post">
                                                    <input type="hidden" name="param" value="update">
                                                    <input type="hidden" name="id" value=<%=pl.getLeagueID()%> />
                                                    <input class="form-control" type="text" name="points" value="<%=pl.getPointsAllocated()%>" style="width:30%; display:inline;">
                                                    <!--button type="submit" class="btn btn-outline btn-warning">Update</button>
                                                </form!-->
                                            </td>
                                            <td>
                                                <!--form action="PublicLeagueServlet" method="post"!-->
                                                    <!--input type="hidden" name="param" value="updatePrize"/-->
                                                    <input type="hidden" name="id" value=<%=pl.getLeagueID()%> />
                                                    <input class="form-control" type="text" name="prize" value="<%=pl.getPrize()%>" style="width:70%; display:inline;"/> 
                                                    <button type="submit" class="btn btn-outline btn-warning">Update</button>
                                                </form>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>

                            <form action="PrivateLeagueServlet" method="post" style="display:inline;">
                                <input type="hidden" name="param" value="loadAll"/>
                                <button type="submit" class="btn btn-outline btn-primary">Back to Main Table</button>
                            </form> 
                                                    
                            <form action="PrivateLeagueServlet" method="post" style="display:inline;">
                                <input type="hidden" name="param" value="delete"/>
                                <input type="hidden" name="leagueid" value=<%=pl.getLeagueID()%> />
                                <button type="submit" class="btn btn-outline btn-danger">Delete this League</button>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>