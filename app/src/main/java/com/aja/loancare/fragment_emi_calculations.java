package com.aja.loancare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.LoginFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.text.RuleBasedNumberFormat;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.Locale;

public class fragment_emi_calculations extends Fragment {
    SeekBar loan_seekbar;
    EditText loanamt;
    SeekBar interest_seekbar;
    EditText interestamt;
    SeekBar tenure_Seekbar;
    boolean inedittext=false;
    EditText tenure;
    double p_amt;
    double i_r;
    int t;
    double emi_amt;
    Button emi_calculate;
    boolean calculate=false;
    TextView emiresult;
    int finalamount;

    int stepSize;
    double si_r=0;
    double sp_amt=0;
    int st;
    double carry=0;
    double semi_amt=0;
    View v;
    NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));

    private static final String TAG = "emi_calculator";

    ToggleButton year_month;
    String currency_code;
    String currency_name;
    String currency_symbol;
    String tcurrency_symbol;
    String currency_symbol_from;
    TextView pie_pamt, pie_ir, pie_tenure, pie_emi;
    PieChart pieChart;
    TextView emi_amount;
    TextView inwords_emi;
    java.util.Currency mCurrency;
    String tenure_type;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_emi, container, false);
        year_month=v.findViewById(R.id.year_month);
        loan_seekbar=v.findViewById(R.id.loan_seekbar);
        loanamt=v.findViewById(R.id.loan_amt);
        interest_seekbar=v.findViewById(R.id.interest_seekbar);
        interestamt=v.findViewById(R.id.interest_rate);
        tenure_Seekbar=v.findViewById(R.id.tenure_seekbar);
        tenure=v.findViewById(R.id.tenure);
        pieChart = v.findViewById(R.id.piechart);
        emi_amount=v.findViewById(R.id.emi_amount);
        inwords_emi=v.findViewById(R.id.inwords_emi);
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getActivity());
        currency_code=String.valueOf(sharedPreferences.getString(fragment_settings.CURRENCY_CODE,"IN"));
         currency_name=String.valueOf(sharedPreferences.getString(fragment_settings.CURRENCY_NAME,"India"));
        tcurrency_symbol=String.valueOf(sharedPreferences.getString(fragment_settings.CURRENCY_SYMBOL,"₹"));
        Locale uk = new Locale("en", currency_code);
        mCurrency= java.util.Currency.getInstance(new Locale("en", currency_code));
        currency_symbol= mCurrency.getSymbol(uk);

      
        loan_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                java.text.NumberFormat formatter = java.text.NumberFormat.getCurrencyInstance(new Locale("en", currency_code));
//                String moneyString = formatter.format(Math.round(progress));
                loanamt.setText(moneyincommas(seekBar.getProgress()));
//                Log.i(TAG, "onProgressChanged: false inedittext + Pricipal amount " +moneyString );
                inedittext=false;

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.i(TAG, "onStartTrackingTouch: false inedittext");
                inedittext=false;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", currency_code));
