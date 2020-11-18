package com.bw.mylibrary.interfaces;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:网络接口</p>
 *
 * @author Xaoyv
 * date 2020/10/15 23:56
 */
public interface IApi {
    /**
     * GET请求
     *
     * @param path 网址
     * @param map  参数
     * @return Observable
     */
    @GET
    Observable<ResponseBody> getInfo(@Url String path, @QueryMap HashMap<String, Object> map);

    @GET
    Call<ResponseBody> downLoadFile(@Url String filePath);

    @Multipart
    @POST
    Observable<ResponseBody> uploadHd(@Url String path, @Part MultipartBody.Part part);

    /**
     * 进行多个文件的上传
     *
     * @param path    需要上传的路径
     * @param content 传入的文本内容 参数需要进行修改
     * @param list    传入的文件的集合
     * @return obs
     */
    @Multipart
    @POST
    Observable<ResponseBody> uploadFiles(@Url String path, @Query("content") String content, @Part List<MultipartBody.Part> list);

    @POST
    Observable<ResponseBody> postInfo(@Url String path, @QueryMap HashMap<String, Object> map);

    @POST
    Observable<ResponseBody> postInfoWithBody(@Url String path, @Body RequestBody body);

    @PUT
    Observable<ResponseBody> putInfo(@Url String path, @QueryMap HashMap<String, Object> map);

    @PUT
    Observable<ResponseBody> putInfoWithBody(@Url String path, @Body RequestBody body);

    @DELETE
    Observable<ResponseBody> deleteInfo(@Url String path, @QueryMap HashMap<String, Object> map);

    @DELETE
    Observable<ResponseBody> deleteInfoWithBody(@Url String path, @Body RequestBody body);
}
