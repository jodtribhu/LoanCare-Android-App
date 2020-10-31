package com.aja.loancare;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.aja.loancare.mvvmmodel.Loan;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LoanHandler {
    public static final String LOAN_ID_HANDLER = " com.aja.loancare.LOAN_ID_HANDLER";
    public static final String LOAN_BANK_NAME = " com.aja.loancare.LOAN_BANK_NAME";
    public static final String LOAN_INTEREST_RATE = " com.aja.loancare.LOAN_INTEREST_RATE";
    public static final String LOAN_TYPE = " com.aja.loancare.LOAN_TYPE";
    private static final String TAG = "LoanHandler";
    private Context mContext;
    Date date2;
    Loan loan;
    public LoanHandler(Context context) {
        mContext=context;
    }

    public void scheduleLoanAlarm(Loan loan)
    {
        Log.i(TAG, "scheduleLoanAlarm: Inside ");
        AlarmManager alarmManager =(AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
        Intent myIntent=new Intent(mContext,LoanReciever.class);
        myIntent.putExtra(LOAN_ID_HANDLER,loan.getLoan_id());
        myIntent.putExtra(LOAN_BANK_NAME,loan.getBankName());
        myIntent.putExtra(LOAN_INTEREST_RATE,loan.getInterest_rate());
        myIntent.putExtra(LOAN_TYPE,loan.getLoanType());
        myIntent.setAction("com.project.action.ALARM");
        PendingIntent pendingintent= PendingIntent.getBroadcast(mContext, loan.getLoan_id(),myIntent,0);
        Calendar calendar=Calendar.getInstance();
//        calendar.set(Calendar.DAY_OF_MONTH,loan.getRday());
//        calendar.set(Calendar.MONTH,loan.getRmonth());
//        calendar.set(Calendar.YEAR,loan.getRyear())  ;
        Log.i(TAG, "scheduleLoanAlarm: Check the ring date "+calendar.getTime());
        calendar.setTime(Calendar.getInstance().getTime());
        calendar.add(Calendar.SECOND, 5);
        Log.i(TAG, "scheduleLoanAlarm: Check sample "+calendar.getTime()+"Loan id "+loan.getLoan_id());
        if(pendingintent!=null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000, pendingintent);
        }
    }

    public void cancelAlarm(Loan loan)
    {
        AlarmManager alarmManager =(AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
        Intent myIntent=new Intent(mContext,LoanReciever.class);
        myIntent.putExtra(LOAN_ID_HANDLER,loan.getLoan_id());
        myIntent.setAction("com.project.action.ALARM");
        PendingIntent pendingintent= PendingIntent.getBroadcast(mContext, loan.getLoan_id(),myIntent,0);
        if(alarmManager!=null)
        {
            if(pendingintent!=null)
            {
                pendingintent.cancel();
            }
        }
    }
}
