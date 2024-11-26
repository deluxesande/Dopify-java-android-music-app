package me.deluxesande.dopify;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;

public class ApiClient {
    private static final String BASE_URL = BuildConfig.BASE_URL;
    private static final String API_KEY = BuildConfig.API_KEY;
    private final OkHttpClient client;

    public ApiClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }

    public void getData(String query, Callback callback) {
        String url = BASE_URL + "/search/?type=multi&offset=0&limit=10&numberOfTopResults=5&q=" + query;
        Request request = new Request.Builder()
                .url(url)
                .addHeader("x-rapidapi-key", API_KEY)
                .addHeader("x-rapidapi-host", BuildConfig.API_HOST)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
