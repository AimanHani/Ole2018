<%-- 
    Document   : login
    Created on : Oct 26, 2018, 8:34:39 PM
    Author     : Yiting
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Olé Admin Page</title>

        <!-- Bootstrap Core CSS -->
        <link href="WebDesignResources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- MetisMenu CSS -->
        <link href="WebDesignResources/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="WebDesignResources/dist/css/sb-admin-2.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="WebDesignResources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

        <%
            String msg = "";

            if (request.getParameter("status") != null) {
                String loginStatus = request.getParameter("status");
                if (loginStatus.equals("success")) {
                    response.sendRedirect("home.jsp");
                } else {
                    msg = "<font color='red'>Invalid username/password</font>";
                }
            }

        %>

    </head>

    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="login-panel panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Olé Admin Login</h3>
                        </div>
                        <div class="panel-body">
                            <form action="LoginServlet" method="post" role="form">
                                <fieldset>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="Username" name="username" type="text" autofocus>
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="Password" name="password" type="password" value="">
                                    </div>
                                    <div class="checkbox">
                                         <!--<label>
                                            <input name="remember" type="checkbox" value="Remember Me">Remember Me
                                        </label>-->
                                    </div>
                                    
                                    <input type="submit" class="btn btn-lg btn-success btn-block" value="Login"></input>
                                    <%=msg%>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- jQuery -->
        <script src="WebDesignResources/vendor/jquery/jquery.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="WebDesignResources/vendor/bootstrap/js/bootstrap.min.js"></script>

        <!-- Metis Menu Plugin JavaScript -->
        <script src="WebDesignResources/vendor/metisMenu/metisMenu.min.js"></script>

        <!-- Custom Theme JavaScript -->
        <script src="WebDesignResources/dist/js/sb-admin-2.js"></script>

    </body>

</html>
