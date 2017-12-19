package com.example.balvinder.newsapplication.network;

import android.content.Context;

import com.example.balvinder.newsapplication.NewsConstant;
import com.example.balvinder.newsapplication.exceptions.ErrorMessage;
import com.example.balvinder.newsapplication.exceptions.NewsException;
import com.example.balvinder.newsapplication.interfaces.ArticleResponseListener;
import com.example.balvinder.newsapplication.interfaces.SourceResponseListener;
import com.example.balvinder.newsapplication.network.model.ArticleResponseModel;
import com.example.balvinder.newsapplication.network.model.SourceResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by balvinder on 18/12/17.
 */

public class ApiHandler {
    static Context mContext;
    SourceResponseListener sourceResponseListener;
    ArticleResponseListener articleResponseListener;

    public void setArticleResponseListener(ArticleResponseListener articleResponseListener) {
        this.articleResponseListener = articleResponseListener;
    }

    public void setSourceResponseListener(SourceResponseListener sourceResponseListener) {
        this.sourceResponseListener = sourceResponseListener;
    }

    public static ApiHandler getApiHandler(Context context) {
        mContext = context;
        return new ApiHandler();
    }

    public void getNewsSourcesFromApi() {

        Call<SourceResponseModel> call = RetrofitRestClient.getInstance(mContext).getSources();

        call.enqueue(new Callback<SourceResponseModel>() {
            @Override
            public void onResponse(Call<SourceResponseModel> call, Response<SourceResponseModel> response) {
                if (response.isSuccessful()) {
                    sourceResponseListener.onSourceResponse(response.body(), null);
                } else {
                    sourceResponseListener.onSourceResponse(null, new NewsException(ErrorMessage.GSON));
                }
            }

            @Override
            public void onFailure(Call<SourceResponseModel> call, Throwable t) {
                if (sourceResponseListener != null) {
                    sourceResponseListener.onSourceResponse(null, new NewsException(ErrorMessage.CONNECTION));
                }
            }
        });

    }


    public void getNewsArticleFromApi(String sourceName) {

        Call<ArticleResponseModel> call = RetrofitRestClient.getInstance(mContext).getArticles(sourceName, NewsConstant.API_KEY);
        call.enqueue(new Callback<ArticleResponseModel>() {
            @Override
            public void onResponse(Call<ArticleResponseModel> call, Response<ArticleResponseModel> response) {
                if (response.isSuccessful()) {
                    articleResponseListener.onArticleResponse(response.body(), null);
                } else{
                    articleResponseListener.onArticleResponse(null, new NewsException(ErrorMessage.GSON));
                }
            }

            @Override
            public void onFailure(Call<ArticleResponseModel> call, Throwable t) {
                if (articleResponseListener != null) {
                    articleResponseListener.onArticleResponse(null, new NewsException(ErrorMessage.CONNECTION));
                }
            }
        });

    }
}
