package com.example.balvinder.newsapplication.network;

import com.example.balvinder.newsapplication.network.model.ArticleResponseModel;
import com.example.balvinder.newsapplication.network.model.SourceResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by balvinder on 18/12/17.
 */

public interface ApiService {

    @GET("sources")
    Call<SourceResponseModel> getSources();


    @GET("articles")
    Call<ArticleResponseModel> getArticles(
            @Query("source") String source,
            @Query("apiKey") String apiKey
    );

}
