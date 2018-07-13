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
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/8/11.
 */

public class MusicsAdapter extends XRecyclerView.Adapter<MusicsAdapter.ViewHolder> {
    private List<SearchMusic.Music> mMusicsList;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickLisener {
        void onItemLongClick(View view, int position);
    }

    private MusicsAdapter.OnItemClickListener mOnItemClickListener;
    private MusicsAdapter.OnItemLongClickLisener mOnItemLongClickLisener;

    public void setOnItemClickListener(MusicsAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickLisener(MusicsAdapter.OnItemLongClickLisener mOnItemLongClickLisener) {
        this.mOnItemLongClickLisener = mOnItemLongClickLisener;
    }

    static class ViewHolder extends XRecyclerView.ViewHolder {
        ItemSearchBinding musicbinding;

        private ViewHolder(ItemSearchBinding mbinding) {
            super(mbinding.getRoot());
            musicbinding = mbinding;
        }
    }

    public MusicsAdapter(List<SearchMusic.Music> MusicsList) {
        mMusicsList = MusicsList;
    }

    public MusicsAdapter() {
        mMusicsList = new ArrayList<>();
    }


    @Override
    public MusicsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemSearchBinding mbinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_search, parent, false);
        final MusicsAdapter.ViewHolder holder = new MusicsAdapter.ViewHolder(mbinding);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MusicsAdapter.ViewHolder holder, final int position) {
        if (position < mMusicsList.size()) {
            SearchMusic.Music musics = mMusicsList.get(position);
            holder.musicbinding.itemTitle.setText(musics.getTitle());
            Picasso.get().load(musics.getImage()).error(R.drawable.douban1).into(holder.musicbinding.itemImage);
            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //int position = holder.getAdapterPosition();
                        mOnItemClickListener.onItemClick(holder.itemView, position);
                    }
                });
            }
            holder.musicbinding.itemTitle.setText(musics.getTitle());
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
        return mMusicsList.size();
    }
}
