package com.aja.loancare;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class fragment_loanList extends Fragment {
    FloatingActionButton fab;
    private Object recyclerAdapter;
    private  Object layoutmanager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_loan_list, container, false);
        fab=v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),LoanForm.class);
                startActivity(intent);
                RecyclerView lv = v.findViewById(R.id.recyclerpersonal);
                layoutmanager = new LinearLayoutManager(getActivity());
                recyclerAdapter= new PersonalRecyclerAdapter(getActivity());
                lv.setAdapter((RecyclerView.Adapter) recyclerAdapter);
                lv.setLayoutManager((RecyclerView.LayoutManager) layoutmanager);
            }
        });
        return v;
    }

}