package com.aja.loancare;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LoanStopReciever extends BroadcastReceiver {
    int loan_id;

    @Override
    public void onReceive(Context context, Intent intent) {
        loan_id = intent.getIntExtra(fragment_loanList.LOAN_RECIEVER_ID, 0);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.cancelAll();
        }

    }

}
