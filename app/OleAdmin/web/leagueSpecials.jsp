<%@page import="model.Specials"%>
<%@page import="java.util.ArrayList"%>
<html>
    <head>
        <title>Specials</title>
        <jsp:include page="WebDesignResources/pages/homeDesign.jsp" />
    </head>
    <body>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Load API</h1>
                </div>
            </div>

            <%
                ArrayList<Specials> specialsList = null;
                String id = null;
                if (request.getAttribute("specials") != null) {
                    specialsList = (ArrayList<Specials>) request.getAttribute("specials");
                }
                if (!request.getParameter("id").equals("") && request.getParameter("id") != null) {
                    id = request.getParameter("id");
                }
            %>

            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">Special Summary</div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <form action="SpecialsServlet" method="post">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th>Include specials</th>
                                                <th>#</th>
                                                <th>Description</th>
                                                <th>Status</th>
                                            </tr>
                                        </thead>
                                        <%
                                            for (int i = 0; i < specialsList.size(); i++) {
                                                Specials special = specialsList.get(i);
                                        %>
                                        <tbody>
                                            <tr>
                                                <td><input type="checkbox" class="checkbox" name="specials" value=<%=special.getSpecialsId()%> /></td>
                                                <td><%=i + 1%></td>
                                                <td><%=special.getDescription()%></td>
                                                <td><%=special.getStatus()%></td>
                                            </tr>
                                            <%
                                                }
                                            %>
                                        </tbody>
                                    </table>

                                    <input type="hidden" name="param" value="updateLeague"/>
                                    <input type="hidden" name="id" value=<%=id%> />
                                    <button type="submit" class="btn btn-outline btn-primary">Choose Specials</button>
                                    <%=id%>
                                </form>  
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>                  
    </body>
</html>
