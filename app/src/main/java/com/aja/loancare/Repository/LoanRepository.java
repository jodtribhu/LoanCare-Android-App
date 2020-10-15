package com.aja.loancare.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.OnLifecycleEvent;

import com.aja.loancare.mvvmmodel.Loan;
import com.aja.loancare.mvvmmodel.LoanDao;
import com.aja.loancare.mvvmmodel.LoanDatabase;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class LoanRepository {
    private LoanDao mLoanDao;
    private LiveData<List<Loan>> allLoan;

    public LoanRepository(Application application)
    {
        LoanDatabase database=LoanDatabase.getInstance(application);
        mLoanDao=database.mLoanDao();
        allLoan=mLoanDao.getAllLoan();
    }

    public void Insert(Loan loan)
    {
        new InsertLoanAsyncTask(mLoanDao).execute(loan);
    }
    public void update(Loan loan)
    {
        new UpdateLoanAsyncTask(mLoanDao).execute(loan);
    }
    public void delete(Loan loan)
    {
        new DeleteLoanAsyncTask(mLoanDao).execute(loan);
    }
    public void deleteallAlarms()
    {
        new DeleteAllLoanAsyncTask(mLoanDao).execute();
    }

    public LiveData<List<Loan>> getAllLoan()
    {
        return allLoan;
    }

    public Loan getLoanById(int id) throws ExecutionException, InterruptedException {
        return new getByIdAsyncTask(mLoanDao).execute(id).get();
    }


    private static class InsertLoanAsyncTask extends AsyncTask<Loan,Void,Void>
    {
        private LoanDao mLoanDao;

        private  InsertLoanAsyncTask(LoanDao mLoanDao)
        {
            this.mLoanDao=mLoanDao;
        }

        @Override
        protected Void doInBackground(Loan... Loans) {
            mLoanDao.insert(Loans[0]);
            return null;
        }
    }

    private static class DeleteLoanAsyncTask extends AsyncTask<Loan,Void,Void>
    {
        private LoanDao mLoanDao;

        private DeleteLoanAsyncTask(LoanDao mLoanDao)
        {
            this.mLoanDao=mLoanDao;
        }

        @Override
        protected Void doInBackground(Loan... Loans) {
            mLoanDao.delete(Loans[0]);
            return null;
        }
    }

    private static class UpdateLoanAsyncTask extends AsyncTask<Loan,Void,Void>
    {
        private LoanDao mLoanDao;

        private  UpdateLoanAsyncTask(LoanDao mLoanDao)
        {
            this.mLoanDao=mLoanDao;
        }

        @Override
        protected Void doInBackground(Loan... Loans) {
            mLoanDao.update(Loans[0]);
            return null;
        }
    }

    private static class DeleteAllLoanAsyncTask extends AsyncTask<Loan,Void,Void>
    {
        private LoanDao mLoanDao;

        private  DeleteAllLoanAsyncTask(LoanDao mLoanDao)
        {
            this.mLoanDao=mLoanDao;
        }

        @Override
        protected Void doInBackground(Loan... Loans) {
            mLoanDao.deleteall();
            return null;
        }
    }

    private static class getByIdAsyncTask extends android.os.AsyncTask<Integer, Void, Loan> {

        private LoanDao mLoanDao;

        getByIdAsyncTask(LoanDao mLoanDao) {
            this.mLoanDao=mLoanDao;
        }

        @Override
        protected Loan doInBackground(final Integer... params) {
            return mLoanDao.getById(params[0]);
        }
    }

}
