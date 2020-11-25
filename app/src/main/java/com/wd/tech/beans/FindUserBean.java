package com.wd.tech.beans;

import java.util.List;

public class FindUserBean {


    /**
     * result : [{"communityUserPostVoList":[],"communityUserVo":{"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","nickName":"tyu","power":2,"userId":1018,"whetherFollow":2,"whetherMyFriend":2}}]
     * message : 查詢成功
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
         * communityUserPostVoList : []
         * communityUserVo : {"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","nickName":"tyu","power":2,"userId":1018,"whetherFollow":2,"whetherMyFriend":2}
         */

        private CommunityUserVoBean communityUserVo;
        private List<?> communityUserPostVoList;

        public CommunityUserVoBean getCommunityUserVo() {
            return communityUserVo;
        }

        public void setCommunityUserVo(CommunityUserVoBean communityUserVo) {
            this.communityUserVo = communityUserVo;
        }

        public List<?> getCommunityUserPostVoList() {
            return communityUserPostVoList;
        }

        public void setCommunityUserPostVoList(List<?> communityUserPostVoList) {
            this.communityUserPostVoList = communityUserPostVoList;
        }

        public static class CommunityUserVoBean {
            /**
             * headPic : http://mobile.bwstudent.com/images/tech/default/tech.jpg
             * nickName : tyu
             * power : 2
             * userId : 1018
             * whetherFollow : 2
             * whetherMyFriend : 2
             */

            private String headPic;
            private String nickName;
            private int power;
            private int userId;
            private int whetherFollow;
            private int whetherMyFriend;

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

            public int getPower() {
                return power;
            }

            public void setPower(int power) {
                this.power = power;
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

            public int getWhetherMyFriend() {
                return whetherMyFriend;
            }

            public void setWhetherMyFriend(int whetherMyFriend) {
                this.whetherMyFriend = whetherMyFriend;
            }
        }
    }
}
