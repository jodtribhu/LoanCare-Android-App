package com.aja.loancare;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.aja.loancare.mvvmmodel.Loan;

import java.util.Calendar;

public class LoanReciever  extends BroadcastReceiver {
    private static final String TAG = "LoanReciever";
    private final String CHANNEL_ID=" com.aja.loancare.LoanRecieverNewChannel";
    int loan_id;
    String bank_name;
    String Loantype;
    int interest_amt;


    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager=(NotificationManager)context.getSystemService((context.NOTIFICATION_SERVICE));
        Toast.makeText(context, "It is working", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onReceive: Inside reciever");
        loan_id=intent.getIntExtra(LoanHandler.LOAN_ID_HANDLER,0);
        bank_name=intent.getStringExtra(LoanHandler.LOAN_BANK_NAME);
        interest_amt=intent.getIntExtra(LoanHandler.LOAN_INTEREST_RATE,0);
        Loantype=intent.getStringExtra(LoanHandler.LOAN_TYPE);

        if(notificationManager!=null)
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel alarmchannel = new NotificationChannel(CHANNEL_ID, TAG, NotificationManager.IMPORTANCE_HIGH);
                alarmchannel.enableLights(true);
                alarmchannel.setLightColor(Color.RED);
                notificationManager.createNotificationChannel(alarmchannel);
            }
        }
        Notification notification=buildnotification(context,getstopintent(context,loan_id),bank_name,interest_amt,Loantype);
        if(notificationManager!=null)
        {
            notificationManager.notify(1,notification);
        }

    }

    private PendingIntent getstopintent(Context context,int loan_id)
    {
        Intent stopIntent=new Intent(context,LoanStopReciever.class);
        stopIntent.putExtra(fragment_loanList.LOAN_RECIEVER_ID,loan_id);
        return PendingIntent.getBroadcast(context,loan_id,stopIntent,PendingIntent.FLAG_UPDATE_CURRENT);
    }




    public Notification buildnotification(Context context, PendingIntent StopIntent,String bank_name,int interest,String Loantype)
    {
        Calendar calender=Calendar.getInstance();
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context, CHANNEL_ID);
        NotificationCompat.Action stopaction = new NotificationCompat.Action.Builder(R.drawable.money_icon, "PAID",
                StopIntent).build();
        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(),R.drawable.logo);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            notification.setDefaults(Notification.DEFAULT_ALL).setLargeIcon(largeIcon)
                    .setStyle(new NotificationCompat.InboxStyle()
                    .addLine("Bank Name : "+bank_name).addLine("Interest Amount To pay "+interest).addLine("Loan Type : "+Loantype)
                    )
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.small_icon_bank)
                    .setContentTitle(context.getString(R.string.app_name))
                    .setContentText("Time to payback the Loan")
                    .addAction(stopaction);
            notification.setColor((ContextCompat.getColor(context,R.color.notification_color)));
        }
        else
        {

            notification.setDefaults(Notification.DEFAULT_ALL)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setWhen(System.currentTimeMillis()).setLargeIcon(largeIcon)
                    .setStyle(new NotificationCompat.InboxStyle()
                            .addLine("Bank Name : "+bank_name).addLine("Interest Amount To pay "+interest).addLine("Loan Type : "+Loantype)
                    )
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(context.getString(R.string.app_name))
                    .setContentText("Time to payback the Loan")
                    .addAction(stopaction);
        }
        notification.setOngoing(true);
        return notification.build();
    }
}
