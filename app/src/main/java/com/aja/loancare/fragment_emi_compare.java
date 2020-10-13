package com.aja.loancare;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class fragment_emi_compare extends Fragment implements View.OnClickListener {

    EditText l1am,l2am,l1int,l2int,l1mon,l2mon;
    Button cal,reset;
    TextView emiL1,emiL2,int1,int2,ta1,ta2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_emi_compare, container, false);
        cal=v.findViewById(R.id.calulate);
        reset=v.findViewById(R.id.reset);
        // edittext id's
        l1am=v.findViewById(R.id.L1amount);
        l2am=v.findViewById(R.id.L2amount);
        l1int=v.findViewById(R.id.L1interest);
        l2int=v.findViewById(R.id.L2interest);
        l1mon=v.findViewById(R.id.L1month);
        l2mon=v.findViewById(R.id.L2month);
        // table id's
        emiL1=(TextView) v.findViewById(R.id.emi1);
        emiL2=(TextView) v.findViewById(R.id.emi2);
        int1=(TextView) v.findViewById(R.id.interest1);
        int2=(TextView) v.findViewById(R.id.interest2);
        ta1=(TextView) v.findViewById(R.id.TA1);
        ta2=(TextView) v.findViewById(R.id.TA2);
        cal.setOnClickListener(this);
        reset.setOnClickListener(this);
        return v;
    }
    @Override
    public void onClick(View view) {
        if(view==cal){

            //for getting values from edittext
            double principle1=0,principle2=0,interL1=0,interL2=0,monL1=0,monL2=0;

            //for calculating new values
            double emi1,emi2,totIn1,totIn2,totAm1,totAm2;
            boolean error=false;
            String errMsg="Enter ";

            //assigning values from input
            if(l1am.getText().toString().matches("")){
                error=true;
                errMsg+="Amount for loan 1; ";
            }
            else {
                principle1 = Float.valueOf(l1am.getText().toString());
            }
            if(l2am.getText().toString().matches("")){
                error=true;
                errMsg+="Amount for loan 2; ";
            }
            else {
                principle2 = Float.valueOf(l2am.getText().toString());
            }
            if(l1int.getText().toString().matches("")){
                error=true;
                errMsg+="Interest for loan 1; ";
            }
            else {
                interL1 = Float.valueOf(l1int.getText().toString());
            }
            if(l2int.getText().toString().matches("")){
                error=true;
                errMsg+="Interest for loan 2; ";
            }
            else {
                interL2 = Float.valueOf(l2int.getText().toString());
            }
            if(l1mon.getText().toString().matches("")){
                error=true;
                errMsg+="Month for loan 1; ";
            }
            else {
                monL1 = Integer.parseInt(l1mon.getText().toString());
            }
            if(l2mon.getText().toString().matches("")){
                error=true;
                errMsg+="Month for loan 2;";
            }
            else {
                monL2 = Integer.parseInt(l2mon.getText().toString());
            }

            //calculating new values
            if(error==true) {
                Toast.makeText(getActivity(),errMsg,Toast.LENGTH_LONG).show();
            }
            else {
                // emi - loan1
                interL1=interL1/(1200);
                double temp = (1 + interL1);
                double numerator = Math.pow(temp, monL1);
                double denominator=Math.pow(temp, monL1)-1;
                double num = numerator / denominator;
                emi1 = principle1 * interL1 * num;
                emiL1.setText("₹"+String.valueOf(new DecimalFormat("#,##,##,##,##,##0").format((int)emi1)));

                // emi - loan2

                interL2=interL2/(1200);
                temp = (1 + interL2);
                numerator = Math.pow(temp, monL2);
                denominator=Math.pow(temp, monL2)-1;
                num = numerator / denominator;
                emi2 = principle2 * interL2 * num;
                emiL2.setText("₹"+String.valueOf(new DecimalFormat("#,##,##,##,##,##0").format((int)emi2) +"\n\n"+ String.valueOf(new DecimalFormat("#,##,##,##,##,##0").format((int)(emi2-emi1)))));


                //total amount - 1,2
                totAm1=emi1*monL1;
                totAm2=emi2*monL2;
                ta1.setText("₹"+String.valueOf(new DecimalFormat("#,##,##,##,##,##0").format((int)totAm1)));
                String diff=String.valueOf((int)(totAm2-totAm1));
                SpannableString spannable = new SpannableString(diff);
                spannable.setSpan(new ForegroundColorSpan(Color.GREEN), 0, spannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                ta2.setText("₹"+String.valueOf(new DecimalFormat("#,##,##,##,##,##0").format((int)totAm2)) +"\n\n" + new DecimalFormat("#,##,##,##,##,##0").format((int)totAm2-totAm1));

                //total interest - 1,2
                totIn1=totAm1-principle1;
                totIn2=totAm2-principle2;
                int1.setText("₹"+String.valueOf(new DecimalFormat("#,##,##,##,##,##0").format((int)totIn1)));
                int2.setText("₹"+String.valueOf(new DecimalFormat("#,##,##,##,##,##0").format((int)totIn2))+"\n\n"+ (String.valueOf(new DecimalFormat("#,##,##,##,##,##0").format((int)(totIn2-totIn1)))));
            }

        }
        if(view==reset){
            //inside table
            emiL1.setText("-");
            emiL2.setText("-");
            ta1.setText("-");
            ta2.setText("-");
            int1.setText("-");
            int2.setText("-");
            //for edittext
            l1am.setText("");
            l2am.setText("");
            l1int.setText("");
            l2int.setText("");
            l1mon.setText("");
            l2mon.setText("");
        }
    }
}