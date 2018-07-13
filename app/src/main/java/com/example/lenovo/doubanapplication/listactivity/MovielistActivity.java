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

import com.example.lenovo.doubanapplication.tool.CrashHandler;
import com.example.lenovo.doubanapplication.tool.LogUtil;
import com.example.lenovo.doubanapplication.adapter.MoviesAdapter;
import com.example.lenovo.doubanapplication.tool.BaseActivity;
import com.example.lenovo.doubanapplication.detailactivity.MovieDetailActivity;
import com.example.lenovo.doubanapplication.tool.HttpUtil;
import com.example.lenovo.doubanapplication.R;
import com.example.lenovo.doubanapplication.search.SearchMovie;
import com.example.lenovo.doubanapplication.databinding.ActivityListBinding;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class MovielistActivity extends BaseActivity {
    private List<SearchMovie.Subject> movieList = new ArrayList<>();
    private ActivityListBinding mbinding;
    private MoviesAdapter adapter = new MoviesAdapter(movieList);
    private boolean isLoadable = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_list);
        mbinding.listLayout.setBackgroundColor(getResources().getColor(R.color.doubanmoviebackground));
        LinearLayoutManager layoutManager = new LinearLayoutManager(MovielistActivity.this);
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
                        movieList.clear();
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
                            Toast.makeText(MovielistActivity.this, "无法加载更多", Toast.LENGTH_SHORT).show();
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
        HttpUtil.sendOKHttpRequest("https://api.douban.com/v2/movie/search?fields=id,title,images&start=" + String.valueOf(movieList.size()) + "&q=" + data, new okhttp3.Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Gson gson = new Gson();
                SearchMovie searchMovie = gson.fromJson(responseData, SearchMovie.class);
                if (searchMovie.getSubjects().size() == 0) {
                    isLoadable = false;
                }
                movieList.addAll(searchMovie.getSubjects());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        if (adapter.getItemCount() == 0) {
                            mbinding.textNullview.setVisibility(View.VISIBLE);
                            //mbinding.recyclerView.setVisibility(View.GONE);
                        } else {
                            adapter.setOnItemClickListener(new MoviesAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    if (position < movieList.size()) {
                                        Toast.makeText(MovielistActivity.this, "你点击了《" + movieList.get(position).getTitle() + "》", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MovielistActivity.this, MovieDetailActivity.class);
                                        intent.putExtra("movieid_data", movieList.get(position).getId());
                                        startActivity(intent);
                                    } else {
                                        LogUtil.e("CrashHandler", "List's position has a default");
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
                Toast.makeText(MovielistActivity.this, "电影搜索出现错误", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
