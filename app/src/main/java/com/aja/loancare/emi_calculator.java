package com.aja.loancare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class emi_calculator extends AppCompatActivity {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emi_calculator);
        principal_amount=findViewById(R.id.principal_amount);
        interest_rate=findViewById(R.id.interest_rate);
        emi_calculate=findViewById(R.id.emi_button);
        tenure=findViewById(R.id.tenure);
        emiresult=findViewById(R.id.result_emi);
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
                            Toast.makeText(emi_calculator.this, "Invalid Interest Rate", Toast.LENGTH_SHORT).show();
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
                   Intent intent=new Intent(emi_calculator.this,piemain.class);

                    intent.putExtra(EMI_INTEREST,i_r);
                    intent.putExtra(EMI_PRINCIPAL,p_amt);
                    intent.putExtra(EMI_TENURE,t);
                    i_r=i_r/1200;
                    double carry=1 + i_r;
                    emi_amt = (p_amt * i_r * Math.pow(carry,t))/(Math.pow(carry,t)-1);
                    Log.i(TAG, "onClick: emi"+emi_amt);
                    emiresult.setText(String.valueOf(Math.round(emi_amt)));
                    intent.putExtra(EMI_AMOUNT,emi_amt);
                    startActivity(intent);
                }
            }
        });

    }
}