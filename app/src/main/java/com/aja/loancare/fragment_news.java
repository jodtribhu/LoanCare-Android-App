package com.aja.loancare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class fragment_news extends Fragment {
    Button settings;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_news, container, false);
        Intent intent=new Intent(getActivity(),SettingsActivity.class);
        startActivity(intent);
        settings=v.findViewById(R.id.settings_id);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getActivity());
                    String Country=String.valueOf(sharedPreferences.getString("Countries","India"));
                Toast.makeText(getActivity(), "The country is "+Country, Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }


}