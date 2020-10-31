package com.aja.loancare;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class LoanReciever  extends BroadcastReceiver {
    private static final String TAG = "LoanReciever";
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "It is working", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onReceive: Inside reciever");
    }
}
