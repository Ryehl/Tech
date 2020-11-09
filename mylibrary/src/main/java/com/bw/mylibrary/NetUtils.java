package com.bw.mylibrary;

public class NetUtils {
    private static NetUtils netUtils;
    private NetUtils(){}

    public static NetUtils getNetUtils() {
        return netUtils = netUtils == null ? new NetUtils() : netUtils;
    }
}
