package com.example.balvinder.newsapplication.fragments.source;

import android.app.ProgressDialog;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.balvinder.newsapplication.NewsConstant;
import com.example.balvinder.newsapplication.R;
import com.example.balvinder.newsapplication.exceptions.NewsException;
import com.example.balvinder.newsapplication.fragments.article.ArticleFragments;
import com.example.balvinder.newsapplication.interfaces.SourceResponseListener;
import com.example.balvinder.newsapplication.network.ApiHandler;
import com.example.balvinder.newsapplication.network.model.SourceResponseModel;
import com.example.balvinder.newsapplication.utils.DividerItemDecoration;
import com.example.balvinder.newsapplication.utils.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by balvinder on 18/12/17.
 */

public class SourceFragments extends android.support.v4.app.Fragment implements SourceResponseListener, SourceListAdapter.ItemClickListener {

    RecyclerView recyclerView;
    SourceListAdapter adapter;
    Utility utility;
    List<SourceResponseModel> dataList;
    private ProgressDialog pDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.source_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initComponents(view);
    }

    private void initComponents(View view) {
        utility = new Utility();
        recyclerView = (RecyclerView) view.findViewById(R.id.sourcesRecyclerView);

        pDialog = Utility.getProgressDialog(getActivity(), getString(R.string.pls_wait), false);
        pDialog.dismiss();
        dataList = new ArrayList<>();
        callSourcesAPI();
    }


    private void callSourcesAPI() {
        if (!pDialog.isShowing()) {
            pDialog.show();
        }
        ApiHandler apiHandler = ApiHandler.getApiHandler(getActivity());
        apiHandler.setSourceResponseListener(this);
        apiHandler.getNewsSourcesFromApi();
    }


    @Override
    public void onSourceResponse(SourceResponseModel response, NewsException e) {
        if (e == null) {

            if (response.getStatus().equalsIgnoreCase(NewsConstant.SUCCESS_STATUS)) {
                pDialog.dismiss();
                dataList.addAll(response.getSources());
                adapter = new SourceListAdapter(getActivity(), recyclerView, dataList, this);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),R.drawable.divider));
                recyclerView.setAdapter(adapter);
            } else {
                pDialog.dismiss();
                utility.getDialogMessage(getActivity(), response.getMessage());

            }


        } else {
            pDialog.dismiss();
            utility.showNetworkError(getActivity());

        }
    }

    @Override
    public void onItemClicked(String sourceName) {
        utility.putVisibleFragment(getActivity(), NewsConstant.ARTICLE_FRAGMENT);
        callArticleFragment(sourceName);
    }


    private void callArticleFragment(String sourceName) {
        new Utility().putVisibleFragment(getActivity(), NewsConstant.ARTICLE_FRAGMENT);
        final FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        ArticleFragments articleFragments = new ArticleFragments();
        Bundle bundle = new Bundle();
        bundle.putString(getString(R.string.source_name), sourceName);
        articleFragments.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragmentContainer, articleFragments, getResources().getString(R.string.article_fragment));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
