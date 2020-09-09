package com.aja.loancare;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class piemain  extends AppCompatActivity{
    TextView pie_pamt, pie_ir, pie_tenure, pie_emi;
    PieChart pieChart;
    private static final String TAG = "piemain";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pie);
        pie_pamt = findViewById(R.id.pie_pamt);
        pie_ir = findViewById(R.id.pie_ir);
        pie_tenure = findViewById(R.id.pie_tenure);
        pie_emi = findViewById(R.id.pie_emi);
        pieChart = findViewById(R.id.piechart);

        Intent intent=getIntent();
        double principal=intent.getDoubleExtra(emi_calculator.EMI_PRINCIPAL,0);
        double interest=intent.getDoubleExtra(emi_calculator.EMI_INTEREST,0);
        int tenure=intent.getIntExtra(emi_calculator.EMI_TENURE,0);
        double amount=intent.getDoubleExtra(emi_calculator.EMI_AMOUNT,0);
        Log.i(TAG, "Emi: "+principal+" "+interest+" "+tenure+" "+amount);
        setData(principal,interest,tenure,amount);
    }
    private void setData(double principle,double interest ,int tenure,double amount)
    {
        amount=Math.round(amount);
        String prin=Double.toString(principle);
        String intre=Double.toString(interest);
        String tenu=Integer.toString(tenure);
        String amt=Double.toString(amount);
        pie_pamt.setText(prin);
        pie_ir.setText(intre);
        pie_tenure.setText(tenu);
        pie_emi.setText(amt);
        double totalinterest=tenure*amount;
        pieChart.addPieSlice(
                new PieModel(
                        "Principal Amount",
                        (float) principle,
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Interest",
                        (int)totalinterest,
                        Color.parseColor("#800000")));


        pieChart.startAnimation();
    }
}
