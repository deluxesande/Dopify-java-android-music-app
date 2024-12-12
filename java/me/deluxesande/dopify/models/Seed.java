package me.deluxesande.dopify.models;

public class Seed {
    private int initialPoolSize;
    private int afterFilteringSize;
    private int afterRelinkingSize;
    private String id;
    private String type;

    // Getters and Setters

    public int getInitialPoolSize() {
        return initialPoolSize;
    }

    public void setInitialPoolSize(int initialPoolSize) {
        this.initialPoolSize = initialPoolSize;
    }

    public int getAfterFilteringSize() {
        return afterFilteringSize;
    }

    public void setAfterFilteringSize(int afterFilteringSize) {
        this.afterFilteringSize = afterFilteringSize;
    }

    public int getAfterRelinkingSize() {
        return afterRelinkingSize;
    }

    public void setAfterRelinkingSize(int afterRelinkingSize) {
        this.afterRelinkingSize = afterRelinkingSize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}