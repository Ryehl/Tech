package com.wd.tech.beans;

import java.util.List;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:聊天历史记录</p>
 *
 * @author Xaoyv
 * date 11/27/2020 9:40 AM
 */
public class JsonChatHisBean {

    /**
     * result : [{"chatTime":1605837788000,"direction":2,"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","userId":1734},{"chatTime":1605581803000,"direction":2,"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","userId":1734},{"chatTime":1605581432000,"direction":2,"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","userId":1734},{"chatTime":1605520962000,"direction":2,"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","userId":1734},{"chatTime":1605520913000,"direction":2,"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","userId":1734},{"chatTime":1605520693000,"direction":2,"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","userId":1734},{"chatTime":1605520687000,"direction":2,"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","userId":1734},{"chatTime":1605520551000,"direction":2,"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","userId":1734},{"chatTime":1605520548000,"direction":2,"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","userId":1734},{"chatTime":1605520543000,"direction":2,"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","userId":1734}]
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
         * chatTime : 1605837788000
         * direction : 2
         * headPic : http://mobile.bwstudent.com/images/tech/default/tech.jpg
         * userId : 1734
         */

        private long chatTime;
        private int direction;
        private String headPic;
        private int userId;

        public long getChatTime() {
            return chatTime;
        }

        public void setChatTime(long chatTime) {
            this.chatTime = chatTime;
        }

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
