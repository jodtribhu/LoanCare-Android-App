package com.aja.loancare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class fragment_news extends Fragment {
    ImageButton settings;
    private NewsRecyclerAdapter recyclerAdapter2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_news, container, false);
        settings=v.findViewById(R.id.settings_id);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SettingsActivity.class);
                startActivity(intent);

            }
        });
        RecyclerView ns = v.findViewById(R.id.recyclernews);
        recyclerAdapter2= new NewsRecyclerAdapter(getActivity());
        ns.setAdapter( recyclerAdapter2);
        ns.setLayoutManager( new LinearLayoutManager(getActivity()));
        return v;
    }


}