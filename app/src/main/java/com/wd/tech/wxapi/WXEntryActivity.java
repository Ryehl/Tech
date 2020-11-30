package com.wd.tech.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mmkv.MMKV;
import com.wd.mylibrary.bean.ConstantMMkv;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.Urls;
import com.wd.tech.activities.MainActivity;
import com.wd.tech.beans.WxBwan;

import java.util.HashMap;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Constants.wx_api.handleIntent(getIntent(), this);


    }

    //微信请求响应
    @Override
    public void onReq(BaseReq baseReq) {

    }
    //发送到微信请求的响应的结果
    @Override
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                Log.i("WXTest","onResp OK");

                if(resp instanceof SendAuth.Resp){
                    SendAuth.Resp newResp = (SendAuth.Resp) resp;
                    //获取微信传回的code
                    String code = newResp.code;

                    HashMap<String,Object> map=new HashMap<>();
                    map.put("code",code);
                    //微信网络请求
                    NetUtils.getNetUtils().postInfo(Urls.WeChatLogin_Url, map, new NetUtils.GetJsonListener() {
                        @Override
                        public void success(String json) {
                            //解析
                            WxBwan wxBwan=new Gson().fromJson(json,WxBwan.class);

                            //将头像  昵称等信息全都存起来 并替换掉
                            MMKV mmkv=MMKV.defaultMMKV();
                            mmkv.putBoolean(ConstantMMkv.Key_IsLogin, true);
                            mmkv.putString("status", wxBwan.getStatus());//状态
                            mmkv.putString("nickName", wxBwan.getResult().getNickName());//昵称
                            mmkv.putInt("whetherVip", wxBwan.getResult().getWhetherVip());//是否是Vip
                            mmkv.putInt("whetherFaceId", wxBwan.getResult().getWhetherFaceId());//FaceId
                            mmkv.putString("headPic", wxBwan.getResult().getHeadPic());//头像

                            Intent intent=new Intent(WXEntryActivity.this, MainActivity.class);
                            startActivity(intent);

                        }

                        @Override
                        public void error() {

                        }
                    });

                    Log.i("WXTest","onResp code = "+code);

                }

                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                Log.i("WXTest","onResp ERR_USER_CANCEL ");
                //发送取消
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                Log.i("WXTest","onResp ERR_AUTH_DENIED");
                //发送被拒绝
                break;
            default:
                Log.i("WXTest","onResp default errCode " + resp.errCode);
                //发送返回
                break;
        }
        finish();
    }
}
