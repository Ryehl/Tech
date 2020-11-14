package com.bw.tech.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.bw.mylibrary.bean.Constant;
import com.bw.tech.R;
import com.bw.tech.beans.JsonFriendListBean;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:联系人二级列表适配器</p>
 *
 * @author Xaoyv
 * date 11/14/2020 8:40 AM
 */
public class ContactExpandableListAdap extends BaseExpandableListAdapter {
    private JsonFriendListBean friendListBean;
    private Context context;

    public ContactExpandableListAdap(JsonFriendListBean friendListBean, Context context) {
        this.friendListBean = friendListBean;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        //list's count
        return friendListBean.getResult().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return friendListBean.getResult().get(groupPosition).getFriendInfoList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        //TODO
        return View.inflate(parent.getContext(), R.layout.item_contact_group, null);
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        //TODO
        return View.inflate(parent.getContext(), R.layout.item_contact_child, null);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
