package com.wd.tech.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyMessageReceiver extends BroadcastReceiver {

    //Action

    @Override
    public void onReceive(Context context, Intent intent) {
        if (listener == null)
            return;
        listener.onRec();
    }

    private RecListener listener;

    public void setListener(RecListener listener) {
        this.listener = listener;
    }

    interface RecListener{
        void onRec();
    }
}
