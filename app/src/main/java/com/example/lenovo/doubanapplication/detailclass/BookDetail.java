package com.example.lenovo.doubanapplication.detailclass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/8/9.
 */

public class BookDetail {
    private String title;
    private String summary;
    private Images images;
    private List<String> author;

    public BookDetail() {
        title = "";
        summary = "";
        images = new Images();
        author = new ArrayList<>();
    }

    public class Images {
        private String small;
        private String medium;
        private String large;

        public String getSmall() {
            return small;
        }

        public String getMedium() {
            return medium;
        }

        public String getLarge() {
            return large;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public Images getImages() {
        return images;
    }

    public List<String> getAuthor() {
        return author;
    }
}
