package com.example.lenovo.doubanapplication.listactivity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lenovo.doubanapplication.adapter.MusicsAdapter;
import com.example.lenovo.doubanapplication.tool.BaseActivity;
import com.example.lenovo.doubanapplication.detailactivity.MusicDetailActivity;
import com.example.lenovo.doubanapplication.tool.HttpUtil;
import com.example.lenovo.doubanapplication.R;
import com.example.lenovo.doubanapplication.search.SearchMusic;
import com.example.lenovo.doubanapplication.databinding.ActivityListBinding;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class MusiclistActivity extends BaseActivity {
    private List<SearchMusic.Music> musicList = new ArrayList<>();
    private ActivityListBinding mbinding;
    private MusicsAdapter adapter = new MusicsAdapter(musicList);
    private boolean isLoadable = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_list);
        mbinding.listLayout.setBackgroundColor(getResources().getColor(R.color.doubanmusicbackground));
        LinearLayoutManager layoutManager = new LinearLayoutManager(MusiclistActivity.this);
        mbinding.recyclerView.setLayoutManager(layoutManager);
        mbinding.recyclerView.setAdapter(adapter);


        //接下来设置XRecyclerview特有的相关属性
        mbinding.recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mbinding.recyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        mbinding.recyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        View header = LayoutInflater.from(this).inflate(R.layout.item_header, (ViewGroup)findViewById(android.R.id.content),false);
        mbinding.recyclerView.addHeaderView(header);
        mbinding.recyclerView.getDefaultRefreshHeaderView() // get default refresh header view
                .setRefreshTimeVisible(true);
        mbinding.recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        musicList.clear();
                        init();
                        adapter.notifyDataSetChanged();
                        mbinding.recyclerView.refreshComplete();
                    }
                }, 1000);

            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        init();
                        adapter.notifyDataSetChanged();
                        if (!isLoadable) {
                            Toast.makeText(MusiclistActivity.this, "无法加载更多", Toast.LENGTH_SHORT).show();
                        }
                        mbinding.recyclerView.loadMoreComplete();
                    }
                }, 1000);
            }
        });
        init();
    }

    private void init() {
        Intent intent = getIntent();
        String data = intent.getStringExtra("extra_data");
        HttpUtil.sendOKHttpRequest("https://api.douban.com/v2/music/search?count=100&start=" + String.valueOf(musicList.size()) + "&fields=id,title,image&q=" + data, new okhttp3.Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Gson gson = new Gson();
                SearchMusic searchMusic = gson.fromJson(responseData, SearchMusic.class);
                if (searchMusic.getMusics().size() == 0) {
                    isLoadable = false;
                }
                musicList.addAll(searchMusic.getMusics());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        if (adapter.getItemCount() == 0) {
                            mbinding.textNullview.setVisibility(View.VISIBLE);
                            //mbinding.recyclerView.setVisibility(View.GONE);
                        } else {
                            adapter.setOnItemClickListener(new MusicsAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    if (position < musicList.size()) {
                                        Toast.makeText(MusiclistActivity.this, "你点击了《" + musicList.get(position).getTitle() + "》", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MusiclistActivity.this, MusicDetailActivity.class);
                                        intent.putExtra("musicid_data", musicList.get(position).getId());
                                        startActivity(intent);
                                    }
                                }
                            });
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Toast.makeText(MusiclistActivity.this, "音乐搜索出现错误", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
