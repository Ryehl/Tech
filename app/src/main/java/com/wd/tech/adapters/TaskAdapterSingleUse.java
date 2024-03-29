package com.wd.tech.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.beans.TaskBean;

import java.util.List;

public class TaskAdapterSingleUse extends RecyclerView.Adapter<TaskAdapterSingleUse.ViewHolder> {
    private List<TaskBean.ResultBean> list;

    public TaskAdapterSingleUse(List<TaskBean.ResultBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.mytask_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //判断任务状态(已完成或者是未完成)
        if (list.get(position).getStatus() == 1) {
            holder.mytask_but.setText("已完成");
            holder.mytask_num.setText("+" + list.get(position).getTaskIntegral());
            holder.mytask_title.setText(list.get(position).getTaskName());
        } else {
            if (list.get(position).getTaskType() == 2) {
                switch (list.get(position).getTaskName()) {
                    case "完善用户信息":
                        holder.mytask_but.setText("去完善");
                        break;
                    case "绑定微信":
                        holder.mytask_but.setText("去绑定");
                        break;
                }
                holder.mytask_num.setText("+" + list.get(position).getTaskIntegral());
                holder.mytask_title.setText(list.get(position).getTaskName());
            }
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mytask_title, mytask_num;
        Button mytask_but;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mytask_title = itemView.findViewById(R.id.mytask_title);
            mytask_num = itemView.findViewById(R.id.mytask_num);
            mytask_but = itemView.findViewById(R.id.mytask_but);
        }
    }
}
