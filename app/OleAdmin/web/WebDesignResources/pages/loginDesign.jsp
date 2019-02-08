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

        <title>Olé Admin Login</title>

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
                    msg = "<font color='red'>Error: Invalid Username/Password</font>";
                }
            }

        %>

    </head>

    <body style="text-align:center; background-color: #CCDAFF;">
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="login-panel panel panel-default" style="padding: 10px; box-shadow: 10px 10px 20px grey">
                        <div class="panel-body">
                            <form action="LoginServlet" method="post" role="form">
                                <fieldset>
                                    <img src="WebDesignResources/images/logo.png" alt="Olé logo" width="120" height="120">
                                    <h3 style="margin-top: 10px;">Olé Administration</h3>
                                    <hr>
                                    <div class="form-group input-group">
                                        <span class="input-group-addon">
                                            <i class="fa fa-user"></i>
                                        </span>
                                        <input type="text" class="form-control" name="username" placeholder="Username" style="height:40px;" autofocus>
                                    </div>
                                    <div class="form-group input-group">
                                        <span class="input-group-addon">
                                            <i class="fa fa-lock"></i>
                                        </span>
                                        <input type="password" class="form-control" name="password" placeholder="Password" style="height:40px;">
                                    </div>

                                    <button type="submit" class="btn btn-lg btn-primary btn-block" style="margin-bottom: 15px;">Login</button>
                                </fieldset>
                                <%=msg%>
                            </form>
                        </div>
                    </div>
                    <p>&copy; Olé2018 Football. Friends. Fun. All Rights Reserved</p>
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

        <!--<div class="checkbox">
            <label>
                <input name="remember" type="checkbox" value="Remember Me">Remember Me
            </label>
        </div>-->

    </body>
</html>
