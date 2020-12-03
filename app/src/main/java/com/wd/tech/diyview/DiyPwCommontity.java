package com.wd.tech.diyview;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.wd.tech.R;
import com.wd.tech.activities.AddfriendActivity;
import com.wd.tech.activities.CreateGroupActivity;
import com.wd.tech.activities.SearchFriendGroupActivity;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:自定义消息界面弹框</p>
 *
 * @author Xaoyv
 * date 11/13/2020 2:20 PM
 */
public class DiyPwCommontity extends PopupWindow {

    private LinearLayout addFriend;
    private LinearLayout addGroup;

    public DiyPwCommontity(Context context) {
        super(View.inflate(context, R.layout.diy_pw_msg_top, null), LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        initView();
    }

    private void initView() {
        View view = getContentView();
        //find view by id
        addFriend = view.findViewById(R.id.diy_msg_top_add_friend);
        addGroup = view.findViewById(R.id.diy_msg_top_add_group);
        //set onclick listener
        addFriend.setOnClickListener(v -> {
            Intent intent = new Intent(view.getContext(), SearchFriendGroupActivity.class);
            intent.putExtra("TYPE", SearchFriendGroupActivity.FIREND);
            view.getContext().startActivity(intent);
        });
        addGroup.setOnClickListener(v -> {
            Intent intent = new Intent(view.getContext(), SearchFriendGroupActivity.class);
            intent.putExtra("TYPE", SearchFriendGroupActivity.FIREND);
            view.getContext().startActivity(intent);
        });
    }
}
