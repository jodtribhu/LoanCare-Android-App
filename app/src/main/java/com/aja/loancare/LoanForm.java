package com.aja.loancare;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class LoanForm extends Activity implements View.OnClickListener,DatePickerDialog.OnDateSetListener {
    EditText txtDate,principle,interest,duration;
    Spinner bank,loan;
    Button submit;
    String bankName,loanType,date,dateInString;
    private int mYear, mMonth, mDay;
    int durationVal,durationEnd;
    Intent i;
    Float principleVal,interestVal;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_form);

        // Spinner element
        principle=findViewById(R.id.principle);
        interest=findViewById(R.id.interest);
        duration=findViewById(R.id.duration);
        loan = (Spinner) findViewById(R.id.spinnerLoan);
        loan.setOnItemSelectedListener(new OnItemSelectedListener() {
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
        bank.setOnItemSelectedListener(new OnItemSelectedListener() {
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



        txtDate=(EditText)findViewById(R.id.in_date);
        submit=findViewById(R.id.Button_loan);
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
            if(txtDate.getText().toString()!=null) {
                Calendar c = Calendar.getInstance();
                durationEnd = Integer.parseInt(duration.getText().toString());
                dateInString = txtDate.getText().toString();  // Start date
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    c.setTime(sdf.parse(dateInString));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                c.add(Calendar.DATE, durationEnd*30);  // add days
                sdf = new SimpleDateFormat("MM/dd/yyyy");

                Date resultdate = new Date(c.getTimeInMillis());   // Get new time
                dateInString = sdf.format(resultdate);
                Toast.makeText(getApplicationContext(), dateInString, Toast.LENGTH_SHORT).show();
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
                i.putExtra("principle", String.valueOf(principleVal));
                i.putExtra("interest", String.valueOf(interestVal));
                i.putExtra("duration", String.valueOf(durationVal));
                i.putExtra("date", date);
                i.putExtra("bankname",bankName);
                i.putExtra("loantype", loanType);
                i.putExtra("enddate",dateInString );

                Toast.makeText(this, "onClick: Log data "+principleVal+interestVal+durationVal, Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK,i);
                finish();

            }
        }
    }


    @Override
    public void onDateSet(DatePicker datePicker, int dayOfMonth, int monthOfYear, int year) {
        txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1)
                + "-" + year);
    }
}