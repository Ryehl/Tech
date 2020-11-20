package com.wd.tech.adapters;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wd.tech.R;

import java.util.ArrayList;

/**
 * <p>Project's name:Test</p>
 * <p>说明:Adapter</p>
 *
 * @author Xaoyv
 * date 11/16/2020 11:26 AM
 */
public class RecyUploadImagesAdap extends RecyclerView.Adapter<RecyUploadImagesAdap.Holder> {

    private static final String TAG = "RecyAdap";
    //图片 集合
    private ArrayList<String> list;
    //最大展示数量
    private int picMax;

    //接口回调的监听
    private CallbackListener listener;

    /**
     * set listener
     *
     * @param listener 监听器
     */
    public void setListener(CallbackListener listener) {
        this.listener = listener;
    }

    /**
     * 设置 最大图片数量
     *
     * @param picMax 9
     */
    public RecyUploadImagesAdap(int picMax) {
        this.picMax = picMax;
    }

    /**
     * 设置图片的集合
     *
     * @param list 图片集合
     */
    public void setImageList(ArrayList<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + getItemCount());
        //根据下标显示 p 1 || p 8
        if (position >= list.size() && position <= (picMax - 1)) {
            //设置图片
            holder.show.setImageResource(R.drawable.add);
            //设置删除 显示
            holder.delete.setVisibility(View.GONE);
        } else {
            //展示列表中的数据
            Glide.with(holder.itemView.getContext())
                    .load(list.get(position))
                    .into(holder.show);
            holder.delete.setVisibility(View.VISIBLE);
        }
        holder.delete.setOnClickListener(v -> {
            //删除
            if (listener == null)
                return;
            listener.delete(position);
            //刷新适配器
            notifyDataSetChanged();
        });
        holder.show.setOnClickListener(v -> {
            if (position >= list.size() && position <= (picMax - 1)) {
                if (listener != null) {
                    listener.add();
                }
            } else {
                if (listener != null) {
                    listener.item(position, list);
                }
            }
        });
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(View.inflate(parent.getContext(), R.layout.item_uploadimgs_images, null));
    }

    @Override
    public int getItemCount() {
        //if list was null
        if (list == null)
            list = new ArrayList<>();
        //if list's size was zero
        if (list.size() == 0)
            return 1;
        return list.size() >= picMax ? picMax : list.size() + 1;
    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView show;
        ImageView delete;

        Holder(@NonNull View itemView) {
            super(itemView);
            show = itemView.findViewById(R.id.item_show_img);
            delete = itemView.findViewById(R.id.item_show_del);
        }
    }

    public interface CallbackListener {
        //图片添加事件
        void add();

        //删除第几张图片
        void delete(int position);

        //图片点击
        void item(int position, ArrayList<String> mList);
    }
}
