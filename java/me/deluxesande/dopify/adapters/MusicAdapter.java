package me.deluxesande.dopify.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import me.deluxesande.dopify.R;
import me.deluxesande.dopify.models.Music;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {

    private List<Music> musicList;

    public MusicAdapter(List<Music> musicList) {
        this.musicList = musicList;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_music, parent, false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        Music music = musicList.get(position);
        holder.musicTitle.setText(music.getTitle());
        holder.musicArtist.setText(music.getArtist());
        holder.releaseYear.setText(String.valueOf(music.getReleaseYear()));
        Glide.with(holder.itemView.getContext()).load(music.getCoverArtUrl()).into(holder.coverArt);
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    public static class MusicViewHolder extends RecyclerView.ViewHolder {
        TextView musicTitle;
        TextView musicArtist;
        TextView releaseYear;
        ImageView coverArt;

        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            musicTitle = itemView.findViewById(R.id.music_title);
            musicArtist = itemView.findViewById(R.id.music_artist);
            releaseYear = itemView.findViewById(R.id.release_year);
            coverArt = itemView.findViewById(R.id.cover_art);
        }
    }
}