package com.aja.loancare;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import com.scrounger.countrycurrencypicker.library.Buttons.CountryCurrencyButton;
import com.scrounger.countrycurrencypicker.library.Country;
import com.scrounger.countrycurrencypicker.library.Listener.CountryCurrencyPickerListener;

public class SettingsActivity extends AppCompatActivity {
    Button settings_button;
    public static final String CURRENCY_SYMBOL=" com.aja.loancare.CURRENCY_SYMBOL";
    public static final String CURRENCY_NAME=" com.aja.loancare.CURRENCY_NAME";
    public static final String CURRENCY_CODE=" com.aja.loancare.CURRENCY_CODE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        CountryCurrencyButton button =  findViewById(R.id.settings_button);
        button.setOnClickListener(new CountryCurrencyPickerListener() {
            @Override
            public void onSelectCountry(Country country) {
                if (country.getCurrency() == null) {
                    Toast.makeText(SettingsActivity.this,
                            String.format("name: %s\ncode: %s", country.getName(), country.getCode())
                            , Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences( SettingsActivity.this );
                    SharedPreferences.Editor editor = mPrefs.edit();
                    editor.putString( CURRENCY_SYMBOL, country.getCurrency().getSymbol() );
                    editor.putString( CURRENCY_CODE, country.getCode() );
                    editor.putString(CURRENCY_NAME,country.getName());
                    editor.commit();
                    Toast.makeText(SettingsActivity.this,
                            "country "+country.getName() +" Currency "+ country.getCurrency().getSymbol() +"Code" +country.getCode()
                            , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onSelectCurrency(com.scrounger.countrycurrencypicker.library.Currency currency) {
                Toast.makeText(SettingsActivity.this,
                        String.format("name: %s\ncode: %s", currency.getName(), currency.getCode())
                        , Toast.LENGTH_SHORT).show();
            }

        });
        button.setCountry("DE");
        button.setShowCurrency(true);
    }

    public static class SettingsFragment extends PreferenceFragmentCompat
    {
        public static final String PREF_COUNTRIES="pref_countries";
        private SharedPreferences.OnSharedPreferenceChangeListener mOnSharedPreferenceChangeListener;
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey)
        {
            setPreferencesFromResource(R.xml.main_preferences, rootKey);

        }

        @Override
        public void onResume()
        {
            super.onResume();


        }

        @Override
        public void onPause() {
            super.onPause();

        }
    }

}