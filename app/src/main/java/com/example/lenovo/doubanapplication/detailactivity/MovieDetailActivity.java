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
import com.example.lenovo.doubanapplication.detailclass.MovieDetail;
import com.example.lenovo.doubanapplication.R;
import com.example.lenovo.doubanapplication.databinding.ActivityDetailBinding;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class MovieDetailActivity extends BaseActivity {
    private ActivityDetailBinding mbinding;
    MovieDetail Detail=new MovieDetail();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding= DataBindingUtil.setContentView(this,R.layout.activity_detail);
        mbinding.scrollView.setBackgroundColor(getResources().getColor(R.color.doubanmoviebackground));
        Intent intent1=getIntent();
        final String movieidData=intent1.getStringExtra("movieid_data");
        HttpUtil.sendOKHttpRequest("https://api.douban.com/v2/movie/subject/"+movieidData,new okhttp3.Callback(){
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData=response.body().string();
                Gson gson=new Gson();
                Detail=gson.fromJson(responseData,MovieDetail.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mbinding.titleText.setText(Detail.getTitle());
                        Picasso.get().load(Detail.getImages().getLarge()).error(R.drawable.douban1).into(mbinding.imageView);
                        if (TextUtils.isEmpty(Detail.getLanguage())){
                            mbinding.languageText.setVisibility(View.GONE);
                        }
                        else{
                            mbinding.languageText.setText("语言："+Detail.getLanguage());
                        }
                        if(TextUtils.isEmpty(Detail.getCountry())){
                            mbinding.countryText.setVisibility(View.GONE);
                        }
                        else {
                            mbinding.countryText.setText("国家："+Detail.getCountry());
                        }
                        if(TextUtils.isEmpty(Detail.getSummary())){
                            mbinding.detailText.setText("简介：暂无介绍");
                        }
                        else{
                            mbinding.detailText.setText("简介：\n"+Detail.getSummary());
                        }
                        mbinding.idText.setText("电影id："+movieidData);
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
