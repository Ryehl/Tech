package com.wd.tech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.wxapi.WXUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import adapters.CommunityAdapter;
import beans.BuyVIPBean;
import beans.CommunityBean;
import beans.PayBean;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<CommunityBean.ResultBean> list=new ArrayList<>();
    private CommunityAdapter communityAdapter;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.but);
        recyclerView=findViewById(R.id.recycle);
        //头参
        NetUtils.getNetUtils().setHeader("16068222614751721","1721");

        NetUtils.getNetUtils().getInfo(Urls.WeChatPay, new HashMap<String, Object>(), new NetUtils.GetJsonListener() {
            @Override
            public void success(String s) {
                //解析
                CommunityBean communityBean=new Gson().fromJson(s,CommunityBean.class);
                list.addAll(communityBean.getResult());
                communityAdapter=new CommunityAdapter(list);
                recyclerView.setAdapter(communityAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));

//                //付款
//                NetUtils.getNetUtils().postInfo(Urls.Buy_VIP,);

            }

            @Override
            public void error() {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
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
                            map1.put("orderId","20201201195259116");
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

