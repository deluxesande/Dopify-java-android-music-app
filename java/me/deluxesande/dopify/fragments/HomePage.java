package me.deluxesande.dopify.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import me.deluxesande.dopify.MainActivity;
import me.deluxesande.dopify.R;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;

public class HomePage extends Fragment {

    View view;
    Button end_button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_homepage, container, false);

        ImageView banner_image = view.findViewById(R.id.banner_image);
        ImageView musicCover1 = view.findViewById(R.id.music_cover_1);
        ImageView musicCover2 = view.findViewById(R.id.music_cover_2);
        ImageView recommended_1 = view.findViewById(R.id.recommended_1);
        ImageView best_1 = view.findViewById(R.id.best_1);

        String imageUrl = "https://images.unsplash.com/photo-1732215684543-86b7034ff5a6?q=80&w=1742&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D";

        Glide.with(this).load(imageUrl).into(banner_image);
        Glide.with(this).load(imageUrl).into(musicCover1);
        Glide.with(this).load(imageUrl).into(musicCover2);
        Glide.with(this).load(imageUrl).into(recommended_1);
        Glide.with(this).load(imageUrl).into(best_1);

        // Test API logic
        end_button = view.findViewById(R.id.end_button);

        end_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(getContext());
                String url = "https://api.restful-api.dev/objects";

                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "That didn't work!", Toast.LENGTH_SHORT).show();
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(request);

                // Request a string response from the provided URL.
//                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                // Display the first 500 characters of the response string.
//                                Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
//                            }
//                        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getContext(), "That didn't work!", Toast.LENGTH_SHORT).show();
//                    }
//                });


            }
        });

        return view;
    }


}