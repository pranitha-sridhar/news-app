package com.example.news;

import java.util.List;

public class Newsclass {
    String status;
    String totalResults;
    List<Articles> articles;

    public Newsclass(String status, String totalResults, List<Articles> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() { return status; }

    public String getTotalResults() { return totalResults; }

    public List<Articles> getArticles() { return articles; }
}
