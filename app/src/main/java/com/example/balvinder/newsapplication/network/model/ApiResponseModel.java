package com.example.balvinder.newsapplication.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by balvinder on 18/12/17.
 */

public class ApiResponseModel {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("sources")
    @Expose
    private List<SourceResponseModel> sources = null;

    @SerializedName("articles")
    @Expose
    private List<ArticleResponseModel> articles = null;

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SourceResponseModel> getSources() {
        return sources;
    }

    public void setSources(List<SourceResponseModel> sources) {
        this.sources = sources;
    }
    public List<ArticleResponseModel> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleResponseModel> articles) {
        this.articles = articles;
    }
}
