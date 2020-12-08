package com.wd.tech.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;

import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.MyApp;
import com.wd.tech.R;
import com.wd.tech.presenters.ActCallPresenter;
import com.wd.tech.utils.JIMUtils;
import com.wd.tech.utils.JMRtcUrl;

import java.util.ArrayList;
import java.util.List;

import cn.jiguang.jmrtc.api.JMRtcClient;
import cn.jiguang.jmrtc.api.JMRtcListener;
import cn.jiguang.jmrtc.api.JMRtcSession;
import cn.jiguang.jmrtc.api.JMSignalingMessage;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.callback.RequestCallback;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

public class CallActivity extends BaseActivity<ActCallPresenter> {

    private String TAG = "CallActivity";
    private static JMRtcSession session;//通话数据元信息对象

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        JMRtcClient.getInstance().initEngine(new JMRtcListener() {
            @Override
            public void onEngineInitComplete(final int errCode, final String errDesc) {
                super.onEngineInitComplete(errCode, errDesc);
                Log.v(TAG, "onEngineInitComplete");
            }

            @Override
            public void onCallOutgoing(JMRtcSession callSession) {
                super.onCallOutgoing(callSession);
                session = callSession;
                Log.v(TAG, "onCallOutgoing");
            }

            @Override
            public void onCallInviteReceived(JMRtcSession callSession) {
                super.onCallInviteReceived(callSession);
                Log.v(TAG, "onCallInviteReceived");
                session = callSession;
                session.getInviterUserInfo(new RequestCallback() {
                    @Override
                    public void gotResult(int i, String s, Object o) {
                        if (i != 0) {
                            JMRtcClient.getInstance().hangup(new BasicCallback() {
                                @Override
                                public void gotResult(int responseCode, String responseMessage) {
                                }
                            });
                            return;
                        }
//                        if(o instanceof UserInfo){
//                            UserInfo userInfo = (UserInfo) o;
//                            userInfo.getUserName();
//                            Intent intent = new Intent(MyApp.context, VideoPhoneActivity.class);
//                            intent.putExtra(VariableName.TYPE,1);
//                            intent.putExtra(VariableName.DATA,userInfo.getUserName());
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            MyApp.context.startActivity(intent);
//                        }
                    }
                });
            }

            @Override
            public void onCallOtherUserInvited(UserInfo fromUserInfo, List<UserInfo> invitedUserInfos, JMRtcSession callSession) {
                super.onCallOtherUserInvited(fromUserInfo, invitedUserInfos, callSession);
                session = callSession;
                Log.v(TAG, "onCallOtherUserInvited");
            }

            //主线程回调
            @Override
            public void onCallConnected(JMRtcSession callSession, SurfaceView localSurfaceView) {
                super.onCallConnected(callSession, localSurfaceView);
                session = callSession;
                Log.v(TAG, "onCallConnected");
            }

            //主线程回调
            @Override
            public void onCallMemberJoin(UserInfo joinedUserInfo, SurfaceView remoteSurfaceView) {
                super.onCallMemberJoin(joinedUserInfo, remoteSurfaceView);
                Log.v(TAG, "onCallMemberJoin");
            }

            @Override
            public void onPermissionNotGranted(final String[] requiredPermissions) {
                Log.v(TAG, "onPermissionNotGranted");
            }

            @Override
            public void onCallMemberOffline(final UserInfo leavedUserInfo, JMRtcClient.DisconnectReason reason) {
                super.onCallMemberOffline(leavedUserInfo, reason);
                Log.v(TAG, "onCallMemberOffline");
                JMRtcClient.getInstance().hangup(new BasicCallback() {
                    @Override
                    public void gotResult(int responseCode, String responseMessage) {

                    }
                });
            }

            @Override
            public void onCallDisconnected(JMRtcClient.DisconnectReason reason) {
                super.onCallDisconnected(reason);
                session = null;
                Log.v(TAG, "onCallDisconnected");
            }

            @Override
            public void onCallError(int errorCode, String desc) {
                super.onCallError(errorCode, desc);
                session = null;
                Log.v(TAG, "onCallError");
            }

            @Override
            public void onRemoteVideoMuted(UserInfo remoteUser, boolean isMuted) {
                super.onRemoteVideoMuted(remoteUser, isMuted);
                Log.v(TAG, "onRemoteVideoMuted");
            }
        });

        Intent intent = getIntent();
        String jUserName = intent.getStringExtra("JUserName");
        JIMUtils.getJimUtils().getOtherUserInfo(jUserName, new GetUserInfoCallback() {
            @Override
            public void gotResult(int i, String s, UserInfo userInfo) {
                ArrayList<UserInfo> list = new ArrayList<>();
                list.add(userInfo);
                list.add(JIMUtils.getJimUtils().getLocalUserInfo());
                JMRtcClient.getInstance().call(list, JMSignalingMessage.MediaType.AUDIO, new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                        Log.d(TAG, "gotResult: " + i + "\t" + s);
                    }
                });
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_call;
    }

    @Override
    public ActCallPresenter initPresenter() {
        return new ActCallPresenter();
    }
}
