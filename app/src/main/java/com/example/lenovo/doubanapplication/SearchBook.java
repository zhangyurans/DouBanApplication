package com.example.lenovo.doubanapplication;

import java.util.List;

/**
 * Created by lenovo on 2017/8/8.
 */

public class SearchBook {
    private int count;
    private int start;
    private int total;
    private List<Books> books;
    public class Books{
        private String id;
        private String title;
        private Images images;
        public class Images{
            private String small;
            private String medium;
            private String large;
            public String getSmall(){
                return small;
            };
            public String getMedium(){
                return medium;
            };
            public String getLarge(){
                return large;
            };
        }
        public String getId(){
            return id;
        }
        public String getTitle(){
            return title;
        }
        public Images getImages(){
            return images;
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
    public List<Books>getBooks(){
        return books;
    }
}
