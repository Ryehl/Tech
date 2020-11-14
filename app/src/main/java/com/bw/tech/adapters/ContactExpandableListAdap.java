package com.bw.tech.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.bw.mylibrary.bean.Constant;
import com.bw.tech.R;
import com.bw.tech.beans.JsonFriendListBean;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

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
        View view = View.inflate(parent.getContext(), R.layout.item_contact_group, null);
        TextView groupName = view.findViewById(R.id.item_contact_group_groupName);
        groupName.setText(friendListBean.getResult().get(groupPosition).getGroupName());
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = View.inflate(parent.getContext(), R.layout.item_contact_child, null);

        //data bean
        JsonFriendListBean.ResultBean.FriendInfoListBean friendInfo = friendListBean.getResult().get(groupPosition).getFriendInfoList().get(childPosition);

        //find view by id
        TextView name = view.findViewById(R.id.item_contact_child_tv_friendName);
        SimpleDraweeView hd = view.findViewById(R.id.item_contact_child_sdv_hd);

        //set data
        name.setText(friendInfo.getNickName());
        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(view.getResources())
                .setRoundingParams(RoundingParams.asCircle())
                .build();
        hd.setHierarchy(hierarchy);
        hd.setImageURI(friendInfo.getHeadPic());
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
