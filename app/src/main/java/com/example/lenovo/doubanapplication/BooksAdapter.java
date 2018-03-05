package com.example.lenovo.doubanapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lenovo on 2017/8/8.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {
    private SearchBook mBooksList;
    private Context mContext;
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
        View bookview;
        ImageView BookImage;
        TextView BookTitle;
        public ViewHolder(View view){
            super(view);
            bookview=view;
            BookImage=(ImageView)view.findViewById(R.id.searchitem_image);
            BookTitle=(TextView)view.findViewById(R.id.searchitem_title);
        }
    }
    public BooksAdapter(Context context,SearchBook BooksList){
        mContext=context;
        mBooksList=BooksList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        SearchBook.Books books=mBooksList.getBooks().get(position);
        holder.BookTitle.setText(books.getTitle());
        Picasso.with(mContext).load(books.getImages().getSmall()).error(R.drawable.douban1).into(holder.BookImage);
        if(mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position=holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }
        holder.BookTitle.setText(books.getTitle());
        if(mOnItemLongClickLisener!=null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    int position=holder.getLayoutPosition();
                    mOnItemLongClickLisener.onItemLongClick(holder.itemView,position);
                    return true;
                }
            });
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
