package com.wd.tech.beans;

import java.util.List;

public class CommuntiyBean {
    /**
     * result : [{"comment":4,"communityCommentVoList":[{"content":"xzx","nickName":"说的还是","userId":1718},{"content":"xzxz","nickName":"说的还是","userId":1718},{"content":"，","nickName":"好哥哥","userId":1709}],"content":"ddddddd","file":"http://mobile.bwstudent.com/images/tech/community_pic/2020-11-12/7210320201112192150.png","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-11-12/20201112205211.png","id":2280,"nickName":"说的还是","praise":1,"publishTime":1605180110000,"userId":1718,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":7,"communityCommentVoList":[{"content":"1111111","nickName":"夜·烨","userId":1710},{"content":"1111","nickName":"夜·烨","userId":1710},{"content":"111","nickName":"夜·烨","userId":1710}],"content":"hahahahaha","file":"","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-11-06/20201106193035.png","id":2279,"nickName":"hcc","praise":5,"publishTime":1605138962000,"userId":1702,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":0,"communityCommentVoList":[],"content":"这是我发的帖子","file":"http://mobile.bwstudent.com/images/tech/community_pic/2020-11-11/2569320201111192030.jpg","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":2278,"nickName":"星辰","praise":77,"publishTime":1605093630000,"signature":"qqq","userId":1715,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":0,"communityCommentVoList":[],"content":"2","file":"","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":2277,"nickName":"金","praise":9,"publishTime":1605093100000,"signature":"1","userId":1695,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":2,"communityCommentVoList":[{"content":"？？？","nickName":"好哥哥","userId":1709},{"content":"？？？！！！","nickName":"好哥哥","userId":1709}],"content":"1","file":"","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":2276,"nickName":"金","praise":2,"publishTime":1605071278000,"signature":"1","userId":1695,"whetherFollow":2,"whetherGreat":2,"whetherVip":2}]
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
         * comment : 4
         * communityCommentVoList : [{"content":"xzx","nickName":"说的还是","userId":1718},{"content":"xzxz","nickName":"说的还是","userId":1718},{"content":"，","nickName":"好哥哥","userId":1709}]
         * content : ddddddd
         * file : http://mobile.bwstudent.com/images/tech/community_pic/2020-11-12/7210320201112192150.png
         * headPic : http://mobile.bwstudent.com/images/tech/head_pic/2020-11-12/20201112205211.png
         * id : 2280
         * nickName : 说的还是
         * praise : 1
         * publishTime : 1605180110000
         * userId : 1718
         * whetherFollow : 2
         * whetherGreat : 2
         * whetherVip : 2
         * signature : qqq
         */

        private int comment;
        private String content;
        private String file;
        private String headPic;
        private int id;
        private String nickName;
        private int praise;
        private long publishTime;
        private int userId;
        private int whetherFollow;
        private int whetherGreat;
        private int whetherVip;
        private String signature;
        private List<CommunityCommentVoListBean> communityCommentVoList;

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
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

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getPraise() {
            return praise;
        }

        public void setPraise(int praise) {
            this.praise = praise;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWhetherFollow() {
            return whetherFollow;
        }

        public void setWhetherFollow(int whetherFollow) {
            this.whetherFollow = whetherFollow;
        }

        public int getWhetherGreat() {
            return whetherGreat;
        }

        public void setWhetherGreat(int whetherGreat) {
            this.whetherGreat = whetherGreat;
        }

        public int getWhetherVip() {
            return whetherVip;
        }

        public void setWhetherVip(int whetherVip) {
            this.whetherVip = whetherVip;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public List<CommunityCommentVoListBean> getCommunityCommentVoList() {
            return communityCommentVoList;
        }

        public void setCommunityCommentVoList(List<CommunityCommentVoListBean> communityCommentVoList) {
            this.communityCommentVoList = communityCommentVoList;
        }

        public static class CommunityCommentVoListBean {
            /**
             * content : xzx
             * nickName : 说的还是
             * userId : 1718
             */

            private String content;
            private String nickName;
            private int userId;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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
}
