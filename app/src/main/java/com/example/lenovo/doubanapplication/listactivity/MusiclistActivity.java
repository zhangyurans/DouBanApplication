package com.example.lenovo.doubanapplication.listactivity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.lenovo.doubanapplication.adapter.MusicsAdapter;
import com.example.lenovo.doubanapplication.BaseActivity;
import com.example.lenovo.doubanapplication.detailactivity.MusicDetailActivity;
import com.example.lenovo.doubanapplication.HttpUtil;
import com.example.lenovo.doubanapplication.R;
import com.example.lenovo.doubanapplication.search.SearchMusic;
import com.example.lenovo.doubanapplication.databinding.ActivityListBinding;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class MusiclistActivity extends BaseActivity {
    private SearchMusic MusicList=new SearchMusic();
    private ActivityListBinding mbinding;
    private MusicsAdapter adapter=new MusicsAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding= DataBindingUtil.setContentView(this,R.layout.activity_list);
        mbinding.listLayout.setBackgroundColor(getResources().getColor(R.color.doubanmusicbackground));
        LinearLayoutManager layoutManager=new LinearLayoutManager(MusiclistActivity.this);
        mbinding.recyclerView.setLayoutManager(layoutManager);
        adapter.setMusicsList(MusicList);
        mbinding.recyclerView.setAdapter(adapter);
        Intent intent=getIntent();
        String data=intent.getStringExtra("extra_data");
        HttpUtil.sendOKHttpRequest("https://api.douban.com/v2/music/search?count=100&feilds=id,title,image&q="+data,new okhttp3.Callback(){
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData=response.body().string();
                Gson gson=new Gson();
                MusicList=gson.fromJson(responseData,SearchMusic.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.updateMusiclist(MusicList);
                        if(adapter.getItemCount()==0){
                            mbinding.textNullview.setVisibility(View.VISIBLE);
                            mbinding.recyclerView.setVisibility(View.GONE);
                        }
                        else {
                            adapter.setOnItemClickListener(new MusicsAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    if (position<MusicList.getMusics().size()){
                                        Toast.makeText(MusiclistActivity.this, "你点击了《" + MusicList.getMusics().get(position).getTitle() + "》", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MusiclistActivity.this, MusicDetailActivity.class);
                                        intent.putExtra("musicid_data", MusicList.getMusics().get(position).getId());
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
