package com.example.lenovo.doubanapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by lenovo on 2017/8/11.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>{
    private SearchMovie mMoviesList;
    private Context mContext;
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    public interface OnItemLongClickLisener{
        void onItemLongClick(View view,int position);
    }
    private MoviesAdapter.OnItemClickListener mOnItemClickListener;
    private MoviesAdapter.OnItemLongClickLisener mOnItemLongClickLisener;
    public void setOnItemClickListener(MoviesAdapter.OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener=mOnItemClickListener;
    }
    public void setOnItemLongClickLisener(MoviesAdapter.OnItemLongClickLisener mOnItemLongClickLisener){
        this.mOnItemLongClickLisener=mOnItemLongClickLisener;
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        View movieview;
        ImageView MovieImage;
        TextView MovieTitle;
        public ViewHolder(View view){
            super(view);
            movieview=view;
            MovieImage=(ImageView)view.findViewById(R.id.searchitem_image);
            MovieTitle=(TextView)view.findViewById(R.id.searchitem_title);
        }
    }
    public MoviesAdapter(Context context,SearchMovie MoviesList){
        mMoviesList=MoviesList;
        mContext=context;
    }


    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item,parent,false);
        final MoviesAdapter.ViewHolder holder=new MoviesAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MoviesAdapter.ViewHolder holder, int position) {
        SearchMovie.Subject movies=mMoviesList.getSubjects().get(position);
        Picasso.with(mContext).load(movies.getImages().getLarge()).error(R.drawable.douban1).into(holder.MovieImage);
        holder.MovieTitle.setText(movies.getTitle());
        if(mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position=holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }
        holder.MovieTitle.setText(movies.getTitle());
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
        if(mMoviesList.getCount()<=mMoviesList.getTotal()){
            return mMoviesList.getCount();
        }
        else{
            return mMoviesList.getTotal();
        }
    }
}
