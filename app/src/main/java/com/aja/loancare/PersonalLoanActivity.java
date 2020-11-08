package com.aja.loancare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ScrollView;

public class PersonalLoanActivity extends AppCompatActivity {
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
        p=i.getFloatExtra("principle",0);
        r=i.getFloatExtra("interest",0);
        d=i.getIntExtra("duration",0);
        prg=i.getIntExtra("progress",0);
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