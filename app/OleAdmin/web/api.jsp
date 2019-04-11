<!DOCTYPE html>
<html>
    <head>
        <title>Olé API</title>

        <jsp:include page="WebDesignResources/pages/homeDesign.jsp" />
        <%
            String msg = "";

            if (request.getParameter("status") != null) {
                msg = request.getParameter("status");
            } else {
                //msg="false";
            }
        %>
    </head>
    <body>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Load API</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">Retrieve Status</div>
                        <div class="panel-body">

                            <form action="APIServlet" method="post" style="display:inline;">
                                <button type="submit" name="team" class="btn btn-outline btn-primary">Team</button>
                                <button type="submit" name="tournament" class="btn btn-outline btn-success">Tournament</button>
                                <button type="submit" name="match" class="btn btn-outline btn-warning">Match</button>
                                <%=msg%>
                            </form>  

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
