package com.example.ole.oleandroid.controller.DAO;

import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.GetHttp;
import com.example.ole.oleandroid.dbConnection.PostHttp;
import com.example.ole.oleandroid.model.PSpecials;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class PSpecialDAO {

    public static ArrayList<PSpecials> getSpecialsList(int logId, int leagueId) {
        ArrayList<PSpecials> specialsList = new ArrayList<>();

        String url = DBConnection.privateSpecials() + "?logId=" + logId + "&leagueId=" + leagueId + "&username=" + UserDAO.getLoginUser().getUsername();
        System.out.println("Getting specials list, logID:" + logId);

        GetHttp getConnection = new GetHttp();
        String response = null;
        try {
            response = getConnection.run(url);
            System.out.println(response);
            if (response != null) {
                JSONObject result = new JSONObject(response);
                JSONArray specials = result.getJSONArray("results");
                System.out.println(specials.length());
                if (specials.length() > 0) {
                    for (int i = 0; i < specials.length(); i++) {
                        JSONObject specialsObject = specials.getJSONObject(i);
                        PSpecials special = new PSpecials(
                                specialsObject.getInt("specialsId"),
                                specialsObject.getString("description"),
                                specialsObject.getInt("points"),
                                specialsObject.getInt("doubleIt")
                        );

                        if (specialsObject.has("prediction")) {
                            special.setPrediction(specialsObject.getString("prediction"));
                        }
                        specialsList.add(special);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
        return specialsList;
    }

    public static boolean updateSpecialsPrediction(ArrayList<PSpecials> specialsList, int leagueId) throws JSONException, IOException {
        JSONObject parentJson = new JSONObject();
        JSONArray specialArray = new JSONArray();

        for (PSpecials s : specialsList) {
            JSONObject childJson = new JSONObject();
            System.out.println("Special: " + s.getSpecialsID() + ", " + s.getPrediction() + ", " + s.getDoubleIt() + ", " + s.getPoints());
            childJson.put("specialsId", s.getSpecialsID());
            childJson.put("prediction", s.getPrediction());
            if (s.getDoubleIt()) {
                childJson.put("doubleIt", 1);
            } else {
                childJson.put("doubleIt", 0);
            }
            specialArray.put(childJson);
        }
        parentJson.put("params", specialArray);

        String send = "params=" + parentJson.toString() + "&leagueId=" + leagueId + "&username=" + UserDAO.getLoginUser().getUsername();
        System.out.println(send);

        String url = DBConnection.privateSpecials();
        PostHttp connection = new PostHttp();

        String response = connection.postForm(url, send);
        System.out.println(response);

        JSONObject result = new JSONObject(response);
        String status = result.getString("status");

        if (status.equals("successful")) {
            return true;
        }

        return false;
    }

    public static ArrayList<PSpecials> getAllSpecials() {
        ArrayList<PSpecials> specialsList = new ArrayList<>();
        String url = DBConnection.privateSpecials();
        System.out.println("Getting specials list");
        GetHttp getConnection = new GetHttp();
        String response = null;
        try {
            response = getConnection.run(url);
            System.out.println(response);
            if (response != null) {
                JSONObject result = new JSONObject(response);
                JSONArray specials = result.getJSONArray("results");
                System.out.println(specials.length());
                if (specials.length() > 0) {
                    for (int i = 0; i < specials.length(); i++) {
                        JSONObject specialsObject = specials.getJSONObject(i);
                        PSpecials special = new PSpecials(
                                specialsObject.getInt("specialsId"),
                                specialsObject.getString("description"),
                                specialsObject.getInt("points"),
                                specialsObject.getInt("doubleIt"),
                                specialsObject.getString("prediction")
                        );
                        specialsList.add(special);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
        return specialsList;
    }
}
