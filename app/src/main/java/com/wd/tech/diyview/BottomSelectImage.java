package com.wd.tech.diyview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.wd.tech.R;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:底部弹框调用系统相机或者图库</p>
 *
 * @author Xaoyv
 * date 11/18/2020 1:45 PM
 */
public class BottomSelectImage extends LinearLayout {
    public BottomSelectImage(Context context) {
        super(context);
        initView(context);
    }

    public BottomSelectImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public BottomSelectImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.bottom_sheet_dialog, this);

        //find view by id
        TextView camera = view.findViewById(R.id.bsd_tv_camera);
        TextView gallery = view.findViewById(R.id.bsd_tv_gallery);
        TextView cancel = view.findViewById(R.id.bsd_tv_cancel);

        //Listener
        camera.setOnClickListener(v -> {
            if (listener != null)
                listener.camera();
        });
        gallery.setOnClickListener(v -> {
            if (listener != null)
                listener.gallery();
        });
        cancel.setOnClickListener(v -> {
            cancelDragAndDrop();
        });
    }

    private BottomDialogListener listener;

    public interface BottomDialogListener {
        void camera();

        void gallery();
    }

    public void setListener(BottomDialogListener listener) {
        this.listener = listener;
    }
}
