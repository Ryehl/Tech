package com.wd.tech.utils;

import android.util.Log;

import cn.jiguang.jmrtc.api.JMRtcClient;
import cn.jiguang.jmrtc.api.JMRtcListener;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:</p>
 *
 * @author Xaoyv
 * date 12/8/2020 10:35 AM
 */
public class JMRtcUrl {
    private String TAG = "JMRtcUrl";
    private static JMRtcUrl jmRtcUrl;
    private JMRtcUrl(){}
    public static JMRtcUrl getJmRtcUrl() {
        return jmRtcUrl == null ? jmRtcUrl = new JMRtcUrl() : jmRtcUrl;
    }

    public void init(){
        JMRtcClient.getInstance().initEngine(new JMRtcListener(){
            @Override
            public void onCallError(int i, String s) {
                super.onCallError(i, s);
                Log.d(TAG, "onCallError: " + s);
                JMRtcClient.getInstance().reinitEngine();
            }
        });
        //JMRtcClient.getInstance().reinitEngine();
    }
}
