package com.wd.tech.beans;

import java.util.List;

public class CommunityCommentListBean {


    /**
     * result : [{"commentTime":1588575774000,"content":"咋就一个呢","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-27/20200427134129.png","nickName":"幻影月缺醉几何","userId":1383},{"commentTime":1588575182000,"content":"试一试","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-27/20200427134129.png","nickName":"幻影月缺醉几何","userId":1383},{"commentTime":1588574869000,"content":"不知道行不行","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-27/20200427134129.png","nickName":"幻影月缺醉几何","userId":1383},{"commentTime":1588075411000,"content":"1,","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-27/20200427134129.png","nickName":"幻影月缺醉几何","userId":1383},{"commentTime":1588075242000,"content":"1,","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-27/20200427134129.png","nickName":"幻影月缺醉几何","userId":1383}]
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
         * commentTime : 1588575774000
         * content : 咋就一个呢
         * headPic : http://mobile.bwstudent.com/images/tech/head_pic/2020-04-27/20200427134129.png
         * nickName : 幻影月缺醉几何
         * userId : 1383
         */

        private long commentTime;
        private String content;
        private String headPic;
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
