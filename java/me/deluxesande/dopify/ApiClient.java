package me.deluxesande.dopify;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class ApiClient {
    private static final String BASE_URL = BuildConfig.BASE_URL;
    private static final String API_KEY = BuildConfig.API_KEY;
    private final RequestQueue requestQueue;

    public ApiClient(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public void getData(String query, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + "/search/?type=multi&offset=0&limit=10&numberOfTopResults=5&q=" + query;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, listener, errorListener) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("x-rapidapi-key", API_KEY);
                headers.put("x-rapidapi-host", BuildConfig.API_HOST);
                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }
}