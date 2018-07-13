package com.example.lenovo.doubanapplication;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.example.lenovo.doubanapplication.adapter.TabAdapter;
import com.example.lenovo.doubanapplication.fragment.BookFragment;
import com.example.lenovo.doubanapplication.fragment.MovieFragment;
import com.example.lenovo.doubanapplication.fragment.MusicFragment;
import com.example.lenovo.doubanapplication.databinding.ActivityTabBinding;
import com.example.lenovo.doubanapplication.tool.BaseActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {
    private ActivityTabBinding mbinding;
    private List<String> mTitles = new ArrayList<String>();
    private List<Fragment> mfragments = new ArrayList<Fragment>();

    private BookFragment bookFragment;
    private MovieFragment movieFragment;
    private MusicFragment musicFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_tab);
        mbinding.searchTab.setBackgroundColor(getResources().getColor(R.color.doubantabcolor));
        mbinding.searchTab.setTabTextColors(Color.WHITE, Color.WHITE);
        initView();
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager(), mTitles, mfragments);
        mbinding.viewPager.setAdapter(tabAdapter);
        mbinding.searchTab.setupWithViewPager(mbinding.viewPager);
    }

    private void initView() {
        bookFragment = new BookFragment();
        movieFragment = new MovieFragment();
        musicFragment = new MusicFragment();
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