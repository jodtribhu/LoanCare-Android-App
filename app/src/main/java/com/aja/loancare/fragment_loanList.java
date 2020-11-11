package com.aja.loancare;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aja.loancare.Vievmodel.LoanViewModel;
import com.aja.loancare.mvvmmodel.Loan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class fragment_loanList extends Fragment implements PersonalRecyclerAdapter.onPersonalItemisCLick, PersonalRecyclerAdapter.onPersonalItemModifyCLick {
    private static final String TAG = "fragment_loanList";
    public static String LOAN_RECIEVER_ID="com.aja.loancare.LOAN_RECIEVER_ID";
    FloatingActionButton fab;
    ArrayList<Loan> loanlist;
    LoanViewModel loanviemodel;
    String principle, interest, duration, bank, loan, date;
    PersonalRecyclerAdapter recyclerAdapter;
    RecyclerView lv;
    View v;
    Date date2;
    int sday;
    int smonth;
    int syear;

    public static final int ADD_LOAN = 1;
    public static final int EDIT_LOAN = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loanviemodel = new ViewModelProvider(getActivity()).get(LoanViewModel.class);

        v = inflater.inflate(R.layout.fragment_loan_list, container, false);
        fab = v.findViewById(R.id.fab);

        IntentFilter intentFilter=new IntentFilter("com.aja.loancare.fragment_loanlist");
        getActivity().registerReceiver(mBroadcastReceiver,intentFilter);

        loanlist = new ArrayList<>();
        lv = v.findViewById(R.id.recyclerpersonal);
        recyclerAdapter = new PersonalRecyclerAdapter(getActivity());
        recyclerAdapter.onPersonalItemisCLickListener(this);
        recyclerAdapter.onPersonalItemisModifyCLickListener(this);
        lv.setAdapter(recyclerAdapter);
        lv.setLayoutManager(new LinearLayoutManager(getActivity()));
        new ItemTouchHelper((new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                LoanHandler loanHandler=new LoanHandler(getActivity());
                loanHandler.cancelAlarm(recyclerAdapter.getLoanAt(viewHolder.getAdapterPosition()));
                loanviemodel.delete(recyclerAdapter.getLoanAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(), "Loan Deleted", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(getActivity(), LoanForm.class);
                startActivityForResult(intent, ADD_LOAN);

            }
        });


        SharedPreferences sharedPreferences= android.preference.PreferenceManager.getDefaultSharedPreferences(getActivity());

        int b_color=sharedPreferences.getInt(fragment_settings.Background_color,0);

        View root = v.findViewById(R.id.coordinator_layout);
        root.setBackgroundColor(b_color);


        return v;
    }

    private BroadcastReceiver mBroadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int loan_id=intent.getIntExtra("LoanStopReciever.LOAN_ID",0);
            if(loanviemodel.checkLoanById(loan_id)!=0) {
                Loan loan = loanviemodel.getLoanById(loan_id);
                int progress = loan.getProgress();
                int duration = loan.getYears();
                int dm = duration * 12;
                int paidmonths = loan.getPaid_months();
                paidmonths = paidmonths + 1;
                int percentage;
                percentage = (int) ((double) paidmonths / (double) dm * 100);
                if(percentage>=100)
                {
                    loanviemodel.delete(loan);
                    LoanHandler loanHandler=new LoanHandler(getActivity());
                    loanHandler.cancelAlarm(loan);

                    Toast.makeText(context, "Congratulations You Have Completed the loan", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context, "Progress" + percentage + " Paid months " + paidmonths + " DM " + dm + "division " + percentage, Toast.LENGTH_SHORT).show();
                    loan.setProgress(percentage);
                    loan.setPaid_months(paidmonths);
                    loan.setLoan_id(loan_id);
                    loanviemodel.update(loan);
                }
            }
            else
            {
                Toast.makeText(context, "Sorry Record Does Not exist", Toast.LENGTH_SHORT).show();
            }


        }
    };



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_LOAN && resultCode == LoanForm.RESULT_OK) {
            String hp;
            Loan loanobj = new Loan();
            Log.i(TAG, "onActivityResult data null:Principle");
            if (data != null) {
                principle = data.getStringExtra("principle");
                interest = data.getStringExtra("interest");
                duration = data.getStringExtra("duration");
                date = data.getStringExtra("date");
                bank = data.getStringExtra("bankname");
                loan = data.getStringExtra("loantype");
                Calendar calendar=Calendar.getInstance();
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                try {
                    date2= format.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                calendar.setTime(date2);
                loanobj.setDate(date);

                sday=calendar.get(Calendar.DAY_OF_MONTH);
                smonth=calendar.get(Calendar.MONTH);
                syear=calendar.get(Calendar.YEAR);

                loanobj.setSday(sday);
                loanobj.setSmonth(smonth);
                loanobj.setSyear(syear);

                //Ring Month
                Calendar ringcal = Calendar.getInstance();
                ringcal.set(Calendar.DAY_OF_MONTH,sday);
                ringcal.set(Calendar.MONTH,smonth);
                ringcal.set(Calendar.YEAR,syear);
                ringcal.add(Calendar.MONTH, 1);

                loanobj.setRday(ringcal.get(Calendar.DAY_OF_MONTH));
                loanobj.setRmonth(ringcal.get(Calendar.MONTH));
                loanobj.setRyear(ringcal.get(Calendar.YEAR));

                loanobj.setBankName(bank);
                loanobj.setLoanType(loan);
                loanobj.setYears(Integer.parseInt(duration));
                loanobj.setInterest_rate(Float.parseFloat(interest));
                loanobj.setPrincipal(Float.parseFloat(principle));
                loanviemodel.insert(loanobj);
            } else if (data == null) {
                Toast.makeText(getActivity(), "null", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "NULL");
            }


        } else if (requestCode == EDIT_LOAN && resultCode == LoanForm.RESULT_OK) {
            String hp;
            Loan loanobj = new Loan();
            Log.i(TAG, "onActivityResult data null:Principle");
            int id;
            if (data != null) {
                principle = data.getStringExtra("principle");
                interest = data.getStringExtra("interest");
                duration = data.getStringExtra("duration");
                date = data.getStringExtra("date");
                bank = data.getStringExtra("bankname");
                loan = data.getStringExtra("loantype");
                id = data.getIntExtra("IDd", 0);
                loanobj.setBankName(bank);
                loanobj.setLoanType(loan);
                loanobj.setYears(Integer.parseInt(duration));
                loanobj.setInterest_rate(Float.parseFloat(interest));
                loanobj.setPrincipal(Float.parseFloat(principle));
                loanobj.setLoan_id(id);
                loanviemodel.update(loanobj);
            } else if (data == null) {
                Toast.makeText(getActivity(), "null", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "NULL");
            }


        }
    }

    @Override
    public void onClickListener(Loan loan) {
        Intent intent = new Intent(getActivity(), PersonalLoanActivity.class);
        intent.putExtra(PersonalLoanActivity.PersonalLoanActivity_PRINCIPLE, loan.getPrincipal());
        intent.putExtra(PersonalLoanActivity.PersonalLoanActivity_INTEREST, loan.getInterest_rate());
        intent.putExtra(PersonalLoanActivity.PersonalLoanActivity_DURATION, loan.getYears());
        intent.putExtra(PersonalLoanActivity.PersonalLoanActivity_PROGRESS, loan.getProgress());
        startActivity(intent);
        }

    @Override
    public void onModifyClickListener(Loan loan) {
        Intent i = new Intent(getActivity(), changeloan.class);
        i.putExtra(changeloan.EDIT_LOAN_PRINCIPLE, loan.getPrincipal());
        i.putExtra(changeloan.EDIT_LOAN_INTEREST, loan.getInterest_rate());
        i.putExtra(changeloan.EDIT_LOAN_DURATION, loan.getYears());
        i.putExtra(changeloan.EDIT_LOAN_DATE, loan.getDate());
        i.putExtra(changeloan.EDIT_LOAN_BANKNAME, loan.getBankName());
        i.putExtra(changeloan.EDIT_LOAN_LOANTYPE, loan.getLoanType());
        i.putExtra("IDd", loan.getLoan_id());
        startActivityForResult(i, EDIT_LOAN);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mBroadcastReceiver);
    }
}