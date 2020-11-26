package com.wd.tech.beans;

import java.util.List;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:好友（非好友也可以）信息</p>
 *
 * @author Xaoyv
 * date 11/25/2020 9:14 AM
 */
public class JsonFriendInfoBean {

    /**
     * result : {"birthday":946656000000,"email":"abc123@qq.com","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","integral":99999,"myGroupList":[{"blackFlag":0,"groupId":1508,"groupImage":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","groupName":"groupTest","hxGroupId":"46170524","role":3},{"blackFlag":0,"groupId":1509,"groupImage":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","groupName":"groupTest2","hxGroupId":"46170526","role":3}],"nickName":"xygg","phone":"13123123123","sex":1,"signature":"喜欢就买，不行就分，多喝热水，重启试试","userId":1734,"userName":"DXTqct13123123123","whetherFaceId":2,"whetherVip":2}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

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

    public static class ResultBean {
        /**
         * birthday : 946656000000
         * email : abc123@qq.com
         * headPic : http://mobile.bwstudent.com/images/tech/default/tech.jpg
         * integral : 99999
         * myGroupList : [{"blackFlag":0,"groupId":1508,"groupImage":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","groupName":"groupTest","hxGroupId":"46170524","role":3},{"blackFlag":0,"groupId":1509,"groupImage":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","groupName":"groupTest2","hxGroupId":"46170526","role":3}]
         * nickName : xygg
         * phone : 13123123123
         * sex : 1
         * signature : 喜欢就买，不行就分，多喝热水，重启试试
         * userId : 1734
         * userName : DXTqct13123123123
         * whetherFaceId : 2
         * whetherVip : 2
         */

        private long birthday;
        private String email;
        private String headPic;
        private int integral;
        private String nickName;
        private String phone;
        private int sex;
        private String signature;
        private int userId;
        private String userName;
        private int whetherFaceId;
        private int whetherVip;
        private List<MyGroupListBean> myGroupList;

        public long getBirthday() {
            return birthday;
        }

        public void setBirthday(long birthday) {
            this.birthday = birthday;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getWhetherFaceId() {
            return whetherFaceId;
        }

        public void setWhetherFaceId(int whetherFaceId) {
            this.whetherFaceId = whetherFaceId;
        }

        public int getWhetherVip() {
            return whetherVip;
        }

        public void setWhetherVip(int whetherVip) {
            this.whetherVip = whetherVip;
        }

        public List<MyGroupListBean> getMyGroupList() {
            return myGroupList;
        }

        public void setMyGroupList(List<MyGroupListBean> myGroupList) {
            this.myGroupList = myGroupList;
        }

        public static class MyGroupListBean {
            /**
             * blackFlag : 0
             * groupId : 1508
             * groupImage : http://mobile.bwstudent.com/images/tech/default/tech.jpg
             * groupName : groupTest
             * hxGroupId : 46170524
             * role : 3
             */

            private int blackFlag;
            private int groupId;
            private String groupImage;
            private String groupName;
            private String hxGroupId;
            private int role;

            public int getBlackFlag() {
                return blackFlag;
            }

            public void setBlackFlag(int blackFlag) {
                this.blackFlag = blackFlag;
            }

            public int getGroupId() {
                return groupId;
            }

            public void setGroupId(int groupId) {
                this.groupId = groupId;
            }

            public String getGroupImage() {
                return groupImage;
            }

            public void setGroupImage(String groupImage) {
                this.groupImage = groupImage;
            }

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }

            public String getHxGroupId() {
                return hxGroupId;
            }

            public void setHxGroupId(String hxGroupId) {
                this.hxGroupId = hxGroupId;
            }

            public int getRole() {
                return role;
            }

            public void setRole(int role) {
                this.role = role;
            }
        }
    }
}
