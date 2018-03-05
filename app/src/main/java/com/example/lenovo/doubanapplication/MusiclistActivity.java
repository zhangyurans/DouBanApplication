package com.example.lenovo.doubanapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class MusiclistActivity extends BaseActivity {
    private SearchMusic MusicList=new SearchMusic();
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        linearLayout=(LinearLayout)findViewById(R.id.list_layout);
        linearLayout.setBackgroundColor(Color.rgb(240,243,239));
        Intent intent=getIntent();
        String data=intent.getStringExtra("extra_data");
        HttpUtil.sendOKHttpRequest("https://api.douban.com/v2/music/search?count=40&q="+data,new okhttp3.Callback(){
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData=response.body().string();
                Gson gson=new Gson();
                MusicList=gson.fromJson(responseData,SearchMusic.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycle_view);
                        LinearLayoutManager layoutManager=new LinearLayoutManager(MusiclistActivity.this);
                        recyclerView.setLayoutManager(layoutManager);
                        MusicsAdapter adapter=new MusicsAdapter(MusiclistActivity.this,MusicList);
                        recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener(new MusicsAdapter.OnItemClickListener(){
                            @Override
                            public void onItemClick(View view, int position) {
                                Toast.makeText(MusiclistActivity.this,"你点击了《"+MusicList.getMusics().get(position).getTitle()+"》",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(MusiclistActivity.this,MusicDetailActivity.class);
                                intent.putExtra("musicid_data",MusicList.getMusics().get(position).getId());
                                startActivity(intent);
                            }
                        });
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
