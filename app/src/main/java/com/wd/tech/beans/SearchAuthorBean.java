package com.wd.tech.beans;

import java.util.List;

public class SearchAuthorBean {

    /**
     * result : [{"id":35,"releaseTime":1539396095000,"source":"砍柴网","title":"VR/AR将我们带到了令人惊叹的新地方"},{"id":34,"releaseTime":1539395910000,"source":"砍柴网","title":"到2020年全球1/3的消费者将使用VR"},{"id":21,"releaseTime":1539247754000,"source":"砍柴网 ","title":"加拿大开设了首个VR验光实验室"},{"id":20,"releaseTime":1539246697000,"source":"砍柴网 ","title":"VR/AR将我们带到了令人惊叹的新地方"},{"id":19,"releaseTime":1539246375000,"source":"砍柴网 ","title":"苹果AR挡风玻璃系统细节：全新UI交互界面，支持FaceTime通话"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 35
         * releaseTime : 1539396095000
         * source : 砍柴网
         * title : VR/AR将我们带到了令人惊叹的新地方
         */

        private int id;
        private long releaseTime;
        private String source;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
