package com.wd.tech.beans;

import java.util.List;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:好友通知</p>
 *
 * @author Xaoyv
 * date 12/8/2020 8:34 AM
 */
public class JsonFriendReqNotBean {

    /**
     * result : [{"fromHeadPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-11-17/20201117081319.png","fromNickName":"冬天的雪","fromUid":1701,"noticeId":1447,"noticeTime":1606567383000,"receiveUid":1730,"remark":"","status":2},{"fromHeadPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-11-16/20201116190842.png","fromNickName":"年轻人","fromUid":1691,"noticeId":1432,"noticeTime":1606396076000,"receiveUid":1730,"remark":"","status":2},{"fromHeadPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-12-07/20201207095212.jpg","fromNickName":"你是","fromUid":1694,"noticeId":1304,"noticeTime":1605513116000,"receiveUid":1730,"remark":"55","status":2},{"fromHeadPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","fromNickName":"爷傲灬奈我何","fromUid":1721,"noticeId":1297,"noticeTime":1605097468000,"receiveUid":1730,"remark":"啊啊啊啊","status":2}]
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
         * fromHeadPic : http://mobile.bwstudent.com/images/tech/head_pic/2020-11-17/20201117081319.png
         * fromNickName : 冬天的雪
         * fromUid : 1701
         * noticeId : 1447
         * noticeTime : 1606567383000
         * receiveUid : 1730
         * remark :
         * status : 2
         */

        private String fromHeadPic;
        private String fromNickName;
        private int fromUid;
        private int noticeId;
        private long noticeTime;
        private int receiveUid;
        private String remark;
        private int status;

        public String getFromHeadPic() {
            return fromHeadPic;
        }

        public void setFromHeadPic(String fromHeadPic) {
            this.fromHeadPic = fromHeadPic;
        }

        public String getFromNickName() {
            return fromNickName;
        }

        public void setFromNickName(String fromNickName) {
            this.fromNickName = fromNickName;
        }

        public int getFromUid() {
            return fromUid;
        }

        public void setFromUid(int fromUid) {
            this.fromUid = fromUid;
        }

        public int getNoticeId() {
            return noticeId;
        }

        public void setNoticeId(int noticeId) {
            this.noticeId = noticeId;
        }

        public long getNoticeTime() {
            return noticeTime;
        }

        public void setNoticeTime(long noticeTime) {
            this.noticeTime = noticeTime;
        }

        public int getReceiveUid() {
            return receiveUid;
        }

        public void setReceiveUid(int receiveUid) {
            this.receiveUid = receiveUid;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
