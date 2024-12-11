package me.deluxesande.dopify.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;

public class DopifySingleton {
    private static DopifySingleton instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private DopifySingleton(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized DopifySingleton getInstance(Context context) {
        if (instance == null) {
            instance = new DopifySingleton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}