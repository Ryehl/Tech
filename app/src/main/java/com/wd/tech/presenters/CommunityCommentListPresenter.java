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

}
