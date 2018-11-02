<%@page import="model.PublicLeague"%>
<%@page import="java.util.ArrayList"%>
<html>
    <head>
        <title>Public League</title>
        <jsp:include page="WebDesignResources/pages/homeDesign.jsp" />
    </head>
    <body>   
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Public League Details</h1>
                </div>
            </div>

            <%
                PublicLeague pl = null;
                if (request.getAttribute("publicLeague") != null) {
                    pl = (PublicLeague) request.getAttribute("publicLeague");
                }
            %>

            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">View Public League Details</div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>Tournament Name</th>
                                            <th>League Name</th>
                                            <th>Olé Points</th>
                                            <th>Prize</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td><%=pl.getTournamentName()%></td>
                                            <td><%=pl.getLeagueName()%></td>
                                            <td>
                                                <form action="PublicLeagueServlet" method="post">
                                                    <input type="hidden" name="param" value="updatePoints"/>
                                                    <input type="hidden" name="id" value=<%=pl.getLeagueID()%> />
                                                    <input class="form-control" type="text" name="points" value="<%=pl.getPointsAllocated()%>" style="width:30%; display:inline;">
                                                    <button type="submit" class="btn btn-outline btn-warning">Update</button>
                                                </form>
                                            </td>
                                            <td>
                                                <form action="PublicLeagueServlet" method="post">
                                                    <input type="hidden" name="param" value="updatePrize"/>
                                                    <input type="hidden" name="id" value=<%=pl.getLeagueID()%> />
                                                    <input class="form-control" type="text" name="prize" value="<%=pl.getPrize()%>" style="width:70%; display:inline;"/> 
                                                    <button type="submit" class="btn btn-outline btn-warning">Update</button>
                                                </form>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>

                            <form action="PublicLeagueServlet" method="post">
                                <input type="hidden" name="param" value="loadAll"/>
                                <button type="submit" class="btn btn-outline btn-primary">Back to Main Table</button>
                            </form> 

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>