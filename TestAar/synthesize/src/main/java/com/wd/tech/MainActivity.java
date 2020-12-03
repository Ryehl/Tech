package com.wd.tech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.wxapi.Constants;
import com.wd.tech.wxapi.PayBean;
import com.wd.tech.wxapi.VIPBean;
import com.wd.tech.wxapi.WXUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Button web_view, wechatlogin, wechatpay, shangla, duotu, xianchengchi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web_view = findViewById(R.id.webview);
        wechatlogin = findViewById(R.id.wechat_login);
        wechatpay = findViewById(R.id.wechat_pay);
        shangla = findViewById(R.id.shangla);
        duotu = findViewById(R.id.duotu);
        xianchengchi = findViewById(R.id.xianchengchi);

        NetUtils.getNetUtils().setHeader("16068962979531721", "1721");

        //webView
        web_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                startActivity(intent);

            }
        });
        //微信登录
        wechatlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //创建微信api并注册到微信
                Constants.wx_api = WXAPIFactory.createWXAPI(MainActivity.this, Constants.APP_ID, true);
                Constants.wx_api.registerApp(Constants.APP_ID);

                //发起登录请求
                final SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wechat_sdk_demo_test";
                Constants.wx_api.sendReq(req);

            }
        });
        //微信支付
        wechatpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String, Object> map = new HashMap<>();

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("1721");
                stringBuilder.append(1001);
                stringBuilder.append("tech");
                String re = stringBuilder.toString();
                String s=MD5(re);

                map.put("commodityId", 1001);
                map.put("sign", s);

                NetUtils.getNetUtils().postInfo(Urls.Buy_VIP, map, new NetUtils.GetJsonListener() {
                    @Override
                    public void success(String s) {
                        VIPBean vipBean=new Gson().fromJson(s,VIPBean.class);
                        if(vipBean.getMessage().equals("下单成功")){
                            HashMap<String,Object> map1=new HashMap<>();
                            map1.put("orderId","20201202102231297");
                            map1.put("payType","1");
                            NetUtils.getNetUtils().postInfo(Urls.Pay_Url, map1, new NetUtils.GetJsonListener() {
                                @Override
                                public void success(String s) {
                                    PayBean pay=new Gson().fromJson(s,PayBean.class);
                                    if(pay!=null && pay.getMessage().equals("支付成功")){
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
        //上拉
        shangla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ShangLaActivity.class);
                startActivity(intent);

            }
        });
        //多图
        duotu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, DuoTuActivity.class);
                startActivity(intent);

            }
        });
        //线程池
//        xianchengchi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent=new Intent(MainActivity.this,WebViewActivity.class);
//                startActivity(intent);
//
//            }
//        });


    }

    /**
     * MD5加密
     *
     * @param sourceStr
     * @return
     */
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
