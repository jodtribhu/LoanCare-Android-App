package com.aja.loancare;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class changeloan extends Activity implements View.OnClickListener, DatePickerDialog.OnDateSetListener  {
    EditText txtDate,principle,interest,duration;
    Spinner bank,loan;
    Button submit;
    String bankName,loanType,date;
    private int mYear, mMonth, mDay;
    int durationVal;
    Intent i;
    Float principleVal,interestVal;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_loan);

        // Spinner element
        principle=findViewById(R.id.principle);
        interest=findViewById(R.id.interest);
        duration=findViewById(R.id.duration);
        txtDate=(EditText) findViewById(R.id.in_date);
        loan = (Spinner) findViewById(R.id.spinnerLoan);
        loan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loanType = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),loanType,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        bank = (Spinner) findViewById(R.id.spinnerBank);
        bank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bankName = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),bankName,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        i=getIntent();
         principle.setHint(i.getExtras().getString("principal"));
         interest.setHint(i.getExtras().getString("interest"));
         duration.setHint(i.getExtras().getString("duration"));
         txtDate.setHint(i.getExtras().getString("date"));
         submit=findViewById(R.id.Button_loan);
        submit.setOnClickListener(this);
        txtDate.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == txtDate) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog
                    (this, this, mYear, mMonth, mDay);
            datePickerDialog.show();

        }
        if (v == submit){
            boolean error=false;
            String errMsg="Enter ";
            if(principle.getText().toString().matches("")){
                error=true;
                errMsg+="principle; ";
            }
            else {
                principleVal = Float.valueOf(principle.getText().toString());
            }
            if(interest.getText().toString().matches("")){
                error=true;
                errMsg+="interest; ";
            }
            else {
                interestVal = Float.valueOf(interest.getText().toString());
            }
            if(duration.getText().toString().matches("")){
                error=true;
                errMsg+="duration; ";
            }
            else {
                durationVal = Integer.parseInt(duration.getText().toString());

            }
            if(txtDate.getText().toString().matches("")){
                error=true;
                errMsg+="date; ";
            }
            else {
                date = txtDate.getText().toString();
            }
            if(bankName=="-"){
                errMsg+="Bank; ";
            }
            if(loanType=="-"){
                errMsg+="Loan type; ";
            }

            if(error==true) {
                Toast.makeText(getApplicationContext(),errMsg,Toast.LENGTH_LONG).show();
            }
            else {
                //set data change

            }
        }
    }


    @Override
    public void onDateSet(DatePicker datePicker, int dayOfMonth, int monthOfYear, int year) {
        txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1)
                + "-" + year);
    }
}
