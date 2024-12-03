package me.deluxesande.dopify.fragments;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class MyDataService {

    public final String API_URL = "https://api.restful-api.dev/objects";

    Context context;

    public MyDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);
        void onResponse(Object response);
    }

    public void getData(VolleyResponseListener volleyResponseListener) {
        String url = API_URL;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject objectInfo = response.optJSONObject(0);
                String objectName = objectInfo.optString("name");

                volleyResponseListener.onResponse(objectName);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("Something went wrong");
            }
        });

        // Add a request (in this example, called stringRequest) to your RequestQueue.
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}
