package com.bw.tech.beans;

import java.util.List;

public class NoticeBean {

    /**
     * result : [{"content":"Xaoyv同意了您的好友申请","createTime":1605097513000,"id":1282,"receiveUid":1721}]
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
         * content : Xaoyv同意了您的好友申请
         * createTime : 1605097513000
         * id : 1282
         * receiveUid : 1721
         */

        private String content;
        private long createTime;
        private int id;
        private int receiveUid;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getReceiveUid() {
            return receiveUid;
        }

        public void setReceiveUid(int receiveUid) {
            this.receiveUid = receiveUid;
        }
    }
}
