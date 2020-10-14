package com.aja.loancare;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
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

public class fragment_loanList extends Fragment implements PersonalRecyclerAdapter.onPersonalItemisCLick ,PersonalRecyclerAdapter.onPersonalItemModifyCLick{
    private static final String TAG = "fragment_loanList";
    FloatingActionButton fab;
    ArrayList<Loan> loanlist;
    LoanViewModel loanviemodel;
    String principle,interest,duration,bank,loan,date;
    PersonalRecyclerAdapter recyclerAdapter;
    RecyclerView lv;
    View v;

    public static final int ADD_LOAN=1;
    public static final int EDIT_LOAN=2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loanviemodel= new ViewModelProvider(getActivity()).get(LoanViewModel.class);

        v=inflater.inflate(R.layout.fragment_loan_list, container, false);
        fab=v.findViewById(R.id.fab);

        loanlist= new ArrayList<>();
        lv = v.findViewById(R.id.recyclerpersonal);
        recyclerAdapter = new PersonalRecyclerAdapter(getActivity());
        recyclerAdapter.onPersonalItemisCLickListener(this);
        recyclerAdapter.onPersonalItemisModifyCLickListener(this);
        lv.setAdapter(recyclerAdapter);
        lv.setLayoutManager( new LinearLayoutManager(getActivity()));
        new ItemTouchHelper((new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                loanviemodel.delete(recyclerAdapter.getLoanAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(), "Alarm Deleted", Toast.LENGTH_SHORT).show();
            }
        })).attachToRecyclerView(lv);
        loanviemodel.getAllLoanss().observe(getActivity(), new Observer<List<Loan>>() {
            @Override
            public void onChanged(List<Loan> loans) {
                recyclerAdapter.setLoans(loans);
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),LoanForm.class);
                startActivityForResult(intent,ADD_LOAN);

            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ADD_LOAN&& resultCode== LoanForm.RESULT_OK){
        String hp;
        Loan loanobj=new Loan();
        Log.i(TAG, "onActivityResult data null:Principle");
                if(data!=null)
                {
                principle=data.getStringExtra("principle");
                interest = data.getStringExtra("interest");
                duration = data.getStringExtra("duration");
                date = data.getStringExtra("date");
                bank=data.getStringExtra("bankname");
                loan=data.getStringExtra("loantype");
                loanobj.setDate(date);
                loanobj.setBankName(bank);
                loanobj.setLoanType(loan);
                loanobj.setYears(Integer.parseInt(duration));
                loanobj.setInterest_rate(Float.parseFloat(interest));
                loanobj.setPrincipal(Float.parseFloat(principle));
                loanviemodel.insert(loanobj);
                }

                else if(data==null){
                    Toast.makeText(getActivity(),"null",Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "NULL");
                }



        }
        else if(requestCode==EDIT_LOAN && resultCode== LoanForm.RESULT_OK){
            String hp;
            Loan loanobj=new Loan();
            Log.i(TAG, "onActivityResult data null:Principle");
            int id;
            if(data!=null)
            {
                principle=data.getStringExtra("principle");

                interest = data.getStringExtra("interest");
                duration = data.getStringExtra("duration");
                date = data.getStringExtra("date");
                bank=data.getStringExtra("bankname");
                loan=data.getStringExtra("loantype");
                id=data.getIntExtra("IDd",0);
                loanobj.setDate(date);
                loanobj.setBankName(bank);
                loanobj.setLoanType(loan);
                loanobj.setYears(Integer.parseInt(duration));
                loanobj.setInterest_rate(Float.parseFloat(interest));
                loanobj.setPrincipal(Float.parseFloat(principle));
                loanobj.setLoan_id(id);
                loanviemodel.update(loanobj);
                Toast.makeText(getActivity(),"nulytttl" +loanobj.getPrincipal(),Toast.LENGTH_SHORT).show();
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

    @Override
    public void onModifyClickListener(Loan loan) {
        Intent i=new Intent(getActivity(),changeloan.class);
        i.putExtra(changeloan.EDIT_LOAN_PRINCIPLE, loan.getPrincipal());
        i.putExtra(changeloan.EDIT_LOAN_INTEREST, loan.getInterest_rate());
        i.putExtra(changeloan.EDIT_LOAN_DURATION, loan.getYears());
        i.putExtra(changeloan.EDIT_LOAN_DATE, loan.getDate());
        i.putExtra(changeloan.EDIT_LOAN_BANKNAME,loan.getBankName());
        i.putExtra(changeloan.EDIT_LOAN_LOANTYPE, loan.getLoanType());
        i.putExtra("IDd",loan.getLoan_id());
        startActivityForResult(i,EDIT_LOAN);
    }
}