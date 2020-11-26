package com.wd.tech.beans;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:群聊信息</p>
 *
 * @author Xaoyv
 * date 11/25/2020 7:00 PM
 */
public class JsonGroupInfoBean {

    /**
     * result : {"currentCount":1,"description":"xygg创建的群","groupId":1508,"groupImage":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","groupName":"groupTest","hxGroupId":"46170524","maxCount":10,"ownerUid":1734}
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
         * currentCount : 1
         * description : xygg创建的群
         * groupId : 1508
         * groupImage : http://mobile.bwstudent.com/images/tech/default/tech.jpg
         * groupName : groupTest
         * hxGroupId : 46170524
         * maxCount : 10
         * ownerUid : 1734
         */

        private int currentCount;
        private String description;
        private int groupId;
        private String groupImage;
        private String groupName;
        private String hxGroupId;
        private int maxCount;
        private int ownerUid;

        public int getCurrentCount() {
            return currentCount;
        }

        public void setCurrentCount(int currentCount) {
            this.currentCount = currentCount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public int getMaxCount() {
            return maxCount;
        }

        public void setMaxCount(int maxCount) {
            this.maxCount = maxCount;
        }

        public int getOwnerUid() {
            return ownerUid;
        }

        public void setOwnerUid(int ownerUid) {
            this.ownerUid = ownerUid;
        }
    }
}
