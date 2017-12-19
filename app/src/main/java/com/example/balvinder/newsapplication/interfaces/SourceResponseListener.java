package com.example.balvinder.newsapplication.interfaces;

import com.example.balvinder.newsapplication.exceptions.NewsException;
import com.example.balvinder.newsapplication.network.model.SourceResponseModel;

/**
 * Created by balvinder on 18/12/17.
 */

public interface SourceResponseListener {
    void onSourceResponse(SourceResponseModel response, NewsException e);

}
