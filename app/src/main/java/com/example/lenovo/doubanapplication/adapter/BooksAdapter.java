package com.example.lenovo.doubanapplication.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.doubanapplication.R;
import com.example.lenovo.doubanapplication.databinding.ItemSearchBinding;
import com.example.lenovo.doubanapplication.search.SearchBook;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lenovo on 2017/8/8.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {
    private SearchBook mBooksList;
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
    public interface OnItemLongClickLisener{
        void onItemLongClick(View view,int position);
    }
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickLisener mOnItemLongClickLisener;
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener=mOnItemClickListener;
    }
    public void setOnItemLongClickLisener(OnItemLongClickLisener mOnItemLongClickLisener){
        this.mOnItemLongClickLisener=mOnItemLongClickLisener;
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        private ItemSearchBinding bookbinding;
        public ViewHolder(ItemSearchBinding mbinding){
            super(mbinding.getRoot());
            bookbinding=mbinding;
        }
    }
    public BooksAdapter(SearchBook BooksList){
        mBooksList=BooksList;
    }
    public BooksAdapter(){
        mBooksList=new SearchBook();
    }
    public void setBooksList(SearchBook BooksList){
        this.mBooksList=BooksList;
    }
    public void addBookItems(List<SearchBook.Books>books){
        this.mBooksList.getBooks().addAll(books);
        notifyDataSetChanged();
    }
    public void updateBooklist(SearchBook BooksList){
        this.mBooksList=BooksList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemSearchBinding mbinding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.item_search,parent,false);
        final ViewHolder holder=new ViewHolder(mbinding);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(position<mBooksList.getBooks().size()){
            SearchBook.Books books=mBooksList.getBooks().get(position);
            holder.bookbinding.itemTitle.setText(books.getTitle());
            Picasso.get().load(books.getImages().getSmall()).error(R.drawable.douban1).into(holder.bookbinding.itemImage);
            if(mOnItemClickListener!=null){
                holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position=holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }
        holder.bookbinding.itemTitle.setText(books.getTitle());
            if(mOnItemLongClickLisener!=null){
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int position = holder.getLayoutPosition();
                        mOnItemLongClickLisener.onItemLongClick(holder.itemView, position);
                        return true;
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        if(mBooksList.getCount()<=mBooksList.getTotal()){
            return mBooksList.getCount();
        }
        else{
            return mBooksList.getTotal();
        }
    }
}
