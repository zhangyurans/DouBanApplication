package com.example.lenovo.doubanapplication.detailclass;

/**
 * Created by lenovo on 2017/8/11.
 */

public class MovieDetail {
    private String title;
    private String summary;
    private String language;
    private String country;
    private Images images;
    public class Images{
        private String small;
        private String medium;
        private String large;
        public String getSmall(){
            return small;
        }
        public String getMedium(){
            return medium;
        }
        public String getLarge(){
            return large;
        }
    }
    public String getTitle(){
        return title;
    }
    public String getSummary(){
        return summary;
    }
    public String getLanguage(){
        return language;
    }
    public String getCountry(){
        return country;
    }
    public Images getImages(){
        return images;
    }
}
