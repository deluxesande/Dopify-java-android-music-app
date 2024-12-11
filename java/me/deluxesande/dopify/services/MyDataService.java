package me.deluxesande.dopify.services;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import me.deluxesande.dopify.BuildConfig;
import me.deluxesande.dopify.network.MySingleton;

public class MyDataService {

    private static final String BASE_URL = BuildConfig.BASE_URL;
    private static final String API_KEY = BuildConfig.API_KEY;

    Context context;

    public MyDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);
        void onResponse(Object response);
    }

    public void getData(VolleyResponseListener volleyResponseListener) {
        String url = BASE_URL + "/search/?type=multi&offset=0&limit=10&numberOfTopResults=5&q=";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Handle the JSONObject response
                volleyResponseListener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("MyDataService", "Something went wrong", error);
                volleyResponseListener.onError("Something went wrong");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("x-rapidapi-key", API_KEY);
                headers.put("x-rapidapi-host", BuildConfig.API_HOST);
                return headers;
            }
        };

        // Add a request (in this example, called stringRequest) to your RequestQueue.
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}