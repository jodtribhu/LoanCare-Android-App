package com.aja.loancare;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class fragment_emi_graph extends Fragment {
    TextView pie_pamt, pie_ir, pie_tenure, pie_emi;
    PieChart pieChart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_emi_graph, container, false);
        pieChart = v.findViewById(R.id.piechart);
        return v ;
    }

    public void update_data(double s_principal,int s_tenure,double s_rate,double s_emi)
    {

        double principal=s_principal;
        double interest=s_rate;
        int tenure=s_tenure;
        double emi=s_emi;
        setData(principal,interest,tenure,emi);
    }
    private void setData(double principle,double interest ,int tenure,double emi)
    {
        emi=Math.round(emi);
        String prin=Double.toString(principle);
        String intre=Double.toString(interest);
        String tenu=Integer.toString(tenure);
        String amt=Double.toString(emi);
        double totalinterest=tenure*emi;
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