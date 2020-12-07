package com.wd.tech.beans;

import java.util.List;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:根据极光消息（批量）获取我们的服务器的信息</p>
 *
 * @author Xaoyv
 * date 11/27/2020 2:56 PM
 */
public class JsonFriendInfoByJusernameBean {

    /**
     * result : [{"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","nickName":"GGBond","pwd":"bOI5BAk67nxPvY18c/iJKcZxKs31PJQGrh+HOVIzI/2ZPghqdmA1hXnqziZ4x/h++E2L2gxGHOWXTJFvWMc0kthWBWAK5/2v+7Kxgyge7QkA2ganv3QXHEQBcVlN7eyoE3hmKqcbcpWNNfd6wq/kx9ShpS5cL5egJgLEXXUr4s0=","userId":1721,"userName":"BKl3Yu18338061345"},{"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","nickName":"xygg","pwd":"Th9DYT/yb6IjpDJ5t0F8cHoykU23JCZCe7rv1TgsAY3w9/sLU0IpLqncMwfkbR0hC90U1+K9FQJt92Mxu98xUtPawIbS3LbPWyDCUe8tGapKeac9d0nMsMJYwfRpr1331AqLZZJimyU7orwylSiR9kWh4xbMDOYjYHClR2KjSe0=","userId":1734,"userName":"DXTqct13123123123"},{"headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-11-16/20201116190842.png","nickName":"年轻人哈1","pwd":"Th9DYT/yb6IjpDJ5t0F8cHoykU23JCZCe7rv1TgsAY3w9/sLU0IpLqncMwfkbR0hC90U1+K9FQJt92Mxu98xUtPawIbS3LbPWyDCUe8tGapKeac9d0nMsMJYwfRpr1331AqLZZJimyU7orwylSiR9kWh4xbMDOYjYHClR2KjSe0=","userId":1691,"userName":"K1piRm15811370082"}]
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
         * headPic : http://mobile.bwstudent.com/images/tech/default/tech.jpg
         * nickName : GGBond
         * pwd : bOI5BAk67nxPvY18c/iJKcZxKs31PJQGrh+HOVIzI/2ZPghqdmA1hXnqziZ4x/h++E2L2gxGHOWXTJFvWMc0kthWBWAK5/2v+7Kxgyge7QkA2ganv3QXHEQBcVlN7eyoE3hmKqcbcpWNNfd6wq/kx9ShpS5cL5egJgLEXXUr4s0=
         * userId : 1721
         * userName : BKl3Yu18338061345
         */

        private String headPic;
        private String nickName;
        private String pwd;
        private int userId;
        private String userName;

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
    }
}
