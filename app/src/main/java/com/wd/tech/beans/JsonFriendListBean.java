package com.wd.tech.beans;

import java.util.List;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:好友列表</p>
 *
 * @author Xaoyv
 * date 11/14/2020 8:44 AM
 */
public class JsonFriendListBean {

    /**
     * result : [{"black":1,"currentNumber":1,"customize":1,"friendInfoList":[{"friendUid":1721,"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","nickName":"GGBond","remarkName":"GGBond","userName":"BKl3Yu18338061345","vipFlag":2}],"groupId":3776,"groupName":"我的好友"},{"black":2,"currentNumber":0,"customize":1,"friendInfoList":[],"groupId":3777,"groupName":"黑名单"}]
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
         * black : 1
         * currentNumber : 1
         * customize : 1
         * friendInfoList : [{"friendUid":1721,"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","nickName":"GGBond","remarkName":"GGBond","userName":"BKl3Yu18338061345","vipFlag":2}]
         * groupId : 3776
         * groupName : 我的好友
         */

        private int black;
        private int currentNumber;
        private int customize;
        private int groupId;
        private String groupName;
        private List<FriendInfoListBean> friendInfoList;

        public int getBlack() {
            return black;
        }

        public void setBlack(int black) {
            this.black = black;
        }

        public int getCurrentNumber() {
            return currentNumber;
        }

        public void setCurrentNumber(int currentNumber) {
            this.currentNumber = currentNumber;
        }

        public int getCustomize() {
            return customize;
        }

        public void setCustomize(int customize) {
            this.customize = customize;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public List<FriendInfoListBean> getFriendInfoList() {
            return friendInfoList;
        }

        public void setFriendInfoList(List<FriendInfoListBean> friendInfoList) {
            this.friendInfoList = friendInfoList;
        }

        public static class FriendInfoListBean {
            /**
             * friendUid : 1721
             * headPic : http://mobile.bwstudent.com/images/tech/default/tech.jpg
             * nickName : GGBond
             * remarkName : GGBond
             * userName : BKl3Yu18338061345
             * vipFlag : 2
             */

            private int friendUid;
            private String headPic;
            private String nickName;
            private String remarkName;
            private String userName;
            private int vipFlag;

            public int getFriendUid() {
                return friendUid;
            }

            public void setFriendUid(int friendUid) {
                this.friendUid = friendUid;
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

            public String getRemarkName() {
                return remarkName;
            }

            public void setRemarkName(String remarkName) {
                this.remarkName = remarkName;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public int getVipFlag() {
                return vipFlag;
            }

            public void setVipFlag(int vipFlag) {
                this.vipFlag = vipFlag;
            }
        }
    }
}
