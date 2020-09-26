package com.aja.loancare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.aja.loancare.model.NewsMOdel;
import com.aja.loancare.volley.MySingleton;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;


public class emi_calculator extends AppCompatActivity implements fragment_emi_calculations.emi_calculatorlistner {
private fragment_emi_calculations mFragment_emi_calculations;

    EditText loan_amt;
    private static final String Base_Url="";
    private static final String TAG = "emi_calculator";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emi_calculator);




        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.emi_cal);
        loan_amt=findViewById(R.id.loan_amt);
        mFragment_emi_calculations=new fragment_emi_calculations();
        loadFragment(mFragment_emi_calculations);
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;

                switch (menuItem.getItemId()) {
                    case R.id.emi_cal:
                        fragment =mFragment_emi_calculations;
                        break;

                    case R.id.News:
                        fragment = new fragment_news();
                        break;

                    case R.id.LoanList:
                        fragment = new fragment_loanList();
                        break;

                }

                return loadFragment(fragment);
            }
        });
    }

    @Override
    public void onInputCalcSent(double principal, int tenure, double rate, double emi) {
        mFragment_emi_calculations.update_data(0,0,0,0);
        mFragment_emi_calculations.update_data(principal,tenure,rate,emi);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.emi_calculator, fragment)
                    .commit();
            return true;
        }
        return false;
    }


}