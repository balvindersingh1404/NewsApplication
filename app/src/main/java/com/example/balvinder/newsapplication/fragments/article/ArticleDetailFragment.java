package com.example.balvinder.newsapplication.fragments.article;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.balvinder.newsapplication.R;
import com.example.balvinder.newsapplication.utils.Utility;

/**
 * Created by balvinder on 18/12/17.
 */

public class ArticleDetailFragment extends Fragment {
    WebView webView;
    String urlToLoad;
    private ProgressDialog pDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            urlToLoad = bundle.getString(getString(R.string.article_url));
        }
        return inflater.inflate(R.layout.article_detail_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initComponents(view);
      //  Toast.makeText(getActivity(), urlToLoad, Toast.LENGTH_LONG).show();
    }


    private void initComponents(View view) {
        webView = (WebView) view.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        pDialog = Utility.getProgressDialog(getActivity(), getString(R.string.pls_wait), false);
        if (!pDialog.isShowing())
            pDialog.show();
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pDialog.dismiss();
            }
        });

        webView.loadUrl(urlToLoad);

    }

}
