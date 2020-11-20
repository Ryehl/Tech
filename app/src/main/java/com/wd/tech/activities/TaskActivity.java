package com.wd.tech.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.adapters.TaskAdapter;
import com.wd.tech.adapters.TaskAdapterSingleUse;
import com.wd.tech.beans.TaskBean;
import com.wd.tech.presenters.TaskPresenter;

import java.util.ArrayList;
import java.util.List;

public class TaskActivity extends BaseActivity<TaskPresenter> {
    private List<TaskBean.ResultBean> list=new ArrayList<>();
    private TaskAdapter taskAdapter;
    private RecyclerView mytaskreycle,mytask_singleuse_recycle;
    private TaskAdapterSingleUse taskAdapterSingleUse;
    @Override
    public void initView() {
        mytaskreycle=findViewById(R.id.mytask_recycle);
        mytask_singleuse_recycle=findViewById(R.id.mytask_singleuse_recycle);
    }

    @Override
    public void initData() {
        pre.getTaskData();
    }
    public void TaskData(String json){
        //解析
        TaskBean taskBean=new Gson().fromJson(json,TaskBean.class);
        list.addAll(taskBean.getResult());
        taskAdapter=new TaskAdapter(list);
        mytaskreycle.setAdapter(taskAdapter);
        mytaskreycle.setLayoutManager(new LinearLayoutManager(TaskActivity.this));

//        taskAdapterSingleUse=new TaskAdapterSingleUse(list);
//        mytask_singleuse_recycle.setAdapter(taskAdapterSingleUse);
//        mytask_singleuse_recycle.setLayoutManager(new LinearLayoutManager(TaskActivity.this));

    }
    @Override
    public int getLayout() {
        return R.layout.activity_task;
    }

    @Override
    public TaskPresenter initPresenter() {
        return new TaskPresenter();
    }
}
