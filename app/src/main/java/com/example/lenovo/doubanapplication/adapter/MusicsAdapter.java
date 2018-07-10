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
import com.example.lenovo.doubanapplication.search.SearchMusic;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lenovo on 2017/8/11.
 */

public class MusicsAdapter extends RecyclerView.Adapter<MusicsAdapter.ViewHolder>{
    private SearchMusic mMusicsList;
    //private Context mContext;
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
        ItemSearchBinding musicbinding;
        public ViewHolder(ItemSearchBinding mbinding){
            super(mbinding.getRoot());
            musicbinding=mbinding;
        }
    }
    public MusicsAdapter(SearchMusic MusicsList){
        mMusicsList=MusicsList;
    }
    public MusicsAdapter(){
        mMusicsList=new SearchMusic();
    }

    public void setMusicsList(SearchMusic MusicsList) {
        this.mMusicsList = MusicsList;
    }
    public void addMusicItems(List<SearchMusic.Music>music){
        this.mMusicsList.getMusics().addAll(music);
        notifyDataSetChanged();
    }
    public void updateMusiclist(SearchMusic MusicsList){
        this.mMusicsList=MusicsList;
        notifyDataSetChanged();
    }

    @Override
    public MusicsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemSearchBinding mbinding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.item_search,parent,false);
        final MusicsAdapter.ViewHolder holder=new MusicsAdapter.ViewHolder(mbinding);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MusicsAdapter.ViewHolder holder, int position) {
        if (position<mMusicsList.getMusics().size()){
            SearchMusic.Music musics=mMusicsList.getMusics().get(position);
            holder.musicbinding.itemTitle.setText(musics.getTitle());
            Picasso.get().load(musics.getImage()).error(R.drawable.douban1).into(holder.musicbinding.itemImage);
            if(mOnItemClickListener!=null){
                holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position=holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }
        holder.musicbinding.itemTitle.setText(musics.getTitle());
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
