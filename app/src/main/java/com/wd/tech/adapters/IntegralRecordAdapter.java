package com.wd.tech.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.mylibrary.utils.TypeConversionUtils;
import com.wd.tech.R;
import com.wd.tech.beans.IntegralRecordBean;

import java.util.List;

public class IntegralRecordAdapter extends RecyclerView.Adapter<IntegralRecordAdapter.ViewHolder> {
    private List<IntegralRecordBean.ResultBean> list;

    public IntegralRecordAdapter(List<IntegralRecordBean.ResultBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.myintegralrecord_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        switch (list.get(position).getType()) {
            case 1:
                holder.myintegralrecord_title.setText("签到成功");
                break;
            case 2:
                holder.myintegralrecord_title.setText("评论");
                break;
            case 3:
                holder.myintegralrecord_title.setText("分享");
                break;
            case 4:
                holder.myintegralrecord_title.setText("发帖");
                break;
            case 5:
                holder.myintegralrecord_title.setText("抽奖");
                break;
            case 6:
                holder.myintegralrecord_title.setText("付费资讯");
                break;
            case 7:
                holder.myintegralrecord_title.setText("抽奖支出");
                break;
            case 8:
                holder.myintegralrecord_title.setText("完善个人信息");
                break;
            case 9:
                holder.myintegralrecord_title.setText("查看广告");
                break;
            case 10:
                holder.myintegralrecord_title.setText("绑定第三方");
                break;
        }
        if (list.get(position).getDirection() == 1) {
            holder.myintegralrecord_num.setText("+" + list.get(position).getAmount());
        } else {
            holder.myintegralrecord_num.setText("-" + list.get(position).getAmount());

        }
        String time = TypeConversionUtils.long2String(list.get(position).getCreateTime());
        holder.myintegralrecord_time.setText(time);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myintegralrecord_title, myintegralrecord_time, myintegralrecord_num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myintegralrecord_num = itemView.findViewById(R.id.myintegralrecord_num);
            myintegralrecord_time = itemView.findViewById(R.id.myintegralrecord_time);
            myintegralrecord_title = itemView.findViewById(R.id.myintegralrecord_title);
        }
    }
}
