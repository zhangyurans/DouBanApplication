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

public class MusicsAdapter extends RecyclerView.Adapter<MusicsAdapter.ViewHolder>{
    private SearchMusic mMusicsList;
    private Context mContext;
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    public interface OnItemLongClickLisener{
        void onItemLongClick(View view,int position);
    }
    private MusicsAdapter.OnItemClickListener mOnItemClickListener;
    private MusicsAdapter.OnItemLongClickLisener mOnItemLongClickLisener;
    public void setOnItemClickListener(MusicsAdapter.OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener=mOnItemClickListener;
    }
    public void setOnItemLongClickLisener(MusicsAdapter.OnItemLongClickLisener mOnItemLongClickLisener){
        this.mOnItemLongClickLisener=mOnItemLongClickLisener;
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        View musicview;
        ImageView MusicImage;
        TextView MusicTitle;
        public ViewHolder(View view){
            super(view);
            musicview=view;
            MusicImage=(ImageView)view.findViewById(R.id.searchitem_image);
            MusicTitle=(TextView)view.findViewById(R.id.searchitem_title);
        }
    }
    public MusicsAdapter(Context context,SearchMusic MusicsList){
        mMusicsList=MusicsList;
        mContext=context;
    }


    @Override
    public MusicsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item,parent,false);
        final MusicsAdapter.ViewHolder holder=new MusicsAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MusicsAdapter.ViewHolder holder, int position) {
        SearchMusic.Music musics=mMusicsList.getMusics().get(position);
        holder.MusicTitle.setText(musics.getTitle());
        Picasso.with(mContext).load(musics.getImage()).error(R.drawable.douban1).into(holder.MusicImage);
        if(mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position=holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }
        holder.MusicTitle.setText(musics.getTitle());
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
        if(mMusicsList.getCount()<=mMusicsList.getTotal()){
            return mMusicsList.getCount();
        }
        else{
            return mMusicsList.getTotal();
        }
    }
}
