package com.wd.tech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.wxapi.Constants;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Button go_webview,wechat_login,wechat_pay,shangla,xianchengchi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        go_webview=findViewById(R.id.go_webview);
        wechat_login=findViewById(R.id.wechat_login);
        wechat_pay=findViewById(R.id.wechat_pay);
        shangla=findViewById(R.id.shangla);
        xianchengchi=findViewById(R.id.xianchengchi);

        //头参
        NetUtils.getNetUtils().setHeader("16068961475031721","1721");

        //抽奖转盘(WebView与Js交互)
        go_webview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,WebViewActivity.class);
                startActivity(intent);
            }
        });
        //微信登录
        wechat_login.setOnClickListener(new View.OnClickListener() {
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
        wechat_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder stringBuilder=new StringBuilder();
                stringBuilder.append("1721");
                stringBuilder.append("1001");
                stringBuilder.append("tech");
                String r=stringBuilder.toString();
                String s=MD5(r);
                HashMap<String,Object> map=new HashMap<>();
                map.put("commodityId","1001");
                map.put("sign",s);
                NetUtils.getNetUtils().postInfo(Urls.Buy_VIP, map, new NetUtils.GetJsonListener() {
                    @Override
                    public void success(String s) {
                        //解析
                        VIPBean vipBean=new Gson().fromJson(s,VIPBean.class);
                        if(vipBean!=null&&vipBean.getMessage().equals("下单成功")){

                            HashMap<String,Object> map1=new HashMap<>();
                            map1.put("orderId","20201202155932471");
                            map1.put("payType","1");
                            NetUtils.getNetUtils().postInfo(Urls.Pay_Url, map1, new NetUtils.GetJsonListener() {
                                @Override
                                public void success(String s) {
//                                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
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

        //自定义上拉框
        shangla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ShanglaActivity.class);
                startActivity(intent);
            }
        });

      //线程池的使用
        ThreadManager.getThreadPollProxy().execute(new Runnable() {
            @Override
            public void run() {
                //请求网络相关等耗时的操作
            }
        });

    }

    /**
     *  MD5加密
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
