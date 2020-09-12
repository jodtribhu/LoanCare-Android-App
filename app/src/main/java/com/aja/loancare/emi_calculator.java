package com.aja.loancare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;


public class emi_calculator extends AppCompatActivity implements fragment_emi_calculations.emi_calculatorlistner {
private fragment_emi_calculations mFragment_emi_calculations;
private fragment_emi_graph mFragment_emi_graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emi_calculator);
        mFragment_emi_calculations=new fragment_emi_calculations();
        mFragment_emi_graph=new fragment_emi_graph();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.emi_calculator,mFragment_emi_calculations).replace(R.id.emi_graph,mFragment_emi_graph).commit();

    }

    @Override
    public void onInputCalcSent(double principal, int tenure, double rate, double emi) {
        mFragment_emi_graph.update_data(0,0,0,0);
        mFragment_emi_graph.update_data(principal,tenure,rate,emi);
    }
}