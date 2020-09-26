package com.aja.loancare.model;

public class NewsMOdel {
    String title;
    String link;
    String description;
    String pubdate;

    public NewsMOdel(String title, String link, String description, String pubdate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.pubdate = pubdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }
}
