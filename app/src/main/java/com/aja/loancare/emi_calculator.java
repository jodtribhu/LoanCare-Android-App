package com.aja.loancare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class emi_calculator extends AppCompatActivity implements fragment_emi_calculations.emi_calculatorlistner {
private fragment_emi_calculations mFragment_emi_calculations;
EditText loan_amt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emi_calculator);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.emi_cal);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.News:startActivity(new Intent(getApplicationContext(), NewsUpdates.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.LoanList:startActivity(new Intent(getApplicationContext(),LoanLists.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
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