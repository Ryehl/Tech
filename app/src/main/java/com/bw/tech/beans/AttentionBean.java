package com.bw.tech.beans;

import java.util.List;

public class AttentionBean {


    /**
     * result : [{"focusTime":1605442973000,"focusUid":1694,"headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-11-10/20201110175110.png","nickName":"胡图图","signature":"222","userId":1721,"whetherMutualFollow":2,"whetherVip":2},{"focusTime":1605314553000,"focusUid":1691,"headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-11-08/20201108222257.png","nickName":"年轻人","userId":1721,"whetherMutualFollow":2,"whetherVip":2}]
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
         * focusTime : 1605442973000
         * focusUid : 1694
         * headPic : http://mobile.bwstudent.com/images/tech/head_pic/2020-11-10/20201110175110.png
         * nickName : 胡图图
         * signature : 222
         * userId : 1721
         * whetherMutualFollow : 2
         * whetherVip : 2
         */

        private long focusTime;
        private int focusUid;
        private String headPic;
        private String nickName;
        private String signature;
        private int userId;
        private int whetherMutualFollow;
        private int whetherVip;

        public long getFocusTime() {
            return focusTime;
        }

        public void setFocusTime(long focusTime) {
            this.focusTime = focusTime;
        }

        public int getFocusUid() {
            return focusUid;
        }

        public void setFocusUid(int focusUid) {
            this.focusUid = focusUid;
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

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWhetherMutualFollow() {
            return whetherMutualFollow;
        }

        public void setWhetherMutualFollow(int whetherMutualFollow) {
            this.whetherMutualFollow = whetherMutualFollow;
        }

        public int getWhetherVip() {
            return whetherVip;
        }

        public void setWhetherVip(int whetherVip) {
            this.whetherVip = whetherVip;
        }
    }
}
