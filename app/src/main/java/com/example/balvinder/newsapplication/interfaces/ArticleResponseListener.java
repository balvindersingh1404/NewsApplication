package com.example.balvinder.newsapplication.interfaces;

import com.example.balvinder.newsapplication.exceptions.NewsException;
import com.example.balvinder.newsapplication.network.model.ArticleResponseModel;

/**
 * Created by balvinder on 18/12/17.
 */

public interface ArticleResponseListener {
    void onArticleResponse(ArticleResponseModel response, NewsException e);

}
