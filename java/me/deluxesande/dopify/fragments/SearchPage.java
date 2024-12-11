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

import me.deluxesande.dopify.R;
import me.deluxesande.dopify.services.SpotifyService;

public class SearchPage extends Fragment {

    View view;
    EditText searchInput;
    ImageView searchVector;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_searchpage, container, false);

        final SpotifyService spotifyService = new SpotifyService(getContext());

        searchInput = view.findViewById(R.id.search_input);
        searchVector = view.findViewById(R.id.search_here_vector);

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
                        searchVector.setVisibility(View.GONE);
                        Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();
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
}