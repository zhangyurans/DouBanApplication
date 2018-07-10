package com.example.lenovo.doubanapplication.detailactivity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.example.lenovo.doubanapplication.BaseActivity;
import com.example.lenovo.doubanapplication.HttpUtil;
import com.example.lenovo.doubanapplication.LogUtil;
import com.example.lenovo.doubanapplication.detailclass.MusicDetail;
import com.example.lenovo.doubanapplication.R;
import com.example.lenovo.doubanapplication.databinding.ActivityDetailBinding;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class MusicDetailActivity extends BaseActivity {
    private ActivityDetailBinding mbinding;
    MusicDetail Detail=new MusicDetail();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding= DataBindingUtil.setContentView(this,R.layout.activity_detail);
        mbinding.scrollView.setBackgroundColor(getResources().getColor(R.color.doubanmusicbackground));
        Intent intent1=getIntent();
        final String musicidData=intent1.getStringExtra("musicid_data");
        HttpUtil.sendOKHttpRequest("https://api.douban.com/v2/music/"+musicidData,new okhttp3.Callback(){
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData=response.body().string();
                Log.d("BookDetailActivity",responseData);
                Gson gson=new Gson();
                Detail=gson.fromJson(responseData,MusicDetail.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mbinding.titleText.setText(Detail.getTitle());
                        mbinding.languageText.setVisibility(View.GONE);
                        mbinding.countryText.setVisibility(View.GONE);
                        Picasso.get().load(Detail.getImage()).error(R.drawable.douban1).into(mbinding.imageView);
                        if(TextUtils.isEmpty(Detail.getSummary())){
                            mbinding.detailText.setText("简介：暂无介绍");
                        }
                        else{
                            mbinding.detailText.setText("简介：\n"+Detail.getSummary());
                        }
                        mbinding.idText.setText("音乐id："+musicidData);
                        LogUtil.d("BookDetailActivity","标题是《"+Detail.getTitle()+"》");
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
