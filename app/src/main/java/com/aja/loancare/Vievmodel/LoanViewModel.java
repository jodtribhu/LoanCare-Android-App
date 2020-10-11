package com.aja.loancare.Vievmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.aja.loancare.Repository.LoanRepository;
import com.aja.loancare.mvvmmodel.Loan;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class LoanViewModel  extends AndroidViewModel {
    private LoanRepository Repository;
    private LiveData<List<Loan>> allLoanss;

    public LoanViewModel(@NonNull Application application) {
        super(application);
        Repository=new LoanRepository(application);
        allLoanss=Repository.getAllAlarm();
    }
    public void insert(Loan loan)
    {
        Repository.Insert(loan);
    }
    public void update(Loan loan)
    {
        Repository.update(loan);
    }
    public  void delete(Loan loan)
    {
        Repository.delete(loan);
    }
    public void deleteallalarms()
    {
        Repository.deleteallAlarms();
    }
    public LiveData<List<Loan>> getAllAlarms()
    {
        return allLoanss;
    }
    public Loan getAlarmById(int id) {
        try {
            return Repository.getLoanById(id);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return new Loan();
    }
}
