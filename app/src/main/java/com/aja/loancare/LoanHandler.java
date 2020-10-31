package com.aja.loancare;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.aja.loancare.mvvmmodel.Loan;

import java.util.Calendar;

public class LoanHandler {
    private static final String TAG = "LoanHandler";
    private Context mContext;
    public LoanHandler(Context context) {
        mContext=context;
    }

    public void scheduleLoanAlarm(Loan loan)
    {
        Log.i(TAG, "scheduleLoanAlarm: Inside ");
        AlarmManager alarmManager =(AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
        Intent myIntent=new Intent(mContext,LoanReciever.class);
        myIntent.putExtra("Loan_ID",loan.getLoan_id());
        PendingIntent pendingintent= PendingIntent.getBroadcast(mContext, loan.getLoan_id(),myIntent,0);
        Calendar calendar=Calendar.getInstance();
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),1000,pendingintent);
    }
}
