<%@page import="model.PublicLeague"%>
<%@page import="java.util.ArrayList"%>
<html>
    <head>
        <title>Public League</title>
    </head>
    <body>
        <h1>Public League</h1>
        <%
            ArrayList<PublicLeague> publicLeagueList = null;
            if (request.getAttribute("publicleague") != null) {
                publicLeagueList = (ArrayList<PublicLeague>) request.getAttribute("publicleague");
            }
        %>


        <table border="1">
            <tr>
                <th>Tournament Name</th>
                <th>League Name</th>
                <th>Points Allocated</th>
                <th>Prize</th>
                <th></th>
            </tr>
            <%
                for (int i = 0; i < publicLeagueList.size(); i++) { 
                    PublicLeague pl = publicLeagueList.get(i);
            %>
            <tr>
                <td><%=pl.getTournamentName()%></td>
                <td><%=pl.getLeagueName()%></td>
                <td><%=pl.getPointsAllocated()%></td>
                <td><%=pl.getPrize()%></td>                
                
                <td><a href="./PublicLeagueServlet?param=details&index=<%=i%>">View</a></td>
            </tr>
            <%
                }
            %>

        </table>
    </body>
</html>
