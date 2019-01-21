package controller;

import dbConnection.DBConnection;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.text.ParseException;

public class APIDAO {

    //static String token  = "862c840a14553b8fc8c1d0290699f21603d5a445b0567abeb65bd8200a4d8d4c";
    public static boolean loadTeam() {

        String statement1 = "select * from team";
        //System.out.println(statement1);

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement1);) {
            //try to get from team db
            JSONParser parser = new JSONParser();
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                //database is empty 
                //call api
                //read json and store into db 

                PreparedStatement stmt2 = conn.prepareStatement("insert into team values (?, ?)");

                try {
                    Object obj = parser.parse(new FileReader("C:\\response.json"));
                    JSONObject jsonObject = (JSONObject) obj;

                    JSONObject api = (JSONObject) jsonObject.get("api");
                    //get number of teams 
                    Long resultsNum = (Long) api.get("results");
                    System.out.println(resultsNum);
                    JSONObject teams = (JSONObject) api.get("teams");

                    for (Object key : teams.keySet()) {
                        //based on you key types
                        String keyStr = (String) key;
                        Object keyvalue = teams.get(keyStr);

                        //Print key and value
                        System.out.println("key: " + keyStr + " value: " + keyvalue);

                        //get team name and store in database
                        JSONObject eachTeam = (JSONObject) teams.get(keyStr);
                        String tID = (String) eachTeam.get("name");

                        stmt2.setString(1, keyStr);
                        stmt2.setString(2, tID);
                        stmt2.executeUpdate();
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }
                //stmt.executeUpdate();
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {

        }

        return false;
    }

    public static String loadTournament() {
        //2 i the id for EPL 
        //for scalability sake in future, may wish to know the different tounamenet id
        String statement1 = "select * from tournament where tournamentId = '2'";
        //System.out.println(statement1);
        String logId = "false";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement1);) {
            //try to get from team db
            JSONParser parser = new JSONParser();
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                //database is empty 
                //call api
                //read json and store into db 

                PreparedStatement stmt2 = conn.prepareStatement("insert into tournament values (?, ?, ?, ?)");

                try {
                    Object obj = parser.parse(new FileReader("C:\\tournament.json"));
                    JSONObject jsonObject = (JSONObject) obj;

                    JSONObject api = (JSONObject) jsonObject.get("api");
                    //get number of teams 
                    Long resultsNum = (Long) api.get("results");
                    System.out.println(resultsNum);

                    //EPL IS ID 2
                    JSONObject leagues = (JSONObject) api.get("leagues");
                    JSONObject league = (JSONObject) leagues.get("2");

                    String leagueID = (String) league.get("league_id");
                    stmt2.setString(1, leagueID);

                    String leagueName = (String) league.get("name");
                    stmt2.setString(2, leagueName);

                    String start = (String) league.get("season_start");
                    stmt2.setString(3, start);

                    String end = (String) league.get("season_end");
                    stmt2.setString(4, end);

                    stmt2.executeUpdate();
                    //System.out.println(tID + tName);

                    PreparedStatement stmt3 = conn.prepareStatement("SELECT * FROM `league` where tournamentId=" + leagueID);

                    rs = stmt3.executeQuery();
                    if (!rs.next()) {
                        //INSERT INTO `league` (`leagueId`, `tournamentId`, `pointsAllocated`, `leagueName`) VALUES (NULL, '2', '5', 'df'); 
                        PreparedStatement stmt4 = conn.prepareStatement("insert into `league` (tournamentId, pointsAllocated, leagueName) values ('" + leagueID + "',1,'" + leagueName + " Public League')");
                        System.out.println(stmt4);
                        stmt4.executeUpdate();

                        //SELECT leagueId FROM league WHERE tournamentId = '2' AND pointsAllocated = 1 AND leagueName= 'Premier League Public League' 
                        String statement5 = "(SELECT leagueId FROM league WHERE tournamentId ='" + leagueID + "'  AND pointsAllocated = 1" + " AND leagueName='" + leagueName + " Public League')";

                        //insert into publicleague(leagueId, prize) values ((SELECT leagueId FROM league WHERE tournamentId = '2' AND pointsAllocated = 1 AND leagueName= 'Premier League Public League' ),'Jersey')
                        System.out.println("insert into publicleague(leagueId, prize) values (" + statement5 + ",'Jersey')");
                        PreparedStatement stmt6 = conn.prepareStatement("insert into publicleague(leagueId, prize) values (" + statement5 + ",'Jersey')");
                        System.out.println(stmt6);
                        stmt6.executeUpdate();

                        PreparedStatement stmt7 = conn.prepareStatement("insert into log(username, leagueId, points) values ('admin', " + statement5 + ", -1)");
                        System.out.println(stmt7);
                        stmt7.executeUpdate();

                        //SELECT logId FROM log l inner join league m on l.leagueId = m.leagueId where m.tournamentId = 2 
                        PreparedStatement stmt8 = conn.prepareStatement("SELECT logId FROM log l inner join league m on l.leagueId = m.leagueId where m.tournamentId = 2");
                        rs = stmt8.executeQuery();
                        if (rs.next()) {
                            logId = rs.getString(1);
                            System.out.println(logId);
                        }

                    } else {
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
                return logId;
            }

        } catch (SQLException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {

        }

        return logId;

    }

    public static boolean loadMatch() {

        String statement1 = "delete FROM `match` ";
        //System.out.println(statement1);

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement1);) {
            //try to get from team db
            JSONParser parser = new JSONParser();
            stmt.executeUpdate();
            //if (!rs.next()) {
            //database is empty 
            //call api
            //read json and store into db 

            PreparedStatement stmt2 = conn.prepareStatement("insert into `match` values (?, ?, ?, ?, ?, ?, ?, ?)");

            try {
                Object obj = parser.parse(new FileReader("C:\\match1.json"));
                JSONObject jsonObject = (JSONObject) obj;

                /*
                HttpResponse<JsonNode> response = Unirest.get("https://api-football-v1.p.mashape.com/fixtures/league/2")
                        .header("X-Mashape-Key", "KlMp6AFpI6mshMZeTmH8WA9qrKHVp1AuUANjsnoJbPWm4lRZPr")
                        .header("Accept", "application/json")
                        .asJson();
                 */
                JSONObject api = (JSONObject) jsonObject.get("api");
                //get number of teams 
                Long resultsNum = (Long) api.get("results");
                System.out.println(resultsNum);
                JSONObject fixtures = (JSONObject) api.get("fixtures");

                for (Object key : fixtures.keySet()) {
                    //based on you key types
                    String keyStr = (String) key;
                    Object keyvalue = fixtures.get(keyStr);

                    //Print key and value
                    System.out.println("key: " + keyStr + " value: " + keyvalue);

                    //get team name and store in database
                    JSONObject eachMatch = (JSONObject) fixtures.get(keyStr);
                    String leagueID = (String) eachMatch.get("league_id");
                    String strDate = (String) eachMatch.get("event_date");
                    String eTime = (String) eachMatch.get("event_timestamp");
                    String team1 = (String) eachMatch.get("homeTeam_id");
                    String team2 = (String) eachMatch.get("awayTeam_id");
                    String team1_score = (String) eachMatch.get("goalsHomeTeam");
                    String team2_score = (String) eachMatch.get("goalsAwayTeam");

                    //yyy-mm-dd
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                    SimpleDateFormat formatter2 = new SimpleDateFormat("hh:mm:ss"); // This line

                    Date d1 = new java.sql.Date(formatter.parse(strDate).getTime());
                    System.out.println(d1);

                    //Time t1 = new java.sql.Time(formatter2.parse(eTime).getTime());
                    //System.out.println(team1_score);
                    Date date = new Date(Integer.parseInt(eTime) * 1000L); // convert seconds to milliseconds

                    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd"); // the format of your date 
                    SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss"); // the format of your date

                    String formattedDate = dateFormat1.format(date);
                    System.out.println(formattedDate);

                    String formattedTime = dateFormat2.format(date);
                    System.out.println(formattedTime);

                    if (team1_score == null) {
                        team1_score = "-1";
                    }
                    if (team2_score == null) {
                        team2_score = "-1";
                    }

                    stmt2.setString(1, keyStr);
                    stmt2.setString(2, leagueID);
                    stmt2.setString(3, formattedDate);
                    stmt2.setString(4, formattedTime);
                    stmt2.setString(5, team1);
                    stmt2.setString(6, team2);
                    stmt2.setInt(7, Integer.parseInt(team1_score));
                    stmt2.setInt(8, Integer.parseInt(team2_score));
                    stmt2.executeUpdate();
                }
                return true;
            } catch (Exception e) {
                System.out.println(e);
            }
            //stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {

        }
        return false;
    }

    public static Boolean addSpecials(String[] specialsList, String id) {

        String updates = "";
        for (int i = 1; i < specialsList.length; i++) {
            updates = updates + "" + specialsList[i - 1] + ",";
        }
        updates = updates + "" + specialsList[specialsList.length - 1] + "";

        System.out.println(updates);

        for (String specials : specialsList) {
            System.out.println(specials);
        }

        String statement1 = "update specials set status ='N'";
        String statement2 = "update specials set status ='Y' where specialsId IN (" + updates + ");";
        String statement3 = "SELECT specialsId FROM specials WHERE status = 'Y' ";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt1 = conn.prepareStatement(statement1); PreparedStatement stmt2 = conn.prepareStatement(statement2);) {
            stmt1.executeUpdate();
            stmt2.executeUpdate();


            PreparedStatement stmt3 = conn.prepareStatement(statement3);
            ResultSet rs = stmt3.executeQuery();
            while(rs.next()){
                String statement4 = "insert into specialslog (logid, specialsId, prediction) values (" + Integer.parseInt(id) + ", " + rs.getString(1) + ", -1)";
                System.out.println(statement4);
                PreparedStatement stmt4 = conn.prepareStatement(statement4);
                stmt4.executeUpdate();
            }

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SpecialsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     public static int loadMatchesFromAPI(String matchId,String leagueID, String matchDate, String matchTime, String team1,String team2,String team1Score,String team2Score){
        int status = 0;
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("INSERT INTO testloadmatch(matchId,tournamentId,date,time,team1,team2,team1_score,team2_score) VALUES(?,?,?,?,?,?,?,?)");) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                    SimpleDateFormat formatter2 = new SimpleDateFormat("hh:mm:ss"); // This line

                    Date d1 = new java.sql.Date(formatter.parse(matchDate).getTime());
                    System.out.println(d1);

                    //Time t1 = new java.sql.Time(formatter2.parse(eTime).getTime());
                    //System.out.println(team1_score);
                    Date date = new Date(Integer.parseInt(matchTime) * 1000L); // convert seconds to milliseconds

                    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd"); // the format of your date 
                    SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss"); // the format of your date

                    String formattedDate = dateFormat1.format(date);
                    System.out.println(formattedDate);

                    String formattedTime = dateFormat2.format(date);
                    System.out.println(formattedTime);

                    if (team1Score == null) {
                        team1Score = "-1";
                    }
                    if (team2Score == null) {
                        team2Score = "-1";
                    }

                    ps.setString(1, matchId);
                    ps.setString(2, leagueID);
                    ps.setString(3, formattedDate);
                    ps.setString(4, formattedTime);
                    ps.setString(5, team1);
                    ps.setString(6, team2);
                    ps.setInt(7, Integer.parseInt(team1Score));
                    ps.setInt(8, Integer.parseInt(team2Score));
                    ps.executeUpdate();
                    return status;
        } catch (SQLException ex) {
            Logger.getLogger(APIDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(APIDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(APIDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
}
