package com.wd.tech.adapters;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.mylibrary.utils.TypeConversionUtils;
import com.wd.tech.R;
import com.wd.tech.beans.InformationBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class InformationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<InformationBean.ResultBean> list;

    public InformationAdapter(List<InformationBean.ResultBean> list) {
        this.list = list;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //不同布局
        if (viewType == 2) {
            View view = View.inflate(parent.getContext(), R.layout.information_item, null);
            return new InformationViewHolder(view);
        } else {
            View view = View.inflate(parent.getContext(), R.layout.information_advertising_item, null);
            return new InformationAdvertisingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof InformationViewHolder) {
            ((InformationViewHolder) holder).information_title.setText(list.get(position).getTitle());
            ((InformationViewHolder) holder).information_content.setText(list.get(position).getSummary());
            ((InformationViewHolder) holder).information_content_title.setText(list.get(position).getSource());
            String url = list.get(position).getThumbnail();
            Uri uri = Uri.parse(url);
            ((InformationViewHolder) holder).information_img.setImageURI(uri);
            ((InformationViewHolder) holder).information_collect_num.setText("" + list.get(position).getCollection());
            ((InformationViewHolder) holder).information_share_num.setText("" + list.get(position).getShare());
//            SimpleDateFormat simpleDateFormat=new SimpleDateFormat();
//            simpleDateFormat.format("yy-")
            String time = TypeConversionUtils.long2String(list.get(position).getReleaseTime());
            ((InformationViewHolder) holder).information_content_time.setText(time);


        } else if (holder instanceof InformationAdvertisingViewHolder) {
            String url = list.get(position).getInfoAdvertisingVo().getPic();
            Uri uri = Uri.parse(url);
            ((InformationAdvertisingViewHolder) holder).advertising_img.setImageURI(uri);
            ((InformationAdvertisingViewHolder) holder).advertising_title.setText(list.get(position).getInfoAdvertisingVo().getContent());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getWhetherAdvertising();
    }

    //列表布局
    public class InformationViewHolder extends RecyclerView.ViewHolder {
        TextView information_title, information_content, information_content_title, information_content_time;
        TextView information_collect_num, information_share_num;
        SimpleDraweeView information_img;

        public InformationViewHolder(@NonNull View itemView) {
            super(itemView);
            information_collect_num = itemView.findViewById(R.id.information_collect_num);//收藏数量
            information_share_num = itemView.findViewById(R.id.information_share_num);//分享数量
            information_content = itemView.findViewById(R.id.information_content);//发布内容
            information_content_time = itemView.findViewById(R.id.information_content);//发布时间
            information_img = itemView.findViewById(R.id.information_img);//发布的图片
            information_content_title = itemView.findViewById(R.id.information_content_title);//发布
            information_title = itemView.findViewById(R.id.information_title);//发表内容的标题

            //利用接口回调将下标传过去
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (JumpDetails != null) {
                        JumpDetails.jumpdetails(getLayoutPosition());
                    }
                }
            });
        }
    }

    //广告布局
    public class InformationAdvertisingViewHolder extends RecyclerView.ViewHolder {
        TextView advertising_title;
        SimpleDraweeView advertising_img;

        public InformationAdvertisingViewHolder(@NonNull View itemView) {
            super(itemView);
            advertising_title = itemView.findViewById(R.id.advertising_title);
            advertising_img = itemView.findViewById(R.id.advertising_img);

            //利用接口回调将下标传过去
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (JumpDetails != null) {
                        JumpDetails.jumpdetails(getLayoutPosition());
                    }
                }
            });

        }
    }

    //接口回调  跳转时携带下标
    public interface OnJumpDetails {
        void jumpdetails(int index);
    }

    public OnJumpDetails JumpDetails;

    public void setOnJumpDetails(OnJumpDetails onJumpDetails) {
        this.JumpDetails = onJumpDetails;
    }
}
