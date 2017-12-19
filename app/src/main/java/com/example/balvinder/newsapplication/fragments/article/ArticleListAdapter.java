package com.example.balvinder.newsapplication.fragments.article;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.balvinder.newsapplication.R;
import com.example.balvinder.newsapplication.network.model.ArticleResponseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by balvinder on 18/12/17.
 */

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ViewHolder> {

    Context context;
    List<ArticleResponseModel> dataList;
    RecyclerView recyclerView;
    private ArticleListAdapter.ItemClickListener listener;


    public ArticleListAdapter(Context context, RecyclerView recyclerView, List<ArticleResponseModel> dataList, ArticleListAdapter.ItemClickListener itemClickListener) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.dataList = dataList;
        this.listener = itemClickListener;
    }

    @Override
    public ArticleListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_item_layout, null);
        ArticleListAdapter.ViewHolder viewHolder = new ArticleListAdapter.ViewHolder(itemLayoutView);

        return viewHolder;
    }



    @Override
    public void onBindViewHolder(final ArticleListAdapter.ViewHolder holder, final int position) {

        final ArticleResponseModel data = dataList.get(position);
        if (data != null) {
            holder.articleTitle.setText(data.getTitle());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(data.getUrl());

                }
            });

        }
    }


    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        ArrayList<ArticleResponseModel> filteredList = new ArrayList<>();
        filteredList.addAll(dataList);

        dataList.clear();

        {
            for (ArticleResponseModel wp : filteredList) {
                if (wp.getTitle().toLowerCase(Locale.getDefault()).contains(charText)) {
                    dataList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (dataList != null)
            return dataList.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView  articleTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            articleTitle = (TextView) itemView.findViewById(R.id.title);
articleTitle.setSelected(true);
        }
    }

    public interface ItemClickListener {
        void onItemClicked(String sourceName);
    }
}

