package com.aja.loancare.Vievmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.OnLifecycleEvent;

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
        allLoanss=Repository.getAllLoan();
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
    public void deleteallLoans()
    {
        Repository.deleteallAlarms();
    }
    public LiveData<List<Loan>> getAllLoanss()
    {
        return allLoanss;
    }
    public Loan getLoanById(int id) {
        try {
            return Repository.getLoanById(id);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return new Loan();
    }
    public int checkLoanById(int id) {
        try {
            return Repository.checkLoanid(id);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return 0;
    }

//    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//    public void onRefresh()
//    {
//        allLoanss=Repository.getAllLoan();
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
//    public void onCreateRefresh()
//    {
//        allLoanss=Repository.getAllLoan();
//    }

}
