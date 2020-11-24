package com.wd.tech.beans;

import java.util.List;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:资讯用户评论列表</p>
 *
 * @author Xaoyv
 * date 11/23/2020 6:15 PM
 */
public class JsonDetailsCommentsBean {

    /**
     * result : [{"commentTime":1593508366000,"content":"的广告","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-09-02/20200902143343.jpg","id":2960,"infoId":1,"nickName":"冬天积雪","userId":1321},{"commentTime":1593508235000,"content":"的广告","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-09-02/20200902143343.jpg","id":2959,"infoId":1,"nickName":"冬天积雪","userId":1321},{"commentTime":1593508228000,"content":"的广告","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-09-02/20200902143343.jpg","id":2958,"infoId":1,"nickName":"冬天积雪","userId":1321},{"commentTime":1587729678000,"content":"秀","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":2808,"infoId":1,"nickName":"小小科技","userId":1384},{"commentTime":1587630219000,"content":"6465664","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-22/20200422003552.jpg","id":2796,"infoId":1,"nickName":"花花公子","userId":1402},{"commentTime":1587437386000,"content":"不错","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":2792,"infoId":1,"nickName":"袁晨冉1","userId":1401},{"commentTime":1587129302000,"content":"加油","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-27/20200427134129.png","id":2788,"infoId":1,"nickName":"幻影月缺醉几何","userId":1383},{"commentTime":1580561980000,"content":"非常好","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":2684,"infoId":1,"nickName":"{{nickName}}","userId":1193},{"commentTime":1580561946000,"content":"非常好","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":2683,"infoId":1,"nickName":"{{nickName}}","userId":1193},{"commentTime":1580561933000,"content":"非常好","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":2682,"infoId":1,"nickName":"{{nickName}}","userId":1193},{"commentTime":1580561923000,"content":"非常好","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":2681,"infoId":1,"nickName":"{{nickName}}","userId":1193},{"commentTime":1580561910000,"content":"非常好","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":2680,"infoId":1,"nickName":"{{nickName}}","userId":1193},{"commentTime":1580561350000,"content":"非常好","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":2679,"infoId":1,"nickName":"{{nickName}}","userId":1193},{"commentTime":1560271993000,"content":"中国北京市东城区锡拉胡同6号","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":2588,"infoId":1,"nickName":"12","userId":758},{"commentTime":1560271886000,"content":"中国北京市东城区锡拉胡同6号","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":2587,"infoId":1,"nickName":"12","userId":758}]
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
