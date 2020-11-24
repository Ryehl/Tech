package com.wd.tech.beans;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:faceId</p>
 *
 * @author Xaoyv
 * date 11/24/2020 9:27 AM
 */
public class RealmFaceInfoBean extends RealmObject {
    @PrimaryKey
    public int id;
    public String faceId;
    public byte[] faceInfo;
}
