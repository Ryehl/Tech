package com.wd.tech.beans;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:解绑FaceId</p>
 *
 * @author Xaoyv
 * date 11/24/2020 10:17 AM
 */
public class JsonUnbindFaceIdBean {

    /**
     * message : 解绑成功
     * status : 0000
     */

    private String message;
    private String status;

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
