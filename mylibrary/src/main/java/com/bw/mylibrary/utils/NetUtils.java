package com.bw.mylibrary.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.bw.mylibrary.Model.Api;
import com.bw.mylibrary.bean.Urls;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetUtils {

    private Retrofit retrofit;

    //内部类实现单例模式
    private static final class NetUtilsHolder{
        private static final NetUtils instance=new NetUtils();
    }
    public static NetUtils getInstance(){
        return NetUtilsHolder.instance;
    }

    //网络判断
    public boolean NetWork(Context context){
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected()){
            return true;
        }
        return false;
    }

    //头部拦截
    public static class headInterceptor implements Interceptor{
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request=chain.request().newBuilder()
                    .addHeader("userId","")
                    .addHeader("sessionId","")
                    .build();
            return chain.proceed(request);
        }
    }

    //Ok
    public OkHttpClient okHttpClient(){
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(new headInterceptor())
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3,TimeUnit.SECONDS)
                .readTimeout(3,TimeUnit.SECONDS)
                .build();
    }

    public NetUtils(){
        retrofit=new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Urls.Base_Url)
                .client(okHttpClient())
                .build();
    }

    public Api create(){
        return retrofit.create(Api.class);
    }



}
