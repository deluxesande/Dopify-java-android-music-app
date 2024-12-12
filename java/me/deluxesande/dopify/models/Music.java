package me.deluxesande.dopify.models;

public class Music {
    private String title;
    private String artist;
    private String uri;
    private String album;
    private String id;
    private String contentRating;
    private int duration; // Duration in minutes
    private boolean playability;
    private String coverArtUrl;

    public Music(String title, String artist, String uri, String album, String id, String contentRating, int duration, boolean playability, String coverArtUrl) {
        this.title = title;
        this.artist = artist;
        this.uri = uri;
        this.album = album;
        this.id = id;
        this.contentRating = contentRating;
        this.duration = duration;
        this.playability = playability;
        this.coverArtUrl = coverArtUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContentRating() {
        return contentRating;
    }

    public void setContentRating(String contentRating) {
        this.contentRating = contentRating;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isPlayability() {
        return playability;
    }

    public void setPlayability(boolean playability) {
        this.playability = playability;
    }

    public String getCoverArtUrl() {
        return coverArtUrl;
    }

    public void setCoverArtUrl(String coverArtUrl) {
        this.coverArtUrl = coverArtUrl;
    }
}