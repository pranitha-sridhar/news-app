package com.example.news;

public class Articles {
    String author;
    String title;
    String description;
    String url;
    String urlToImage;
    String publishedAt;
    String content;

    public Articles(String author, String title, String description, String url, String urlToImage, String publishedAt, String content) {

        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }
    public String getTitle() { return title; }
    public String getDescription() {
        return description;
    }
    public String getUrl() {
        return url;
    }
    public String getUrlToImage() {
        return urlToImage;
    }
    public String getPublishedAt() {
        return publishedAt;
    }
    public String getContent() {
        return content;
    }
}
