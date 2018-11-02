<%@page import="model.PublicLeague"%>
<%@page import="java.util.ArrayList"%>
<html>
    <head>
        <title>Public League</title>
    </head>
    <body>        
        <h1>Public League Details</h1>                  

        <%
            PublicLeague pl = null;
            if (request.getAttribute("publicLeague") != null) {
                pl = (PublicLeague)request.getAttribute("publicLeague");
            }
        %>
        Tournament Name: <%=pl.getTournamentName()%><br><br>
        League Name: <%=pl.getLeagueName()%><br><br>
        
        <form action="PublicLeagueServlet" method="post">
        Points: 
        <input type="hidden" name="param" value="updatePoints"/>
        <input type="hidden" name="id" value=<%=pl.getLeagueID()%> />
        <input type="text" name="points" value="<%=pl.getPointsAllocated()%>">
        <input type = "submit" value = "update points" />
        </form>
        
        <form action="PublicLeagueServlet" method="post">
        Prize: 
        <input type="hidden" name="param" value="updatePrize"/>
        <input type="hidden" name="id" value=<%=pl.getLeagueID()%> />
        <input type="text" name="prize" value="<%=pl.getPrize()%>" /> 
        <input type = "submit" value = "update prize" />
        </form>
        
        <br>
        <form action="PublicLeagueServlet" method="post">
            <input type="hidden" name="param" value="loadAll"/>
            <input type = "submit" value = "Back" />
        </form>
    </body>
</html>
