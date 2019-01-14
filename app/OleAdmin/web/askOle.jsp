<%@page import="model.Ask"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
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
        <title>Ask Ole Questions</title>
    </head>

    <body>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Ask Ole</h1>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">View</div>
                        <div class="panel-body">
                            <form action="AskServlet" method="post" style="display:inline;">
                                <button type="submit" name="loadAll" class="btn btn-outline btn-primary">All</button>
                                <button type="submit" name="loadUnread" class="btn btn-outline btn-success">Unread</button>
                                <button type="submit" name="catPublic" class="btn btn-outline btn-success">Public League</button>
                                <button type="submit" name="catPrivate" class="btn btn-outline btn-success">Private League</button>
                                <button type="submit" name="catGeneral" class="btn btn-outline btn-success">General</button>
                            </form>  

                        </div>
                    </div>

                    <!-- Start of Search -->
                    <!-- <form action="FaqServlet" method="post" style="display:inline;"> -->
                        <!-- <input type="hidden" name="param" value="search"/> -->
                        <!-- <input class="form-control" type = "text" name = "keyword" placeholder="Search by keywords..." style="width:50%; margin-bottom: 15px; display:inline;"/>  
                        <button class="btn btn-outline btn-primary" type="submit" value = "Submit">Search</button>
                    </form>
                    <!-- End of Search -->



                </div>
            </div>
            <%
                ArrayList<Ask> askList = null;
                if (request.getAttribute("ask") != null) {
                    askList = (ArrayList<Ask>) request.getAttribute("ask");
                }
            %>

            <div class="row">
                <div class="col-lg-12">

                    <!-- Start of Ask Table -->
                    <div class="panel panel-default">
                        <div class="panel-heading">View all Ask Ole Questions</div>
                        <div class="panel-body">
                            <div class="table-responsive">  
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Question</th>
                                            <th>Answer</th>
                                            <th>Category</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <%
                                        for (int i = 0; i < askList.size(); i++) {
                                            Ask ask = askList.get(i);
                                    %>
                                    <tbody>
                                        <tr>
                                            <td><%=i + 1%></td>
                                            <td><%=ask.getQuestion()%></td>
                                            <td>
                                                <form action="AskServlet" method="post" style="display:inline;">
                                                    <textarea name="answer" rows="4" cols="50" ><%=ask.getAnswer()%></textarea>
                                                    <input type="hidden" name="param" value="addAnswer" />
                                                    <input type="hidden" name="askId" value=<%=ask.getAskId()%> />
                                                    <button type="submit" class="btn btn-outline btn-primary">Answer</button>
                                                </form>
                                            </td>
                                            <td><%=ask.getCategory()%></td>
                                            <td><a href="./AskServlet?param=delete&askId=<%=ask.getAskId()%>">Delete</a></td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- End of FAQ Table -->
                </div>
            </div>
        </div>
    </body>
</html>
