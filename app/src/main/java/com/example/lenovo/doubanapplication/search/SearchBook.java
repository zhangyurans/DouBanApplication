package com.example.lenovo.doubanapplication.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/8/8.
 */

public class SearchBook {
    private int count;
    private int start;
    private int total;
    private List<Books> books;

    public SearchBook() {
        count = 0;
        start = 0;
        total = 0;
        books = new ArrayList<>();
    }

    public class Books {
        private String id;
        private String title;
        private Images images;

        public class Images {
            private String small;
            private String medium;
            private String large;

            public String getSmall() {
                return small;
            }

            ;

            public String getMedium() {
                return medium;
            }

            ;

            public String getLarge() {
                return large;
            }

            ;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public Images getImages() {
            return images;
        }
    }

    public int getCount() {
        return count;
    }

    public int getStart() {
        return start;
    }

    public int getTotal() {
        return total;
    }

    public void setStart(int mStart) {
        start = mStart;
    }

    public List<Books> getBooks() {
        return books;
    }
}
