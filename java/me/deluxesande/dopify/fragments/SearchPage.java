package me.deluxesande.dopify.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import me.deluxesande.dopify.R;
import me.deluxesande.dopify.adapters.MusicAdapter;
import me.deluxesande.dopify.models.Music;
import me.deluxesande.dopify.services.SpotifyService;

public class SearchPage extends Fragment {

    View view;
    EditText searchInput;
    ImageView searchVector;
    RecyclerView searchResultsList;
    MusicAdapter musicAdapter;
    List<Music> musicList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_searchpage, container, false);

        final SpotifyService spotifyService = new SpotifyService(getContext());

        searchInput = view.findViewById(R.id.search_input);
        searchVector = view.findViewById(R.id.search_here_vector);
        searchResultsList = view.findViewById(R.id.search_results_list);

        musicList = new ArrayList<>();
        musicAdapter = new MusicAdapter(musicList);
        searchResultsList.setLayoutManager(new LinearLayoutManager(getContext()));
        searchResultsList.setAdapter(musicAdapter);

        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                spotifyService.search(charSequence.toString(), new SpotifyService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Object response) {
                        List<Music> listObject = parseMusicResponse((JSONObject) response);
                        searchVector.setVisibility(View.GONE);
                        searchResultsList.setVisibility(View.VISIBLE);
                        musicList.clear();
                        musicList.addAll(listObject);
                        musicAdapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), String.valueOf(listObject.size()), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Do nothing
            }
        });

        return view;
    }

    private List<Music> parseMusicResponse(JSONObject response) {
        List<Music> musicList = new ArrayList<>();
        try {
            JSONArray items = response.getJSONObject("tracks").getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject track = items.getJSONObject(i).getJSONObject("data");
                String uri = track.getString("uri");
                String id = track.getString("id");
                String name = track.getString("name");
                String album = track.getJSONObject("albumOfTrack").getString("name");
                String artist = "";
                if (track.has("artists")) {
                    artist = track.getJSONObject("artists").getJSONArray("items").getJSONObject(0).getJSONObject("profile").getString("name");
                }
                String contentRating = track.getJSONObject("contentRating").getString("label");
                long durationMilliseconds = track.getJSONObject("duration").getLong("totalMilliseconds");
                int durationMinutes = (int) (durationMilliseconds / 60000); // Convert milliseconds to minutes
                boolean playability = track.getJSONObject("playability").getBoolean("playable");
                String coverArtUrl = track.getJSONObject("albumOfTrack").getJSONObject("coverArt").getJSONArray("sources").getJSONObject(0).getString("url");

                musicList.add(new Music(name, artist, uri, album, id, contentRating, durationMinutes, playability, coverArtUrl));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return musicList;
    }
}