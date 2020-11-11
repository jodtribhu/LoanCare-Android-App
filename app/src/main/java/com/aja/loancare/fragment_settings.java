package com.aja.loancare;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.scrounger.countrycurrencypicker.library.Buttons.CountryCurrencyButton;
import com.scrounger.countrycurrencypicker.library.Country;
import com.scrounger.countrycurrencypicker.library.Listener.CountryCurrencyPickerListener;

import java.util.Calendar;

public class fragment_settings extends Fragment {
    Button settings_button;
    TextView Timeview;
    Button timebutton;
    boolean isCheckeds;
    public static final String CURRENCY_SYMBOL=" com.aja.loancare.CURRENCY_SYMBOL";
    public static final String CURRENCY_NAME=" com.aja.loancare.CURRENCY_NAME";
    public static final String CURRENCY_CODE=" com.aja.loancare.CURRENCY_CODE";
    public static final String LOAN_HOUR=" com.aja.loancare.LOAN_HOUR";
    public static final String LOAN_MINUTE=" com.aja.loancare.LOAN_MINUTE";
    public static final String Vibrate=" com.aja.loancare.Vibrate";
    public static final String Background_color=" com.aja.loancare.Background_color";
    public static final String Card_color=" com.aja.loancare.Card_color";
    public static final String Text_Color=" com.aja.loancare.Progress_color";
    CheckBox vibrate_checkbox;
    TextView Colorb;
    Button Colourbutton;

    TextView Colorc;
    Button cardcolorbutton;

    TextView ColorT;
    Button texrcolorbutton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_settings, container, false);
        CountryCurrencyButton button =  v.findViewById(R.id.settings_button);
        Timeview=v.findViewById(R.id.TimeView);
        timebutton=v.findViewById(R.id.TimeButton);
        vibrate_checkbox=v.findViewById(R.id.vibrate_checkbox);
        Colorb=v.findViewById(R.id.colorbView);
        Colourbutton=v.findViewById(R.id.Background_Color);
        Colorc=v.findViewById(R.id.colorcView);
        cardcolorbutton=v.findViewById(R.id.card_Color);
        texrcolorbutton=v.findViewById(R.id.text_Color);
        ColorT=v.findViewById(R.id.colortView);





        //Settings time prior option
        SharedPreferences sharedPreferences= android.preference.PreferenceManager.getDefaultSharedPreferences(getActivity());
        int hour=sharedPreferences.getInt(fragment_settings.LOAN_HOUR,0);
        int minute=sharedPreferences.getInt(fragment_settings.LOAN_MINUTE,0);
        int ccolor=sharedPreferences.getInt(fragment_settings.Card_color,0);
        int bcolor=sharedPreferences.getInt(fragment_settings.Background_color,0);
        int tcolor=sharedPreferences.getInt(fragment_settings.Text_Color,0);
        if(hour!=0 || minute !=0)
        {
            Timeview.setText(hour+":"+minute);
        }
        isCheckeds= sharedPreferences.getBoolean(fragment_settings.Vibrate,false);
        vibrate_checkbox.setChecked(isCheckeds);
        Colorb.setBackgroundColor(bcolor);
        Colorc.setBackgroundColor(ccolor);
        ColorT.setBackgroundColor(tcolor);


        texrcolorbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerDialogBuilder
                        .with(getActivity())
                        .setTitle("Choose color")
//                        .initialColor(Color.parseColor("#ffff")
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(12)
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {
                                Toast.makeText(getActivity(), "onColorSelected: 0x" + Integer.toHexString(selectedColor), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("ok", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                                SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences( getActivity());
                                SharedPreferences.Editor editor = mPrefs.edit();
                                editor.putInt(Text_Color, selectedColor);
                                editor.commit();
                                ColorT.setBackgroundColor(selectedColor);


                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .build()
                        .show();
            }
        });






        cardcolorbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerDialogBuilder
                        .with(getActivity())
                        .setTitle("Choose color")
//                        .initialColor(Color.parseColor("#ffff")
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(12)
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {
                                Toast.makeText(getActivity(), "onColorSelected: 0x" + Integer.toHexString(selectedColor), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("ok", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                                SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences( getActivity());
                                SharedPreferences.Editor editor = mPrefs.edit();
                                editor.putInt(Card_color, selectedColor);
                                editor.commit();
                                Colorc.setBackgroundColor(selectedColor);


                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .build()
                        .show();
            }
        });


        Colourbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerDialogBuilder
                        .with(getActivity())
                        .setTitle("Choose color")
//                        .initialColor(Color.parseColor("#ffff")
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(12)
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {
                                Toast.makeText(getActivity(), "onColorSelected: 0x" + Integer.toHexString(selectedColor), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("ok", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                                SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences( getActivity());
                                SharedPreferences.Editor editor = mPrefs.edit();
                                editor.putInt(Background_color, selectedColor);
                                editor.commit();
                                Colorb.setBackgroundColor(selectedColor);


                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .build()
                        .show();
            }
        });


        vibrate_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences( getActivity());
                SharedPreferences.Editor editor = mPrefs.edit();
                Toast.makeText(getActivity(), "Checked or not "+isChecked, Toast.LENGTH_SHORT).show();
                if(isChecked==true) {
                    editor.putBoolean(Vibrate, true);
                }
                else
                {
                    editor.putBoolean(Vibrate, false);
                }
                editor.commit();
            }
        });
        timebutton.setOnClickListener(new View.OnClickListener() {
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
                        Timeview.setText( selectedHour + ":" + selectedMinute);
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