package me.deluxesande.dopify.models;

public class Music {
    private String title;
    private String artist;
    private String albumUri;
    private String coverArtUrl;
    private int releaseYear;

    public Music(String title, String artist, String albumUri, String coverArtUrl, int releaseYear) {
        this.title = title;
        this.artist = artist;
        this.albumUri = albumUri;
        this.coverArtUrl = coverArtUrl;
        this.releaseYear = releaseYear;
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

    public String getAlbumUri() {
        return albumUri;
    }

    public void setAlbumUri(String albumUri) {
        this.albumUri = albumUri;
    }

    public String getCoverArtUrl() {
        return coverArtUrl;
    }

    public void setCoverArtUrl(String coverArtUrl) {
        this.coverArtUrl = coverArtUrl;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}