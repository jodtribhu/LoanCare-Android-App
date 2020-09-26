package com.aja.loancare;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import model.Loan;

public class fragment_loanList extends Fragment implements PersonalRecyclerAdapter.onPersonalItemisCLick{
    FloatingActionButton fab;
    private PersonalRecyclerAdapter recyclerAdapter;
    ArrayList<Loan> loanlist= new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_loan_list, container, false);
        fab=v.findViewById(R.id.fab);


        loanlist.add(new Loan("Home Loan","60",R.drawable.ic_home));
        loanlist.add(new Loan("Car Loan","30",R.drawable.ic_car));
        loanlist.add(new Loan("Education Loan","50",R.drawable.ic_edu));
        loanlist.add(new Loan("Agriculture Loan","90",R.drawable.ic_agro));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),LoanForm.class);
                startActivity(intent);

            }
        });
        RecyclerView lv = v.findViewById(R.id.recyclerpersonal);
        recyclerAdapter= new PersonalRecyclerAdapter(getActivity() ,loanlist);
        lv.setAdapter( recyclerAdapter);
        lv.setLayoutManager( new LinearLayoutManager(getActivity()));
        recyclerAdapter.onPersonalItemisCLickListener(this);
        return v;
    }

    @Override
    public void onClickListener(int position) {
    Intent intent=new Intent(getActivity(),PersonalLoanActivity.class);
    startActivity(intent);
    }
}