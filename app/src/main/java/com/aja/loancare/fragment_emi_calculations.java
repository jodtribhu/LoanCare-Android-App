package com.aja.loancare;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
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

import com.ibm.icu.text.NumberFormat;

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

    double si_r=0;
    double sp_amt=0;
    int st;
    double carry=0;
    double semi_amt=0;

    private static final String TAG = "emi_calculator";
    emi_calculatorlistner listner;

    public interface emi_calculatorlistner
    {
        void onInputCalcSent(double principal,int tenure,double rate,double emi);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_emi_calculations, container, false);

        loan_seekbar=v.findViewById(R.id.loan_seekbar);
        loanamt=v.findViewById(R.id.loan_amt);
        interest_seekbar=v.findViewById(R.id.interest_seekbar);
        interestamt=v.findViewById(R.id.interest_rate);
        tenure_Seekbar=v.findViewById(R.id.tenure_seekbar);
        tenure=v.findViewById(R.id.tenure);

loanamt.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        inedittext=true;
        loanamt.getText().clear();
    }
});
        loan_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
                String moneyString = formatter.format(Math.round(progress));
                loanamt.setText(moneyString);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                inedittext=false;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
                String moneysString = formatter.format(Math.round(seekBar.getProgress()));
                loanamt.setText(moneysString);
                p_amt=seekBar.getProgress();
                calculation();
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
        loanamt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                if(!TextUtils.isEmpty(s.toString()) && inedittext)
//                {
//                    int amt=Integer.parseInt(s.toString());
//                    if(amt>100000000)
//                    {
//                        loanamt.getText().clear();
//                    }
//                    else
//                    {
//                        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
//                        String moneysString = formatter.format(Math.round(amt));
//                        Toast.makeText(get, "", Toast.LENGTH_SHORT).show();
//                        formatter.format(moneysString);
//                        loanamt.setText(moneysString);
//                    }
//                }

            }
        });
        return v;
    }
    public void calculation()
    {
        p_amt=0;
        i_r=0;
        t=0;
        emi_amt=0;

        if(!TextUtils.isEmpty(loanamt.getText()))
        {


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
            calculate=true;
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
             st=t;
             i_r=i_r/1200;
             carry=1 + i_r;
             emi_amt = (p_amt * i_r * Math.pow(carry,t))/(Math.pow(carry,t)-1);
             Log.i(TAG, "onClick: emi"+emi_amt);
             semi_amt=emi_amt;
            listner.onInputCalcSent(sp_amt,st,si_r,semi_amt);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof emi_calculator) {
            listner = (emi_calculatorlistner) context;
        }else
        {
            throw new RuntimeException(context.toString()+"must implement emi_listner");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listner=null;
    }
}