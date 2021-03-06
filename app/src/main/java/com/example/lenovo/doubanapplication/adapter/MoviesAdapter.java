package com.example.lenovo.doubanapplication.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.doubanapplication.tool.LogUtil;
import com.example.lenovo.doubanapplication.R;
import com.example.lenovo.doubanapplication.databinding.ItemSearchBinding;
import com.example.lenovo.doubanapplication.search.SearchMovie;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/8/11.
 */

public class MoviesAdapter extends XRecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private List<SearchMovie.Subject> mMoviesList;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickLisener {
        void onItemLongClick(View view, int position);
    }

    private MoviesAdapter.OnItemClickListener mOnItemClickListener;
    private MoviesAdapter.OnItemLongClickLisener mOnItemLongClickLisener;

    public void setOnItemClickListener(MoviesAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickLisener(MoviesAdapter.OnItemLongClickLisener mOnItemLongClickLisener) {
        this.mOnItemLongClickLisener = mOnItemLongClickLisener;
    }

    static class ViewHolder extends XRecyclerView.ViewHolder {
        ItemSearchBinding moviebinding;

        private ViewHolder(ItemSearchBinding mbinding) {
            super(mbinding.getRoot());
            moviebinding = mbinding;
        }
    }

    public MoviesAdapter(List<SearchMovie.Subject> MoviesList) {
        mMoviesList = MoviesList;
    }

    public MoviesAdapter() {
        mMoviesList = new ArrayList<>();
    }


    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemSearchBinding mbinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_search, parent, false);
        final MoviesAdapter.ViewHolder holder = new MoviesAdapter.ViewHolder(mbinding);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MoviesAdapter.ViewHolder holder, final int position) {

        LogUtil.d("CrashHandler", "数组长度：" + String.valueOf(mMoviesList.size()));
        LogUtil.d("CrashHandler", "position：" + String.valueOf(position));

        if (position < mMoviesList.size()) {
            SearchMovie.Subject movies = mMoviesList.get(position);
            Picasso.get().load(movies.getImages().getLarge()).error(R.drawable.douban1).into(holder.moviebinding.itemImage);
            holder.moviebinding.itemTitle.setText(movies.getTitle());
            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //int position = holder.getLayoutPosition();
                        mOnItemClickListener.onItemClick(holder.itemView, position);
                    }
                });
            }
            holder.moviebinding.itemTitle.setText(movies.getTitle());
            if (mOnItemLongClickLisener != null) {
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        //int position = holder.getLayoutPosition();
                        mOnItemLongClickLisener.onItemLongClick(holder.itemView, position);
                        return true;
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return mMoviesList.size();
    }
}
