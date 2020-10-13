package com.aja.loancare;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aja.loancare.Vievmodel.LoanViewModel;
import com.aja.loancare.mvvmmodel.Loan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

public class fragment_loanList extends Fragment implements PersonalRecyclerAdapter.onPersonalItemisCLick {
    private static final String TAG = "fragment_loanList";
    FloatingActionButton fab;
    ArrayList<Loan> loanlist;
    LoanViewModel loanviemodel;
    String principle,interest,duration,bank,loan,date;
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
                startActivityForResult(intent,123);

            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==123 && resultCode== LoanForm.RESULT_OK){
        String hp;

        Log.i(TAG, "onActivityResult data null:Principle");
                if(data!=null)

                {
                principle=data.getStringExtra("principle");
                interest = data.getStringExtra("interest");
                duration = data.getStringExtra("duration");
                date = data.getStringExtra("date");
                bank=data.getStringExtra("bankname");
                loan=data.getStringExtra("loantype");
                    Toast.makeText(getActivity(), "onClick:" +
                            "bbbbbb Log data "+interest+duration+date+principle+bank+loan, Toast.LENGTH_SHORT).show();
                }

                else if(data==null){
                    Toast.makeText(getActivity(),"null",Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "NULL");
                }



        }
    }

    @Override
    public void onClickListener(int position) {
    Intent intent=new Intent(getActivity(),PersonalLoanActivity.class);
    startActivity(intent);
    }
}