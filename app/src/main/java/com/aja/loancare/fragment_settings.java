package com.aja.loancare;

import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.scrounger.countrycurrencypicker.library.Buttons.CountryCurrencyButton;
import com.scrounger.countrycurrencypicker.library.Country;
import com.scrounger.countrycurrencypicker.library.Listener.CountryCurrencyPickerListener;

import java.util.Calendar;

public class fragment_settings extends Fragment {
    Button settings_button;
    EditText Timeedit;
    public static final String CURRENCY_SYMBOL=" com.aja.loancare.CURRENCY_SYMBOL";
    public static final String CURRENCY_NAME=" com.aja.loancare.CURRENCY_NAME";
    public static final String CURRENCY_CODE=" com.aja.loancare.CURRENCY_CODE";
    public static final String LOAN_HOUR=" com.aja.loancare.LOAN_HOUR";
    public static final String LOAN_MINUTE=" com.aja.loancare.LOAN_MINUTE";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_settings, container, false);
        CountryCurrencyButton button =  v.findViewById(R.id.settings_button);
        Timeedit=v.findViewById(R.id.TimeEditText);
        Timeedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences( getActivity());
                        SharedPreferences.Editor editor = mPrefs.edit();
                        editor.putInt( LOAN_HOUR, selectedHour);
                        editor.putInt( LOAN_MINUTE,selectedMinute);
                        editor.commit();
                        Timeedit.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });


        button.setOnClickListener(new CountryCurrencyPickerListener() {
            @Override
            public void onSelectCountry(Country country) {
                if (country.getCurrency() == null) {
                    Toast.makeText(getActivity(),
                            String.format("name: %s\ncode: %s", country.getName(), country.getCode())
                            , Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences( getActivity() );
                    SharedPreferences.Editor editor = mPrefs.edit();
                    editor.putString( CURRENCY_SYMBOL, country.getCurrency().getSymbol() );
                    editor.putString( CURRENCY_CODE, country.getCode() );
                    editor.putString(CURRENCY_NAME,country.getName());
                    editor.commit();
                    Toast.makeText(getActivity(),
                            "country "+country.getName() +" Currency "+ country.getCurrency().getSymbol() +"Code" +country.getCode()
                            , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onSelectCurrency(com.scrounger.countrycurrencypicker.library.Currency currency) {
                Toast.makeText(getActivity(),
                        String.format("name: %s\ncode: %s", currency.getName(), currency.getCode())
                        , Toast.LENGTH_SHORT).show();
            }

        });
        button.setCountry("DE");
        button.setShowCurrency(true);
        return v;
    }
}