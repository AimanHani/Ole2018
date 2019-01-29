/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backGroudTask;

import controller.APIDAO;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import servlet.APICallServlet;

/**
 *
 * @author user
 */
public class UpdateMatchTableByTimer implements Runnable {

    @Override
    public void run() {
        JSONArray list = new JSONArray();
        JSONObject parentJson = new JSONObject();
        JSONObject json = APIDAO.loadAPIMatch();
        JSONObject result = new JSONObject();
        JSONArray array = null;
        try {
            array = json.getJSONArray("array");
            JSONObject jb = null;

            for (int i = 0; i < array.length(); i++) {
                jb = array.getJSONObject(i);
            }
            JSONObject jb1 = null;
            jb1 = jb.getJSONObject("api");
            JSONObject jb2 = null;
            JSONObject jb3 = null;
            int status = 0;
            JSONObject finalResult = new JSONObject();
            jb3 = jb1.getJSONObject("fixtures");
            Iterator it = jb3.keys();

            while (it.hasNext()) {
                String keyStr = (String) it.next();
                Object keyvalue = jb3.get(keyStr);
                JSONObject eachMatch = (JSONObject) jb3.get(keyStr);
                String leagueID = (String) eachMatch.get("league_id");
                String strDate = (String) eachMatch.get("event_date");
                String eTime = (String) eachMatch.get("event_timestamp");
                String team1 = (String) eachMatch.get("homeTeam");
                String team2 = (String) eachMatch.get("awayTeam");
                String team1_score;
                String team2_score;
                if (eachMatch.get("goalsHomeTeam").equals(null)) {
                    team1_score = null;
                } else {
                    team1_score = (String) eachMatch.get("goalsHomeTeam");
                }

                if (eachMatch.get("goalsAwayTeam").equals(null)) {
                    team2_score = null;
                } else {
                    team2_score = (String) eachMatch.get("goalsAwayTeam");
                }

                status += APIDAO.loadMatchesFromAPI(keyStr, leagueID, strDate, eTime, team1, team2, team1_score, team2_score);

                it.remove(); // avoids a ConcurrentModificationException

            }
            if (status > 0) {
                result.put("status", "error");
            } else {
                result.put("status", "successful");
                System.out.println("update testload match table successfully");
            }

        } catch (JSONException ex) {
            Logger.getLogger(APICallServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
