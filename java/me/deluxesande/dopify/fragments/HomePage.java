package me.deluxesande.dopify.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.deluxesande.dopify.R;
import me.deluxesande.dopify.adapters.TrackAdapter;
import me.deluxesande.dopify.models.Artist;
import me.deluxesande.dopify.models.Image;
import me.deluxesande.dopify.models.Music;
import me.deluxesande.dopify.models.Track;
import me.deluxesande.dopify.models.Album;
import me.deluxesande.dopify.services.SpotifyService;

public class HomePage extends Fragment {

    View view;
    RecyclerView popularTrendingRecyclerView;
    TrackAdapter trackAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_homepage, container, false);

        popularTrendingRecyclerView = view.findViewById(R.id.popular_trending_recycler_view);
        popularTrendingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        fetchPopularTrendingData();

        return view;
    }

    private void fetchPopularTrendingData() {
        final SpotifyService spotifyService = new SpotifyService(getContext());

        spotifyService.fetchPopularRecommendations(new SpotifyService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Object response) {
                List<Track> musicList = parseMusicResponse((JSONObject) response);
                displayPopularTrendingData(musicList);
            }
        });
    }

    private List<Track> parseMusicResponse(JSONObject response) {
    List<Track> trackList = new ArrayList<>();
    try {
        JSONArray tracksArray = response.getJSONArray("tracks");
        for (int i = 0; i < tracksArray.length(); i++) {
            JSONObject trackObject = tracksArray.getJSONObject(i);
            Track track = new Track();

            // Parse track details
            track.setId(trackObject.getString("id"));
            track.setName(trackObject.getString("name"));
            track.setUri(trackObject.getString("uri"));
            track.setDurationMs(trackObject.getInt("duration_ms"));
            track.setExplicit(trackObject.getBoolean("explicit"));
            track.setPreviewUrl(trackObject.getString("preview_url"));
            track.setPopularity(trackObject.getInt("popularity"));

            // Parse album details
            JSONObject albumObject = trackObject.getJSONObject("album");
            Album album = new Album();
            album.setId(albumObject.getString("id"));
            album.setName(albumObject.getString("name"));
            album.setReleaseDate(albumObject.getString("release_date"));
            album.setAlbumType(albumObject.getString("album_type"));

            // Parse album images
            JSONArray imagesArray = albumObject.getJSONArray("images");
            List<Image> images = new ArrayList<>();
            for (int j = 0; j < imagesArray.length(); j++) {
                JSONObject imageObject = imagesArray.getJSONObject(j);
                Image image = new Image();
                image.setHeight(imageObject.getInt("height"));
                image.setWidth(imageObject.getInt("width"));
                image.setUrl(imageObject.getString("url"));
                images.add(image);
            }
            album.setImages(images);

            // Parse album artists
            JSONArray albumArtistsArray = albumObject.getJSONArray("artists");
            List<Artist> albumArtists = new ArrayList<>();
            for (int j = 0; j < albumArtistsArray.length(); j++) {
                JSONObject artistObject = albumArtistsArray.getJSONObject(j);
                Artist artist = new Artist();
                artist.setId(artistObject.getString("id"));
                artist.setName(artistObject.getString("name"));
                artist.setUri(artistObject.getString("uri"));
                albumArtists.add(artist);
            }
            album.setArtists(albumArtists);
            track.setAlbum(album);

            // Parse track artists
            JSONArray trackArtistsArray = trackObject.getJSONArray("artists");
            List<Artist> trackArtists = new ArrayList<>();
            for (int j = 0; j < trackArtistsArray.length(); j++) {
                JSONObject artistObject = trackArtistsArray.getJSONObject(j);
                Artist artist = new Artist();
                artist.setId(artistObject.getString("id"));
                artist.setName(artistObject.getString("name"));
                artist.setUri(artistObject.getString("uri"));
                trackArtists.add(artist);
            }
            track.setArtists(trackArtists);

            trackList.add(track);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return trackList;
}

    private void displayPopularTrendingData(List<Track> musicList) {
        trackAdapter = new TrackAdapter(getContext(), musicList);
        popularTrendingRecyclerView.setAdapter(trackAdapter);
    }
}