package com.aja.loancare;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class fragment_emi_top extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_emi_top, container, false);
        ViewPager2 viewPager2=v.findViewById(R.id.viewPager);
        viewPager2.setAdapter(new emicalcAdapter(getActivity()));

        TabLayout tabLayout=v.findViewById(R.id.tabLayout);
        TabLayoutMediator tabLayoutMediator=new TabLayoutMediator(tabLayout, viewPager2
                , new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position)
                {
                    case 0:{
                        tab.setText("EMI COMPARE");
                        tab.setIcon(R.drawable.tabbed_compare);
                        break;
                    }
                    case 1:{
                        tab.setText("EMI CALCULATOR");
                        tab.setIcon(R.drawable.tab_loan_calculator);
                        break;
                    }
                }
            }
        });
        tabLayoutMediator.attach();
        return v ;
    }
}