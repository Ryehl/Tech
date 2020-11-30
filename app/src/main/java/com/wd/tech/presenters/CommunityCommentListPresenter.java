package com.wd.tech.presenters;

import com.wd.mylibrary.base.BasePresenter;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.Urls;
import com.wd.tech.activities.CommunityCommentListActivity;

import java.util.HashMap;

public class CommunityCommentListPresenter extends BasePresenter<CommunityCommentListActivity> {

    public void getCommentListData(int communityId){
        HashMap<String,Object> map=new HashMap<>();
        map.put("communityId",communityId);
        map.put("page","1");
        map.put("count","5");
        NetUtils.getNetUtils().getInfo(Urls.CommunityCommentList_Url, map, new NetUtils.GetJsonListener() {
            @Override
            public void success(String json) {
                iView.CommentListData(json);
            }

            @Override
            public void error() {

            }
        });
    }
    public void getCommentListData2(int communityId,int page){
        HashMap<String,Object> map=new HashMap<>();
        map.put("communityId",communityId);
        map.put("page",page+"");
        map.put("count","5");
        NetUtils.getNetUtils().getInfo(Urls.CommunityCommentList_Url, map, new NetUtils.GetJsonListener() {
            @Override
            public void success(String json) {
                iView.CommentListData2(json);
            }

            @Override
            public void error() {

            }
        });
    }

    //发表评论
    public void getAddCommunityCommentData(String content,int commentId){
        HashMap<String,Object> map=new HashMap<>();
        map.put("content",content);
        map.put("communityId",commentId+"");
        NetUtils.getNetUtils().postInfo(Urls.AddCommunityComment_Url, map, new NetUtils.GetJsonListener() {
            @Override
            public void success(String json) {
                iView.AddCommunityCommentData(json);
            }

            @Override
            public void error() {

            }
        });
    }


}
