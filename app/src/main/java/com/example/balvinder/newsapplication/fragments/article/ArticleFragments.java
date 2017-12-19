package com.example.balvinder.newsapplication.fragments.article;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.balvinder.newsapplication.NewsConstant;
import com.example.balvinder.newsapplication.R;
import com.example.balvinder.newsapplication.exceptions.NewsException;
import com.example.balvinder.newsapplication.interfaces.ArticleResponseListener;
import com.example.balvinder.newsapplication.network.ApiHandler;
import com.example.balvinder.newsapplication.network.model.ArticleResponseModel;
import com.example.balvinder.newsapplication.utils.DividerItemDecoration;
import com.example.balvinder.newsapplication.utils.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by balvinder on 18/12/17.
 */

public class ArticleFragments extends Fragment implements ArticleResponseListener, ArticleListAdapter.ItemClickListener {

    RecyclerView recyclerView;
    ArticleListAdapter adapter;
    Utility utility;
    List<ArticleResponseModel> dataList;
    private ProgressDialog pDialog;
    String sourceName;
    EditText searchEditText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            sourceName = bundle.getString(getString(R.string.source_name));
        }
        return inflater.inflate(R.layout.article_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initComponents(view);
    }

    private void initComponents(View view) {
        utility = new Utility();
        searchEditText = (EditText) view.findViewById(R.id.srchEditText);

        recyclerView = (RecyclerView) view.findViewById(R.id.articleRecyclerView);
        pDialog = Utility.getProgressDialog(getActivity(), getString(R.string.pls_wait), false);
        pDialog.dismiss();
        dataList = new ArrayList<>();

        searchEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }


            @Override
            public void afterTextChanged(Editable arg0) {
                String text = searchEditText.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }
        });

        if(sourceName.contains(" ")){
            sourceName = sourceName.replace(' ', '-');
        }

        callArticleAPI(sourceName);
    }

    @Override
    public void onItemClicked(String url) {
        utility.putVisibleFragment(getActivity(), NewsConstant.ARTICLE_DETAIL_FRAGMENT);
        callArticleDetailFragment(url);

    }


    private void callArticleDetailFragment(String url) {
        new Utility().putVisibleFragment(getActivity(),NewsConstant.ARTICLE_DETAIL_FRAGMENT);
        final FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        ArticleDetailFragment articleDetailFragments = new ArticleDetailFragment();
        Bundle bundle=new Bundle();
        bundle.putString(getString(R.string.article_url),url);
        articleDetailFragments.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragmentContainer, articleDetailFragments, getResources().getString(R.string.article_detail_fragment));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void callArticleAPI(String sourceName) {
        if (!pDialog.isShowing()) {
            pDialog.show();
        }
        ApiHandler apiHandler = ApiHandler.getApiHandler(getActivity());
        apiHandler.setArticleResponseListener(this);
        apiHandler.getNewsArticleFromApi(sourceName);
    }

    @Override
    public void onArticleResponse(ArticleResponseModel response, NewsException e) {
        if (e == null) {

            if (response.getStatus().equalsIgnoreCase(NewsConstant.SUCCESS_STATUS)) {
                pDialog.dismiss();
                dataList.addAll(response.getArticles());
                adapter = new ArticleListAdapter(getActivity(), recyclerView, dataList, this);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),R.drawable.divider));
                recyclerView.setAdapter(adapter);
            }else{
                pDialog.dismiss();
                utility.getDialogMessage(getActivity(), response.getMessage());
            }


        } else {
            pDialog.dismiss();
            utility.showNetworkError(getActivity());

        }
    }


}
