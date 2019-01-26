package com.example.ole.oleandroid.dbConnection;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

public class VolleyRequest {
    private static RequestQueue requestQueue;

    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public static void setRequestQueue(RequestQueue requestQueue) {
        VolleyRequest.requestQueue = requestQueue;
    }

    public static void addRequestJson(JsonObjectRequest request){
        requestQueue.add(request);
    }

    public static void addRequestString(StringRequest request){
        requestQueue.add(request);
    }
}
