package com.wd.tech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wd.mylibrary.utils.NetUtils;

import com.wd.tech.wxapi.Constants;
import com.wd.tech.wxapi.WXUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import beans.BuyVIPBean;
import beans.PayBean;

public class MainActivity extends AppCompatActivity {

    Button button,wechat_pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.wechat_login);
        wechat_pay=findViewById(R.id.wechat_pay);

        NetUtils.getNetUtils().setHeader("16068718290201721","1721");

        //创建微信api并注册到微信
        Constants.wx_api = WXAPIFactory.createWXAPI(MainActivity.this, Constants.APP_ID, true);
        Constants.wx_api.registerApp(Constants.APP_ID);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //发起登录请求
                final SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wechat_sdk_demo_test";
                Constants.wx_api.sendReq(req);

            }
        });

        wechat_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder stringBuilder=new StringBuilder();
                stringBuilder.append("1721");
                stringBuilder.append(1001);
                stringBuilder.append("tech");
                String re="";
                re=stringBuilder.toString();
                String s=MD5(re);
                Log.i("tag",s);
                HashMap<String,Object> map=new HashMap<>();
                map.put("sign",s);
                map.put("commodityId","1001");
                NetUtils.getNetUtils().postInfo(Urls.Buy_VIP, map, new NetUtils.GetJsonListener() {
                    @Override
                    public void success(String s) {
                        //解析
                        BuyVIPBean buyVIPBean=new Gson().fromJson(s,BuyVIPBean.class);
                        if(buyVIPBean.getMessage().equals("下单成功")){
                            HashMap<String,Object> map1=new HashMap<>();
                            map1.put("orderId",buyVIPBean.getResult().getOrderId());
                            map1.put("payType","1");
                            NetUtils.getNetUtils().postInfo(Urls.Pay_Url, map1, new NetUtils.GetJsonListener() {
                                @Override
                                public void success(String s) {
                                    PayBean pay=new Gson().fromJson(s,PayBean.class);
                                    //Toast.makeText(MainActivity.this,s, Toast.LENGTH_SHORT).show();
                                    if(pay!=null &&pay.getStatus().equals("0000")){
                                        PayReq req = new PayReq();
                                        req.appId = pay.getAppId();
                                        req.nonceStr = pay.getNonceStr();
                                        req.partnerId = pay.getPartnerId();
                                        req.prepayId = pay.getPrepayId();
                                        req.sign = pay.getSign();
                                        req.timeStamp = pay.getTimeStamp();
                                        req.packageValue = pay.getPackageValue();
                                        //去调微信
                                        WXUtils.reg(MainActivity.this).sendReq(req);
                                    }
                                }

                                @Override
                                public void error() {

                                }
                            });
                        }
                    }

                    @Override
                    public void error() {

                    }
                });
            }
        });
    }

    // MD5加密
    public static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }
}
