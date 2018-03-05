package com.example.lenovo.doubanapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {
    private TabLayout tabs;
    private ViewPager viewPager;
    private List<String> mTitles=new ArrayList<String>();
    private List<Fragment>mfragments=new ArrayList<Fragment>();

    private BookFragment bookFragment;
    private MovieFragment movieFragment;
    private MusicFragment musicFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        tabs=(TabLayout)findViewById(R.id.search_tab);
        viewPager=(ViewPager)findViewById(R.id.view_pager);
        tabs.setBackgroundColor(getResources().getColor(R.color.doubantabcolor));
        tabs.setTabTextColors(Color.WHITE,Color.WHITE);
        initView();
        TabAdapter tabAdapter=new TabAdapter(getSupportFragmentManager(),mTitles,mfragments);
        viewPager.setAdapter(tabAdapter);
        tabs.setupWithViewPager(viewPager);
    }
    private void initView(){
        bookFragment=new BookFragment();
        movieFragment=new MovieFragment();
        musicFragment=new MusicFragment();
        mTitles.add("图书");
        mTitles.add("电影");
        mTitles.add("音乐");
        //加装名字
        mfragments.add(bookFragment);
        mfragments.add(movieFragment);
        mfragments.add(musicFragment);
        //加装fragment
    }
}