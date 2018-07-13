package com.example.lenovo.doubanapplication.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/8/11.
 */

public class SearchMusic {
    private int count;
    private int start;
    private int total;
    private List<Music>musics;
    public SearchMusic(){
        count=0;
        start=0;
        total=0;
        musics=new ArrayList<>();
    }
    public class Music{
        private String id;
        private String title;
        private String image;
        public String getId(){
            return id;
        }
        public String getTitle(){
            return title;
        }
        public String getImage(){
            return image;
        }
    }
    public int getCount(){
        return count;
    }
    public int getStart(){
        return start;
    }
    public int getTotal(){
        return total;
    }
    public void setStart(int mStart){
        start=mStart;
    }
    public List<Music>getMusics(){
        return musics;
    }
}
