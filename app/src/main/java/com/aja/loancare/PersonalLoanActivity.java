package com.aja.loancare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ScrollView;

public class PersonalLoanActivity extends AppCompatActivity {
    private ScrollView layMain;
    private tablegenerator mTable;
   int p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_loan);
        Intent i =getIntent();
        p=i.getIntExtra("position",0);
        showTable();
    }
    private void showTable() {
        if (p==0){
        mTable = new tablegenerator(getApplicationContext());
        layMain = findViewById(R.id.table);

        String[] firstRow = {"EMI#", "EMI", "Principal", "Interest","Balance"};
        String[] secondRow = {"1", "12", "13", "14","15"};
        String[] thirdRow = {"2", "17", "18", "19","20"};

        mTable.addRow(firstRow);
        mTable.addRow(secondRow);
        mTable.addRow(thirdRow);

        layMain.removeAllViews();
        layMain.addView(mTable.getTable());}
        if (p==1){
            mTable = new tablegenerator(getApplicationContext());
            layMain = findViewById(R.id.table);

            String[] firstRow = {"EMI#", "EMI", "Principal", "Interest","Balance"};
            String[] secondRow = {"1", "12", "13", "14","15"};
            String[] thirdRow = {"2", "17", "18", "19","20"};

            mTable.addRow(firstRow);
            mTable.addRow(secondRow);
            mTable.addRow(thirdRow);

            layMain.removeAllViews();
            layMain.addView(mTable.getTable());
        }
        if (p==2){
            mTable = new tablegenerator(getApplicationContext());
            layMain = findViewById(R.id.table);

            String[] firstRow = {"EMI#", "EMI", "Principal", "Interest","Balance"};
            String[] secondRow = {"1", "12", "13", "14","15"};
            String[] thirdRow = {"2", "17", "18", "19","20"};

            mTable.addRow(firstRow);
            mTable.addRow(secondRow);
            mTable.addRow(thirdRow);

            layMain.removeAllViews();
            layMain.addView(mTable.getTable());
        }
        if (p==3){
            mTable = new tablegenerator(getApplicationContext());
            layMain = findViewById(R.id.table);

            String[] firstRow = {"EMI#", "EMI", "Principal", "Interest","Balance"};
            String[] secondRow = {"1", "12", "13", "14","15"};
            String[] thirdRow = {"2", "17", "18", "19","20"};

            mTable.addRow(firstRow);
            mTable.addRow(secondRow);
            mTable.addRow(thirdRow);

            layMain.removeAllViews();
            layMain.addView(mTable.getTable());
        }
    }
}