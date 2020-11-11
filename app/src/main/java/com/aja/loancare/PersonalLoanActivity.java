package com.aja.loancare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.Toast;

public class PersonalLoanActivity extends AppCompatActivity {
    public static String PersonalLoanActivity_PRINCIPLE="com.aja.loancare.PRINCIPLE";
    public static String PersonalLoanActivity_INTEREST="com.aja.loancare.INTEREST";
    public static String PersonalLoanActivity_DURATION="com.aja.loancare.DURATION";
    public static String PersonalLoanActivity_PROGRESS="com.aja.loancare.PROGRESS";
    private ScrollView layMain;
    private tablegenerator mTable;
    Float p,r,carry;
    double emi,temi;
    Integer d,prg,x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_loan);
        Intent i =getIntent();
        p=i.getFloatExtra(PersonalLoanActivity_PRINCIPLE,0);
        r=i.getFloatExtra(PersonalLoanActivity_INTEREST,0);
        d=i.getIntExtra(PersonalLoanActivity_DURATION,0);
        prg=i.getIntExtra(PersonalLoanActivity_DURATION,0);
        r=r/1200;
        carry=1+r;
        emi=(int)Math.round((p * r * Math.pow(carry,d))/(Math.pow(carry,d)-1));
        x=(int)Math.round((prg * 0.01 )*d);
        temi=p;
        showTable();
    }
    private void showTable() {
        mTable = new tablegenerator(getApplicationContext());
        layMain = findViewById(R.id.table);
        String[] firstRow = {"EMI#", "EMI","Balance"};
        mTable.addRow(firstRow);
        for (int i=1;i<=x;i++){
             temi=temi-emi;
        mTable.addRow(new String[]{String.valueOf(i),String.valueOf(emi),String.valueOf(temi)});}
        layMain.removeAllViews();
        layMain.addView(mTable.getTable());

    }
}