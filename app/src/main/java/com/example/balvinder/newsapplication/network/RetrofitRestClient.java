package com.example.balvinder.newsapplication.network;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.balvinder.newsapplication.NewsConstant;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by balvinder on 18/12/17.
 */

public class RetrofitRestClient {
    private static ApiService apiService;
    private static Context mcontext;

    public static ApiService getInstance(Context context) {

        if (apiService == null) {
            mcontext = context;
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = getOkHttpClient(logging);
            Retrofit retrofit = getRetrofit(client);
            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }

    @NonNull
    private static Retrofit getRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(NewsConstant.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @NonNull
    private static OkHttpClient getOkHttpClient(HttpLoggingInterceptor logging) {
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Request request = getDefaultRequest(original);
                        Response response = chain.proceed(request);
                        return response;
                    }
                })
                .addInterceptor(logging)
                .build();
    }

    private static Request getDefaultRequest(Request original) {
        Request request = original.newBuilder().
                addHeader("apikey", NewsConstant.API_KEY).
              build();
        return request;
    }
}