//                String moneysString = formatter.format(Math.round(seekBar.getProgress()));
//                Log.i(TAG, "onStopTrackingTouch: false inedittext +amount "+moneysString );
                loanamt.setText(moneyincommas(seekBar.getProgress()));
                p_amt=seekBar.getProgress();
                calculation();
                inedittext=false;

            }
        });

        interest_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                interestamt.setText((String.valueOf(seekBar.getProgress())));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                interestamt.setText((String.valueOf(seekBar.getProgress())));
                i_r=seekBar.getProgress();
                calculation();

            }
        });

        tenure_Seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tenure.setText((String.valueOf(seekBar.getProgress())));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tenure.setText((String.valueOf(seekBar.getProgress())));
                t=seekBar.getProgress();
                calculation();
            }
        });
        interestamt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    if (Integer.parseInt(String.valueOf(s)) > 0 && Integer.parseInt(String.valueOf(s)) < 100) {
                        calculation();
                    } else {
                        Toast.makeText(getActivity(), "Invalid Rate", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        tenure.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!TextUtils.isEmpty(s)) {
                    if (Integer.parseInt(String.valueOf(s)) > 0 && Integer.parseInt(String.valueOf(s)) < 100) {
                        calculation();
                    } else {
                        Toast.makeText(getActivity(), "Invalid Tenure", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        loanamt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s)
            {

                if(!TextUtils.isEmpty(s))
                {
                    Log.i(TAG, "Loan amount calculation: ineittext"+inedittext+"Value "+s.toString() +" " +currency_symbol);
                    calculation();
                    Log.i(TAG, "afterTextChanged: "+s.toString());
                    double d=ValueFromEdittext(s.toString());
                    Log.i(TAG, "Loan amount double calculation: ineittext"+inedittext+"Value "+p_amt +" " +currency_symbol+" "+d);
                    double amount = Math.round(d);
                    if (amount > 100000000) {
                        loanamt.getText().clear();
                    }
                }

            }
        });

        loanamt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inedittext=true;
            }
        });

        return v;
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
//        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", currency_code));
//        String moneyString = formatter.format(Math.round(emi));
        inwords_emi.setText(english);
        emi_amount.setText(moneyincommas(Math.round(emi)));
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

    public void calculation()
    {
        p_amt=0;
        i_r=0;
        t=0;
        emi_amt=0;

        if(!TextUtils.isEmpty(loanamt.getText()) )
        {
            Log.i(TAG, "calculation: ineittext"+inedittext+"Value "+p_amt);
            if(inedittext)
           {
               p_amt=ValueFromEdittext(loanamt.getText().toString());


           }
            else
           {
               p_amt=ValueFromEdittext(loanamt.getText().toString());
           }
            calculate=true;
        }
        else
        {
            calculate=false;
        }
        if(!TextUtils.isEmpty(interestamt.getText()))
        {

            i_r=Integer.parseInt(interestamt.getText().toString());
            calculate=true;
        }
        else {
            Log.i(TAG, "onCreateView: inside interest calculate");
            calculate=false;

        }
        if(!TextUtils.isEmpty(tenure.getText()))
        {

            t=Integer.parseInt(tenure.getText().toString());
            if(t>0) {
                calculate = true;
            }
            else
            {
                calculate=false;
            }
        }
        else {
            Log.i(TAG, "onCreateView: inside tenure calculate");
            calculate=false;
        }
        if(calculate)
        {
            Log.i(TAG, "onCreateView: inside calculate");
             si_r=i_r;
             sp_amt=p_amt;
            st = t;
             i_r=i_r/1200;
             carry=1 + i_r;
             emi_amt = (p_amt * i_r * Math.pow(carry,t))/(Math.pow(carry,t)-1);
             Log.i(TAG, "onClick: emi"+emi_amt);
             semi_amt=emi_amt;
             update_data(sp_amt,st,si_r,semi_amt);
        }
    }

    public  String moneyincommas(double d)
    {
       double amount=d;
        String moneyString;
        String currency_code_value_inmoney;
        String currency_symbol_value_inmoney;
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getActivity());
        currency_symbol_value_inmoney=String.valueOf(sharedPreferences.getString(fragment_settings.CURRENCY_SYMBOL,"India"));
        currency_code_value_inmoney=String.valueOf(sharedPreferences.getString(fragment_settings.CURRENCY_CODE,"INR"));
        if(currency_symbol_value_inmoney.length()==3 && currency_symbol_value_inmoney.matches("[a-zA-Z]*"))
        {
            java.text.NumberFormat formatter = java.text.NumberFormat.getCurrencyInstance();


            Log.i(TAG, "moneyincommas: Code "+currency_symbol_value_inmoney+" amount "+d);
            formatter.setCurrency(java.util.Currency.getInstance(currency_symbol_value_inmoney));
            moneyString = formatter.format(amount);
        }
        else
        {
            moneyString = NumberFormat.getCurrencyInstance(new Locale("en", currency_code_value_inmoney)).format(amount);
        }

        return moneyString;
    }
    public double ValueFromEdittext(String s)
    {
        double Value;
        String currency_code_value;
        String currency_name_value;
        String tcurrency_symbol_value;
        java.util.Currency mCurrency_value;
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getActivity());
        currency_code_value=String.valueOf(sharedPreferences.getString(fragment_settings.CURRENCY_CODE,"INR"));
        currency_name_value=String.valueOf(sharedPreferences.getString(fragment_settings.CURRENCY_NAME,"India"));
        tcurrency_symbol_value=String.valueOf(sharedPreferences.getString(fragment_settings.CURRENCY_SYMBOL,"India"));
        Locale uk = new Locale("en", currency_code);
        mCurrency_value= java.util.Currency.getInstance(new Locale("en", currency_code));
        currency_symbol= mCurrency_value.getSymbol(uk);
        Log.i(TAG, "ValueFromEdittext: value"+s.toString());
        if(!TextUtils.isEmpty(s))
        {
            String replace=s.replaceAll("[^\\d.]", "");
            Log.i(TAG, "ValueFromEdittext: replace"+replace.toString());
            Value = Double.parseDouble(replace);

        }
        else
        {
            Value=0;
        }
        return Value;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}