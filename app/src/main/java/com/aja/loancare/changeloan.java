package com.aja.loancare;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class changeloan extends Activity implements View.OnClickListener, DatePickerDialog.OnDateSetListener  {
    public static String EDIT_LOAN_PRINCIPLE="com.aja.loancare.PRINCIPLE";
    public static String EDIT_LOAN_INTEREST="com.aja.loancare.INTEREST";
    public static String EDIT_LOAN_DURATION="com.aja.loancare.DURATION";
    public static String EDIT_LOAN_DATE="com.aja.loancare.DATE";
    public static String EDIT_LOAN_BANKNAME="com.aja.loancare.BANKNAME";
    public static String EDIT_LOAN_LOANTYPE="com.aja.loancare.LOANTYPE";
    private static final String TAG = "changeloan";
    EditText txtDate,principle,interest,duration;
    Spinner bank,loan;
    Button submit;
    String bankName,loanType,date,dateInString,currentDate;
    private int mYear, mMonth, mDay;
    int durationVal,monthDifference;
    int editid;
    Float principleVal,interestVal;
    Date date1;
    Date date2;
    long differenceDates,difference;
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


        Intent in=getIntent();
        principle.setText(String.valueOf(in.getFloatExtra(EDIT_LOAN_PRINCIPLE,0)));
        interest.setText(String.valueOf(in.getFloatExtra(EDIT_LOAN_INTEREST,0)));
        duration.setText(String.valueOf(in.getIntExtra(EDIT_LOAN_DURATION,0)));
        txtDate.setText(in.getStringExtra(EDIT_LOAN_DATE));
        editid=in.getIntExtra("IDd",0);

        submit=findViewById(R.id.Button_loan);
        loan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loanType = parent.getItemAtPosition(position).toString();
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit.setOnClickListener(this);
        txtDate.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == txtDate) {
            Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog
                    (this, this, mYear, mMonth, mDay);
            datePickerDialog.show();

            //

        }
        if (v == submit){
            Date d = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
            currentDate = df.format(d);
            if(txtDate.getText().toString()!=null) {
                Calendar c = Calendar.getInstance();
                if(duration.getText().toString().matches("") && txtDate.getText().toString().matches("")) {
                    //
                }
                else {
                    dateInString = txtDate.getText().toString();  // Start date
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd",Locale.ENGLISH);
                    try {
                        c.setTime(sdf.parse(dateInString));
                    } catch (ParseException e) {

                    }

                    c.add(Calendar.MONTH, durationVal );  // add MONTHS
                    sdf = new SimpleDateFormat("yyyy/MM/dd");

                    Date resultdate = new Date(c.getTimeInMillis());   // Get new time
                    dateInString = sdf.format(resultdate);

                }
            }
            else{
                Toast.makeText(getApplicationContext(), "err", Toast.LENGTH_SHORT).show();
            }
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
                error=true;
                errMsg+="Bank; ";
            }
            if(loanType=="-"){
                error=true;
                errMsg+="Loan type; ";
            }

            if(error==true) {
                Toast.makeText(getApplicationContext(),errMsg,Toast.LENGTH_LONG).show();
            }
            else {
                try {
                    date1=new SimpleDateFormat("yyyy/MM/dd").parse(currentDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    date2=new SimpleDateFormat("yyyy-MM-dd").parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                difference = date1.getTime() - date2.getTime();
                differenceDates = difference / (24 * 60 * 60 * 1000);
                monthDifference =(int)((differenceDates)/30);
                Log.i(TAG, "onClick: monthdifferene "+monthDifference);

                Log.i(TAG, "onClick: principleVal"+principleVal);
                Intent i=getIntent();
                i.putExtra("principle", String.valueOf(principleVal));
                i.putExtra("interest", String.valueOf(interestVal));
                i.putExtra("duration", String.valueOf(durationVal));
                i.putExtra("date", date);
                i.putExtra("bankname",bankName);
                i.putExtra("loantype", loanType);
                i.putExtra("pastmonths",monthDifference);
                i.putExtra("Id",editid);
                setResult(RESULT_OK,i);
                finish();

            }
        }
    }


    @Override
    public void onDateSet(DatePicker datePicker, int dayOfMonth, int monthOfYear, int year) {
        txtDate.setText(dayOfMonth  + "-" + (monthOfYear + 1)
                + "-" + year);
    }
}