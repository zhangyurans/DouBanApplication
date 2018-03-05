package com.example.lenovo.doubanapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class MusicDetailActivity extends BaseActivity {
    ScrollView scrollView;
    TextView detailText;
    TextView titleText;
    TextView idText;
    TextView languageText;
    TextView countryText;
    ImageView image;
    MusicDetail Detail=new MusicDetail();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        scrollView=(ScrollView)findViewById(R.id.scroll_View);
        detailText=(TextView)findViewById(R.id.detail_text);
        titleText=(TextView)findViewById(R.id.title_text);
        idText=(TextView)findViewById(R.id.id_text);
        languageText=(TextView)findViewById(R.id.language_text);
        countryText=(TextView)findViewById(R.id.country_text);
        image=(ImageView)findViewById(R.id.image_view);
        scrollView.setBackgroundColor(getResources().getColor(R.color.doubanmusicbackground));
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
                        titleText.setText(Detail.getTitle());
                        languageText.setVisibility(View.GONE);
                        countryText.setVisibility(View.GONE);
                        Picasso.with(MusicDetailActivity.this).load(Detail.getImage()).error(R.drawable.douban1).into(image);
                        if(TextUtils.isEmpty(Detail.getSummary())){
                            detailText.setText("简介：暂无介绍");
                        }
                        else{
                            detailText.setText("简介："+Detail.getSummary());
                        }
                        idText.setText("音乐id："+musicidData);
                        Log.d("BookDetailActivity","标题是"+Detail.getTitle());
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
