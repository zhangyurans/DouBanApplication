package com.example.lenovo.doubanapplication.listactivity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.lenovo.doubanapplication.LogUtil;
import com.example.lenovo.doubanapplication.adapter.MoviesAdapter;
import com.example.lenovo.doubanapplication.BaseActivity;
import com.example.lenovo.doubanapplication.detailactivity.MovieDetailActivity;
import com.example.lenovo.doubanapplication.HttpUtil;
import com.example.lenovo.doubanapplication.R;
import com.example.lenovo.doubanapplication.search.SearchMovie;
import com.example.lenovo.doubanapplication.databinding.ActivityListBinding;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class MovielistActivity extends BaseActivity {
    private SearchMovie MovieList=new SearchMovie();
    private ActivityListBinding mbinding;
    private MoviesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding= DataBindingUtil.setContentView(this,R.layout.activity_list);
        mbinding.listLayout.setBackgroundColor(getResources().getColor(R.color.doubanmoviebackground));
        LinearLayoutManager layoutManager=new LinearLayoutManager(MovielistActivity.this);
        mbinding.recyclerView.setLayoutManager(layoutManager);
        adapter=new MoviesAdapter(MovieList.getSubjects());
        adapter.setMoviesList(MovieList.getSubjects());
        mbinding.recyclerView.setAdapter(adapter);

        Intent intent=getIntent();
        String data=intent.getStringExtra("extra_data");
        HttpUtil.sendOKHttpRequest("https://api.douban.com/v2/movie/search?feilds=id,title,images&q="+data,new okhttp3.Callback(){
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData=response.body().string();
                Gson gson=new Gson();
                MovieList=gson.fromJson(responseData,SearchMovie.class);
                //LogUtil.d("CrashHandler","count:"+String.valueOf(MovieList.getCount()));
               // LogUtil.d("CrashHandler","total:"+String.valueOf(MovieList.getTotal()));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.updateMovielist(MovieList.getSubjects());

                        LogUtil.d("CrashHandler","ItemCount:"+String.valueOf(adapter.getItemCount()));

                        if(adapter.getItemCount()==0){
                            mbinding.textNullview.setVisibility(View.VISIBLE);
                            mbinding.recyclerView.setVisibility(View.GONE);
                        }
                        else {
                            adapter.setOnItemClickListener(new MoviesAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    if(position<MovieList.getSubjects().size()){
                                        Toast.makeText(MovielistActivity.this, "你点击了《" + MovieList.getSubjects().get(position).getTitle() + "》", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MovielistActivity.this, MovieDetailActivity.class);
                                        intent.putExtra("movieid_data", MovieList.getSubjects().get(position).getId());
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
            }
        });
    }
}
