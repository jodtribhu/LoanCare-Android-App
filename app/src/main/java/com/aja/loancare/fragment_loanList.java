package com.aja.loancare;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aja.loancare.Vievmodel.LoanViewModel;
import com.aja.loancare.mvvmmodel.Loan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class fragment_loanList extends Fragment implements PersonalRecyclerAdapter.onPersonalItemisCLick {
    FloatingActionButton fab;
    ArrayList<Loan> loanlist;
    LoanViewModel loanviemodel;
    PersonalRecyclerAdapter recyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_loan_list, container, false);
        fab=v.findViewById(R.id.fab);
        loanviemodel= new ViewModelProvider(getActivity()).get(LoanViewModel.class);
        loanlist= new ArrayList<>();
        RecyclerView lv = v.findViewById(R.id.recyclerpersonal);
        recyclerAdapter = new PersonalRecyclerAdapter(getActivity(), loanlist);
        lv.setAdapter(recyclerAdapter);
        lv.setLayoutManager( new LinearLayoutManager(getActivity()));
        recyclerAdapter.onPersonalItemisCLickListener(this);
        loanviemodel.getAllAlarms().observe(getActivity(), new Observer<List<com.aja.loancare.mvvmmodel.Loan>>() {
            @Override
            public void onChanged(List<com.aja.loancare.mvvmmodel.Loan> loans) {
                recyclerAdapter.changed((ArrayList<Loan>) loans);
            }
        });
        getLifecycle().addObserver(loanviemodel);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),LoanForm.class);
                startActivity(intent);

            }
        });

        return v;
    }

    @Override
    public void onClickListener(int position) {
    Intent intent=new Intent(getActivity(),PersonalLoanActivity.class);
    startActivity(intent);
    }
}