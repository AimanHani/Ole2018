<%@page import="model.Faq"%>
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
        <title>Frequently Asked Questions</title>
    </head>

    <body>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">FAQ Summary</h1>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">View</div>
                        <div class="panel-body">
                            <form action="FaqServlet" method="post" style="display:inline;">
                                <button type="submit" name="loadAll" class="btn btn-outline btn-primary">All</button>
                                <button type="submit" name="loadUnread" class="btn btn-outline btn-success">Unread</button>
                            </form>  

                        </div>
                    </div>

                    <!-- Start of Search -->
                    <form action="FaqServlet" method="post" style="display:inline;">
                        <!-- for servlet to know it's searching for users --> 
                        <input type="hidden" name="param" value="search"/>
                        <input class="form-control" type = "text" name = "keyword" placeholder="Search by keywords..." style="width:50%; margin-bottom: 15px; display:inline;"/>
                        <button class="btn btn-outline btn-primary" type="submit" value = "Submit">Search</button>
                    </form>
                    <!-- End of Search -->



                </div>
            </div>
            <%
                ArrayList<Faq> faqList = null;
                if (request.getAttribute("faq") != null) {
                    faqList = (ArrayList<Faq>) request.getAttribute("faq");
                }
            %>

            <div class="row">
                <div class="col-lg-12">
                    <!-- Start of Add Special  -->
                    <form action="FaqServlet" method="post" style="display:inline;">
                        <input type="hidden" name="param" value="addQuestion"/>
                        <input class="form-control" type="text" name="question" placeholder="Add a New Question..." style="width:50%; margin-bottom: 15px; display:inline;"/>
                        <button type="submit" class="btn btn-outline btn-primary">Add Question</button>
                    </form>
                    <!-- End of Add Question -->

                    <!-- Start of Special Table -->
                    <div class="panel panel-default">
                        <div class="panel-heading">View all Frequently Asked Questions</div>
                        <div class="panel-body">
                            <div class="table-responsive">  
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Question</th>
                                            <th>Answer</th>
                                            <th></th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <%
                                        for (int i = 0; i < faqList.size(); i++) {
                                            Faq faq = faqList.get(i);
                                    %>
                                    <tbody>
                                        <tr>
                                            <td><%=i + 1%></td>
                                            <td><%=faq.getQuestion()%></td>
                                            <td>
                                                <form action="FaqServlet" method="post" style="display:inline;">
                                                    <textarea name="answer" rows="4" cols="50" ><%=faq.getAnswer()%></textarea>
                                                    <input type="hidden" name="param" value="addAnswer" />
                                                    <input type="hidden" name="faqId" value=<%=faq.getFaqId()%> />
                                                    <button type="submit" class="btn btn-outline btn-primary">Answer</button>
                                                </form>
                                            </td>

                                            <td><a href="./FaqServlet?param=delete&faqId=<%=faq.getFaqId()%>">Delete</a></td>
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
