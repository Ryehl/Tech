package com.wd.tech.beans;

import java.util.List;

public class IntegralRecordBean {

    /**
     * result : [{"amount":10,"createTime":1605492550000,"direction":1,"type":2},{"amount":20,"createTime":1605446893000,"direction":1,"type":4},{"amount":10,"createTime":1605320382000,"direction":1,"type":1},{"amount":20,"createTime":1605315459000,"direction":1,"type":4}]
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
         * amount : 10
         * createTime : 1605492550000
         * direction : 1
         * type : 2
         */

        private int amount;
        private long createTime;
        private int direction;
        private int type;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
