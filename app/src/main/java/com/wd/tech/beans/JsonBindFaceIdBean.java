package com.wd.tech.beans;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:绑定FaceId</p>
 *
 * @author Xaoyv
 * date 11/24/2020 9:52 AM
 */
public class JsonBindFaceIdBean {

    /**
     * faceId : kr7GB7oiZbAk2G2egLs1zUol/7xGkO/g3M2ekwipGSqxOHcI5VWI6VY17tMSlS28h54jT76gruy0ZmoRQoeGvR+u+TpNzFkzBAp7Xg8Yd7M0JXa6unKO3jJUkDPVxElUunG3zqeXVGMAyc4QF4n2EhUPwAMLrVR+h6UKelhltxw=
     * message : 绑定成功
     * status : 0000
     */

    private String faceId;
    private String message;
    private String status;

    public String getFaceId() {
        return faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
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
