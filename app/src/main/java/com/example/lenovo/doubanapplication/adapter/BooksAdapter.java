package com.example.lenovo.doubanapplication.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.doubanapplication.R;
import com.example.lenovo.doubanapplication.databinding.ItemSearchBinding;
import com.example.lenovo.doubanapplication.search.SearchBook;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/8/8.
 */

public class BooksAdapter extends XRecyclerView.Adapter<BooksAdapter.ViewHolder> {
    private List<SearchBook.Books> mBooksList;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickLisener {
        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickLisener mOnItemLongClickLisener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickLisener(OnItemLongClickLisener mOnItemLongClickLisener) {
        this.mOnItemLongClickLisener = mOnItemLongClickLisener;
    }

    static class ViewHolder extends XRecyclerView.ViewHolder {
        private ItemSearchBinding bookbinding;

        private ViewHolder(ItemSearchBinding mbinding) {
            super(mbinding.getRoot());
            bookbinding = mbinding;
        }
    }

    public BooksAdapter(List<SearchBook.Books> BooksList) {
        mBooksList = BooksList;
    }

    public BooksAdapter() {
        mBooksList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemSearchBinding mbinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_search, parent, false);
        final ViewHolder holder = new ViewHolder(mbinding);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (position < mBooksList.size()) {
            SearchBook.Books books = mBooksList.get(position);
            holder.bookbinding.itemTitle.setText(books.getTitle());
            Picasso.get().load(books.getImages().getSmall()).error(R.drawable.douban1).into(holder.bookbinding.itemImage);
            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //int position = holder.getLayoutPosition();
                        mOnItemClickListener.onItemClick(holder.itemView, position);
                    }
                });
            }
            holder.bookbinding.itemTitle.setText(books.getTitle());
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
        return mBooksList.size();
    }
}
