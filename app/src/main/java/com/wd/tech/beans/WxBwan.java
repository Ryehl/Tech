package com.wd.tech.beans;

/**
 * @Description: java类作用描述
 * @Author: 李凌旭
 * @CreateDate: 2020/11/26 19:56
 */
public class WxBwan {
    /**
     * result : {"headPic":"https://thirdwx.qlogo.cn/mmopen/vi_32/juLGhSPu7ibgYkuiaZhcI51X7j3d5kZNR6AFichNG4A2yfj16ZlRcEicvicVGdkYAGG3n8UoApspPiaBVht4ViaqEiajrA/132","nickName":"양호걸_UQ1","pwd":"R+0jdN3P4MXHPMFVe9cX5MbX5ulIXHJkfigPLKEeTBY5lUgxJWUNg0js1oGtbsKiLFL4ScqdmUbtHXIfrgQnWrwTNjf09OJLycbeJ+ka4+CV7I1eEqG8DtZPnQoCyxjoYMjO4soDl6EX9YgqaZp3DlUH4pXrYHYz58YyFkSeJEk=","sessionId":"16063913713451769","userId":1769,"userName":"sU8rGLeeM8L1s3tid","whetherFaceId":2,"whetherVip":2}
     * message : 登陆成功
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
         * headPic : https://thirdwx.qlogo.cn/mmopen/vi_32/juLGhSPu7ibgYkuiaZhcI51X7j3d5kZNR6AFichNG4A2yfj16ZlRcEicvicVGdkYAGG3n8UoApspPiaBVht4ViaqEiajrA/132
         * nickName : 양호걸_UQ1
         * pwd : R+0jdN3P4MXHPMFVe9cX5MbX5ulIXHJkfigPLKEeTBY5lUgxJWUNg0js1oGtbsKiLFL4ScqdmUbtHXIfrgQnWrwTNjf09OJLycbeJ+ka4+CV7I1eEqG8DtZPnQoCyxjoYMjO4soDl6EX9YgqaZp3DlUH4pXrYHYz58YyFkSeJEk=
         * sessionId : 16063913713451769
         * userId : 1769
         * userName : sU8rGLeeM8L1s3tid
         * whetherFaceId : 2
         * whetherVip : 2
         */

        private String headPic;
        private String nickName;
        private String pwd;
        private String sessionId;
        private int userId;
        private String userName;
        private int whetherFaceId;
        private int whetherVip;

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

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
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
    }
}
