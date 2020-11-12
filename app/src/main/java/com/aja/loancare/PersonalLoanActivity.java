package com.aja.loancare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.Toast;

public class PersonalLoanActivity extends AppCompatActivity {
    public static String PersonalLoanActivity_PRINCIPLE="com.aja.loancare.PRINCIPLE";
    public static String PersonalLoanActivity_INTEREST="com.aja.loancare.INTEREST";
    public static String PersonalLoanActivity_DURATION="com.aja.loancare.DURATION";
    public static String PersonalLoanActivity_PROGRESS="com.aja.loancare.PROGRESS";
    public static String PersonalLoanActivity_PAID_MONTHS="com.aja.loancare.PAID_MONTHS";
    private ScrollView layMain;
    private tablegenerator mTable;
    Float p,r,carry;
    double emi,temi;
    Integer d,prg,x;
    int pmonths;
    private static final String TAG = "PersonalLoanActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_loan);
        Intent i =getIntent();
        p=i.getFloatExtra(PersonalLoanActivity_PRINCIPLE,0);
        r=i.getFloatExtra(PersonalLoanActivity_INTEREST,0);
        d=i.getIntExtra(PersonalLoanActivity_DURATION,0);
        prg=i.getIntExtra(PersonalLoanActivity_PROGRESS,0);
        pmonths=i.getIntExtra(PersonalLoanActivity_PAID_MONTHS,0);
        r=r/1200;
        carry=1+r;;
        Log.i(TAG, "onCreate: inside p "+p+" r "+r+" d "+d+" prg "+prg+ "pmonths "+pmonths);
        double numerator = Math.pow(carry,d);
        double denominator=Math.pow(carry,d)-1;
        double num = numerator / denominator;
        emi=(int)Math.round(p * r * num);
        x=pmonths;
        temi=p;
        showTable();
    }
    private void showTable() {
        mTable = new tablegenerator(getApplicationContext());
        layMain = findViewById(R.id.table);
        String[] firstRow = {"EMI#", "EMI","Balance"};
        mTable.addRow(firstRow);
        for (int i=0;i<x;i++){
             temi=temi-emi;
        mTable.addRow(new String[]{String.valueOf(i+1),String.valueOf(emi),String.valueOf(temi)});}
        layMain.removeAllViews();
        layMain.addView(mTable.getTable());

    }
}