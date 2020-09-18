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
import android.widget.Toast;

import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.text.RuleBasedNumberFormat;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.Locale;

public class fragment_emi_graph extends Fragment {
    TextView pie_pamt, pie_ir, pie_tenure, pie_emi;
    PieChart pieChart;
    TextView emi_amount;
    TextView inwords_emi;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_emi_graph, container, false);
        pieChart = v.findViewById(R.id.piechart);
        emi_amount=v.findViewById(R.id.emi_amount);
        inwords_emi=v.findViewById(R.id.inwords_emi);
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
    private String convertIntoWords(Double str,String language,String Country) {
        Locale local = new Locale(language, Country);
        RuleBasedNumberFormat ruleBasedNumberFormat = new RuleBasedNumberFormat(local, RuleBasedNumberFormat.SPELLOUT);
        return ruleBasedNumberFormat.format(str);
    }
    private void setData(double principle,double interest ,int tenure,double emi)
    {
        emi=Math.round(emi);
        String prin=Double.toString(principle);
        String intre=Double.toString(interest);
        String tenu=Integer.toString(tenure);
        String amt=Double.toString(emi);

        String english=Currency.convertToIndianCurrency(String.valueOf(Math.round(emi)));
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
        String moneyString = formatter.format(Math.round(emi));
        inwords_emi.setText(english);
        emi_amount.setText(moneyString);
        double totalinterest=tenure*emi;

        pieChart.clearChart();
        pieChart.addPieSlice(
                new PieModel(
                        "Principal Amount",
                        (float) principle,
                        Color.parseColor("#ffce08")));
        pieChart.addPieSlice(
                new PieModel(
                        "Interest",
                        (int)totalinterest,
                        Color.parseColor("#b42e8c")));
        pieChart.startAnimation();
    }
}