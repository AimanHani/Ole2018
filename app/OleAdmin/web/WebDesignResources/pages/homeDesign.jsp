<%-- 
    Document   : homeDesign.jsp
    Created on : Oct 26, 2018, 9:08:28 PM
    Author     : Yiting
--%>

<%@page import="controller.HomeDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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

        <!-- Morris Charts CSS -->
        <link href="WebDesignResources/vendor/morrisjs/morris.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="WebDesignResources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    </head>

    <body>
        
        <%
            if (session.getAttribute("admin") == null || (boolean) session.getAttribute("admin") == false) {
                String message = ("page can only be accessed by admin").toUpperCase();
                System.out.println(message);
                session.removeAttribute("admin");
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <div id="wrapper">
            <!-- Left Navigation Bar-->
            <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Olé Admin</a>
                </div>

                <ul class="nav navbar-top-links navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <!--<li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                            </li>
                            <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                            </li>
                            <li class="divider"></li>-->
                            <li><a href="logout.jsp"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                            </li>
                        </ul>
                    </li>
                </ul>

                <div class="navbar-default sidebar" role="navigation">
                    <div class="sidebar-nav navbar-collapse">
                        <ul class="nav" id="side-menu">
                            <li>
                                <a href="home.jsp"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-user fa-fw"></i> Olé Users<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="./UsersServlet?param=loadAll">View all users</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-sitemap fa-fw"></i> Public Competition<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="./PublicLeagueServlet?param=loadAll">View all Public Competitions</a>
                                    </li>
                                    <li>
                                        <a href="./LeaderBoardPublicServlet?param=loadAll">Public Competition Leader Board</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-shield fa-fw"></i> Private Competitions<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="./PrivateLeagueServlet?param=loadAll">View all Private Competitions</a>
                                    </li>
                                    <li>
                                        <a href="./PrivateLeagueServlet?param=leaderBoard">Private Competitions Leader Board</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-star fa-fw"></i> Specials<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="./SpecialsServlet?param=loadAll">View All Specials</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-table fa-fw"></i> Matches<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="./MatchesServlet">Premier League</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-users fa-fw"></i> Team<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="./TeamServlet">Premier League</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="./FaqServlet?param=loadAll"><i class="fa fa-a fa-fw"></i>Frequently Asked Questions</a>
                            </li>
                            <li>
                                <a href="./AskServlet?param=loadAll"><i class="fa fa-a fa-fw"></i>Ask Ole</a>
                            </li>

<!--                            <li>
                                <a href="api.jsp"><i class="fa fa-a fa-fw"></i> API</a>
                            </li>-->
                        </ul>
                    </div>
                </div>
            </nav>

            <!-- 4 Main Tiles -->
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Dashboard</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-3 col-md-6">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-user fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge"><%=HomeDAO.getTotalUsers()%></div> <!-- get total number of users-->
                                        <div>Olé Users</div>
                                    </div>
                                </div>
                            </div>
                            <a href="./UsersServlet?param=loadAll">
                                <div class="panel-footer">
                                    <span class="pull-left">View all Users</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="panel panel-green">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-sitemap fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge"><%=HomeDAO.getTotalPublicLeague()%></div> <!-- get total number of Public Leagues-->
                                        <div>Public Competitions</div>
                                    </div>
                                </div>
                            </div>
                            <a href="./PublicLeagueServlet?param=loadAll">
                                <div class="panel-footer">
                                    <span class="pull-left">View all Public Competitions</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="panel panel-yellow">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-shield fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge"><%=HomeDAO.getTotalPrivateLeague()%></div> <!-- get total number of Private Leagues-->
                                        <div>Private Competitions</div>
                                    </div>
                                </div>
                            </div>
                            <a href="./PrivateLeagueServlet?param=loadAll">
                                <div class="panel-footer">
                                    <span class="pull-left">View all Private Competitions</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="panel panel-red">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-star fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge"><%=HomeDAO.getTotalSpecials()%></div><!-- get total number of Specials-->
                                        <div>Specials</div>
                                    </div>
                                </div>
                            </div>
                            <a href="./SpecialsServlet?param=loadAll">
                                <div class="panel-footer">
                                    <span class="pull-left">View all Specials</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
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

        <!-- Morris Charts JavaScript -->
        <script src="WebDesignResources/vendor/raphael/raphael.min.js"></script>
        <script src="WebDesignResources/vendor/morrisjs/morris.min.js"></script>
        <script src="WebDesignResources/data/morris-data.js"></script>

        <!-- Custom Theme JavaScript -->
        <script src="WebDesignResources/dist/js/sb-admin-2.js"></script>

    </body>
</html>
