package com.example.ole.oleandroid.controller.FAQ;

import com.example.ole.oleandroid.controller.Profile;
import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.PostHttp;
import com.example.ole.oleandroid.model.FAQObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FAQDAO {

    public static ArrayList<FAQObject> getFaqs(String category){
        //String category = "Profile";
        PostHttp connection = new PostHttp();
        String response = null;
        String url = DBConnection.getFaq();
        ArrayList<FAQObject> faqs = new ArrayList<>();

        try {
            response = connection.postForm(url, "category="+category);
            System.out.println(response);

            JSONObject result = new JSONObject(response);
            JSONArray arrayResult = result.getJSONArray("results");

            if (arrayResult.length() > 0) {
                for (int i = 0; i<arrayResult.length(); i++){
                    JSONObject faq = arrayResult.getJSONObject(i);
                    String question = faq.getString("question");
                    String answer = faq.getString("answer");
                    int faqId = faq.getInt("faqId");
                    faqs.add(new FAQObject(faqId, answer, question, category));
                }
            } else {
                faqs = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return faqs;
    }
}
