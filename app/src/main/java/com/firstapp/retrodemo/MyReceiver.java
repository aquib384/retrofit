package com.firstapp.retrodemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String status=NetworkCalls.getConnectivityStatusString(context);
        Toast.makeText(context,status,Toast.LENGTH_SHORT).show();
    }
}
