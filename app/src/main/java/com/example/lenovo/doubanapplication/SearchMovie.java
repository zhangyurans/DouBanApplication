package com.example.lenovo.doubanapplication;

import java.util.List;

/**
 * Created by lenovo on 2017/8/11.
 */
public class SearchMovie {
    private int count;
    private int start;
    private int total;
    private List<Subject>subjects;
    public class Subject{
        private String title;
        private String id;
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
        public String getTitle(){
            return title;
        }
        public String getId(){
            return id;
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
    public List<Subject>getSubjects(){
        return subjects;
    }
}
