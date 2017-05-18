package com.example.copengxiaolue.personaldemo.model;

import java.util.List;

/**
 * Created by copengxiaolue on 2017/05/17.
 */

public class GankResult {

    private boolean error;
    private List<ResultBean> results;

    public boolean isError() {
        return error;
    }

    public List<ResultBean> getResults() {
        return results;
    }

    public static class ResultBean {
        public String _id;
        public String createdAt;
        public String desc;
        public List<String> images;
        public String publishedAt;
        public String source;
        public String type;
        public String url;
        public boolean used;
        public String who;
    }
}
