package com.wd.tech.beans;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:判断是不是好友</p>
 *
 * @author Xaoyv
 * date 11/25/2020 9:00 AM
 */
public class JsonIsFriendBean {

    /**
     * flag : 1
     * message : 已是好友
     * status : 0000
     */

    private int flag;
    private String message;
    private String status;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
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
}
