package com.wd.tech.adapters;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.mylibrary.utils.TypeConversionUtils;
import com.wd.tech.R;
import com.wd.tech.beans.CommuntiyBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder> {
    private List<CommuntiyBean.ResultBean> list;

    public CommunityAdapter(List<CommuntiyBean.ResultBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.community_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //赋值
        holder.community_nickName.setText(list.get(position).getNickName());
        String time = TypeConversionUtils.long2String(list.get(position).getPublishTime());
        holder.community_time.setText(time);//时间
        holder.community_signature.setText(list.get(position).getSignature());//签名
        holder.community_content.setText(list.get(position).getContent());//评论内容
        holder.community_comment_num.setText(list.get(position).getComment() + "");//评论数
        holder.community_praise_num.setText(list.get(position).getPraise() + "");//点赞数
        String head = list.get(position).getHeadPic();//头像
        Uri uri = Uri.parse(head);
        holder.community_head_img.setImageURI(uri);

        String i = list.get(position).getFile();//图片
        Uri uri1 = Uri.parse(i);
        holder.community_img.setImageURI(uri1);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView community_nickName, community_time, community_praise_num, community_comment_num, community_content, community_signature;
        SimpleDraweeView community_head_img, community_img;
        TextView comment_nickName, comment_content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            community_signature = itemView.findViewById(R.id.community_signature);
            community_praise_num = itemView.findViewById(R.id.community_praise_num);
            community_content = itemView.findViewById(R.id.community_content);
            community_head_img = itemView.findViewById(R.id.community_head_img);
            community_img = itemView.findViewById(R.id.community_img);
            community_nickName = itemView.findViewById(R.id.community_nickName);
            community_time = itemView.findViewById(R.id.community_time);
            community_comment_num = itemView.findViewById(R.id.community_comment_num);

            //评论的
            comment_nickName = itemView.findViewById(R.id.comment_nickName);
            comment_content = itemView.findViewById(R.id.comment_content);

            //点击头像跳转到用户中心页面
            community_head_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(findUserHead!=null){
                        findUserHead.finduser(list.get(getLayoutPosition()).getUserId(),list.get(getLayoutPosition()).getHeadPic(),list.get(getLayoutPosition()).getSignature(),list.get(getLayoutPosition()).getNickName());
                    }
                }
            });


        }
    }

    //接口回调
    public interface FindUserHead{
        void finduser(int index,String head,String signature,String nickName);
    }
    public FindUserHead findUserHead;

    public void setFindUserHead(FindUserHead findUserHead) {
        this.findUserHead = findUserHead;
    }
}
