package com.aja.loancare;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


public class StartActivityOnBootReciever extends BroadcastReceiver {
    private static final String TAG = "StartActivityOnBootReci";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: Inside After Boot Completed");
        Toast.makeText(context, "HEllo ", Toast.LENGTH_SHORT).show();
    }
}