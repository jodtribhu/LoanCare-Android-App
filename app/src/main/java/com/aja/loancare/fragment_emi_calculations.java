package com.aja.loancare;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class fragment_emi_calculations extends Fragment {
    public  static String EMI_PRINCIPAL="com.aja.loancare.principalamount";
    public static String EMI_INTEREST="com.aja.loancare.interest";
    public static String EMI_TENURE="com.aja.loancare.tenure";
    public static String EMI_AMOUNT="com.aja.loancare.amount";
    EditText principal_amount;
    EditText interest_rate;
    EditText tenure;
    double p_amt;
    double i_r;
    int t;
    double emi_amt;
    Button emi_calculate;
    boolean calculate=false;
    TextView emiresult;
    private static final String TAG = "emi_calculator";
    emi_calculatorlistner listner;

    public interface emi_calculatorlistner
    {
        void onInputCalcSent(double principal,int tenure,double rate,double emi);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_emi_calculations, container, false);;

        principal_amount=v.findViewById(R.id.principal_amount);
        interest_rate=v.findViewById(R.id.interest_rate);
        emi_calculate=v.findViewById(R.id.emi_button);
        tenure=v.findViewById(R.id.tenure);
        interest_rate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null) {
                    if(s.toString()!="") {
                        int currentno = Integer.parseInt(s.toString());
                        if (currentno > 100) {
                            Toast.makeText(getActivity(), "Invalid Interest Rate", Toast.LENGTH_SHORT).show();
                            interest_rate.setText("0");
                        }
                    }
                }
            }
        });

        emi_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(principal_amount.getText() != null)
                {
                    p_amt=Integer.parseInt(principal_amount.getText().toString());
                    calculate=true;
                }
                else
                {
                    calculate=false;
                }
                if(interest_rate.getText() != null)
                {
                    i_r=Integer.parseInt(interest_rate.getText().toString());
                    calculate=true;
                }
                else
                {
                    calculate=false;
                }
                if(tenure.getText() != null)
                {
                    t=Integer.parseInt(tenure.getText().toString());
                    calculate=true;
                }
                else
                {
                    calculate=false;
                }
                if(calculate)
                {



                    double si_r=i_r;
                    double sp_amt=p_amt;
                    int st=t;
                    i_r=i_r/1200;
                    double carry=1 + i_r;
                    emi_amt = (p_amt * i_r * Math.pow(carry,t))/(Math.pow(carry,t)-1);
                    Log.i(TAG, "onClick: emi"+emi_amt);

                    double semi_amt=emi_amt;
                    listner.onInputCalcSent(sp_amt,st,si_r,semi_amt);
                }
            }
        });
        return v;
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