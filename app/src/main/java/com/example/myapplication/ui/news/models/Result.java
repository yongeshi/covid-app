package com.example.myapplication.ui.news.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("source")
    @Expose
    private Source source;

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("body")
    @Expose
    private String body;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("dateTimePub")
    @Expose
    private String publishedAt;

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUrl() {
        /*
        String regexTarget = "http";
        String replacement = "https";
        String processed = url.replaceAll(regexTarget, replacement);
        return processed;*/
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        /*
        if (image != null) {
            String regexTarget = "http";
            String replacement = "https";
            String processed = image.replaceAll(regexTarget, replacement);
            return processed;
        } else {
            return image;
        }*/
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}