package com.example.sti_agent.Model;

public class QuoteCard {


    private String title;
    private int thumbnail;

    public QuoteCard(String title, int thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;

    }







    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }


}
