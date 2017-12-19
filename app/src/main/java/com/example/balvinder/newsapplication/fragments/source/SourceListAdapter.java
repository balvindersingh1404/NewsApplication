package com.example.balvinder.newsapplication.fragments.source;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.balvinder.newsapplication.R;
import com.example.balvinder.newsapplication.network.model.SourceResponseModel;

import java.util.List;

/**
 * Created by balvinder on 18/12/17.
 */

public class SourceListAdapter extends RecyclerView.Adapter<SourceListAdapter.ViewHolder> {

    Context context;
    List<SourceResponseModel> dataList;
    RecyclerView recyclerView;
    private ItemClickListener listener;


    public SourceListAdapter(Context context, RecyclerView recyclerView, List<SourceResponseModel> dataList, ItemClickListener itemClickListener) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.dataList = dataList;
        this.listener = itemClickListener;
    }

    @Override
    public SourceListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item_layout, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SourceListAdapter.ViewHolder holder, final int position) {

        final SourceResponseModel data = dataList.get(position);
        if (data != null) {
            holder.newsSource.setText(data.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    listener.onItemClicked(data.getName());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (dataList != null)
            return dataList.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView newsSource;

        public ViewHolder(View itemView) {
            super(itemView);
            newsSource = (TextView) itemView.findViewById(R.id.sourceName);

        }
    }

    public interface ItemClickListener {
        void onItemClicked(String sourceName);
    }
}

