<%@page import="model.Faq"%>
<%@page import="java.sql.ResultSet"%>
<html>
    <head>
        <title>Olé FAQ Details</title>

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
    </head>
    <body>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Olé FAQ</h1>
                </div>
            </div>

            <%
                Faq faq = null;
                if (request.getAttribute("faqObj") != null) {
                    faq = (Faq) request.getAttribute("faqObj");
                }
            %>

            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">View FAQ Details</div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>Description</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>
                                                <form action="FaqServlet" method="post" style="display:inline;">
                                                    <textarea name="question" rows="4" cols="50" ><%=faq.getQuestion()%></textarea>
                                                    <textarea name="answer" rows="4" cols="50" ><%=faq.getAnswer()%></textarea>
                                                    <input type="hidden" name="param" value="update" />
                                                    <input type="hidden" name="faqId" value=<%=faq.getFaqId()%> />
                                                    <button type="submit" class="btn btn-outline btn-primary">Update</button>
                                                </form>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>

                            <form action="FaqServlet" method="post" style="display:inline;">
                                <input type="hidden" name="param" value="loadAll"/>
                                <button type="submit" class="btn btn-outline btn-primary">Back to Main Table</button>
                            </form>

                            <form action="FaqServlet" method="post" style="display:inline;">
                                <input type="hidden" name="param" value="delete"/>
                                <input type="hidden" name="faqId" value=<%=faq.getFaqId()%> />
                                <button type="submit" class="btn btn-outline btn-danger">Delete</button>
                            </form>    

                        </div>
                    </div>
                </div>
            </div>
        </div> 
    </body>
</html>
