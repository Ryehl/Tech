package com.wd.tech.beans;

import java.util.List;

public class DetailsCommentBean {

    /**
     * result : [{"commentTime":1593508366000,"content":"的广告","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-09-02/20200902143343.jpg","id":2960,"infoId":1,"nickName":"冬天积雪","userId":1321},{"commentTime":1593508235000,"content":"的广告","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-09-02/20200902143343.jpg","id":2959,"infoId":1,"nickName":"冬天积雪","userId":1321},{"commentTime":1593508228000,"content":"的广告","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-09-02/20200902143343.jpg","id":2958,"infoId":1,"nickName":"冬天积雪","userId":1321},{"commentTime":1587729678000,"content":"秀","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":2808,"infoId":1,"nickName":"小小科技","userId":1384},{"commentTime":1587630219000,"content":"6465664","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-22/20200422003552.jpg","id":2796,"infoId":1,"nickName":"花花公子","userId":1402}]
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
         * commentTime : 1593508366000
         * content : 的广告
         * headPic : http://mobile.bwstudent.com/images/tech/head_pic/2020-09-02/20200902143343.jpg
         * id : 2960
         * infoId : 1
         * nickName : 冬天积雪
         * userId : 1321
         */

        private long commentTime;
        private String content;
        private String headPic;
        private int id;
        private int infoId;
        private String nickName;
        private int userId;

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getInfoId() {
            return infoId;
        }

        public void setInfoId(int infoId) {
            this.infoId = infoId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
