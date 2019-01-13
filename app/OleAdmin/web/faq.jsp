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
                                <button type="submit" name="catPublic" class="btn btn-outline btn-success">Public League</button>
                                <button type="submit" name="catPrivate" class="btn btn-outline btn-success">Private League</button>
                                <button type="submit" name="catGeneral" class="btn btn-outline btn-success">General</button>
                            </form>  

                        </div>
                    </div>
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

                    <a href="addFaq.jsp">Add new</a><br><br>

                    <!-- Start of Faq Table -->
                    <div class="panel panel-default">
                        <div class="panel-heading">View all Frequently Asked Questions</div>
                        <div class="panel-body">
                            <div class="table-responsive">  
                                <form action="FaqServlet" method="post">
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
                                            for (int i = 0; i < faqList.size(); i++) {
                                                Faq faq = faqList.get(i);
                                        %>
                                        <tbody>
                                            <tr>
                                                <td><%=i + 1%></td>
                                                <td><%=faq.getQuestion()%></td>
                                                <td><%=faq.getAnswer()%></td>
                                                <td><%=faq.getCategory()%></td>
                                                <td><a href="./FaqServlet?param=details&index=<%=i%>">View</a></td>
                                            </tr>
                                            <%
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- End of FAQ Table -->
                </div>
            </div>
        </div>
    </body>
</html>