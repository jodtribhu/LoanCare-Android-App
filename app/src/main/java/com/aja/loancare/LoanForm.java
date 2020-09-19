package com.aja.loancare;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class LoanForm extends Activity implements OnItemSelectedListener,View.OnClickListener,
        DatePickerDialog.OnDateSetListener{
    EditText txtDate;
    private int mYear, mMonth, mDay;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_form);
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> banks = new ArrayList<String>();
        banks.add("-");
        banks.add("SBI");
        banks.add("HDFC");
        banks.add("PNB");
        banks.add("Axis bank");
        banks.add("Canara");
        banks.add("Federal bank");
        banks.add("ICICI");
        banks.add("City bank");
        banks.add("Dhanlaxmi bank");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, banks);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        txtDate=(EditText)findViewById(R.id.in_date);
        txtDate.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onClick(View v) {

        if (v == txtDate) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog
                    (this, this, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

    }

    @Override
    public void onDateSet(DatePicker datePicker, int dayOfMonth, int monthOfYear, int year) {
        txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1)
                + "-" + year);
    }

}