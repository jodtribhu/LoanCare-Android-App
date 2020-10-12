package com.aja.loancare;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.scrounger.countrycurrencypicker.library.Buttons.CountryCurrencyButton;
import com.scrounger.countrycurrencypicker.library.Country;
import com.scrounger.countrycurrencypicker.library.Listener.CountryCurrencyPickerListener;

public class fragment_settings extends Fragment {
    Button settings_button;
    public static final String CURRENCY_SYMBOL=" com.aja.loancare.CURRENCY_SYMBOL";
    public static final String CURRENCY_NAME=" com.aja.loancare.CURRENCY_NAME";
    public static final String CURRENCY_CODE=" com.aja.loancare.CURRENCY_CODE";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_settings, container, false);
        CountryCurrencyButton button =  v.findViewById(R.id.settings_button);
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