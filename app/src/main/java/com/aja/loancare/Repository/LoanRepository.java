package com.aja.loancare.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

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
        allLoan=mLoanDao.getAllalarm();
    }

    public void Insert(Loan loan)
    {
        new InsertAlarmAsyncTask(mLoanDao).execute(loan);
    }
    public void update(Loan loan)
    {
        new UpdateAlarmAsyncTask(mLoanDao).execute(loan);
    }
    public void delete(Loan loan)
    {
        new DeleteAlarmAsyncTask(mLoanDao).execute(loan);
    }
    public void deleteallAlarms()
    {
        new DeleteAllAlarmAsyncTask(mLoanDao).execute();
    }

    public LiveData<List<Loan>> getAllAlarm()
    {
        return allLoan;
    }

    public Loan getLoanById(int id) throws ExecutionException, InterruptedException {
        return new getByIdAsyncTask(mLoanDao).execute(id).get();
    }


    private static class InsertAlarmAsyncTask extends AsyncTask<Loan,Void,Void>
    {
        private LoanDao mLoanDao;

        private  InsertAlarmAsyncTask(LoanDao mLoanDao)
        {
            this.mLoanDao=mLoanDao;
        }

        @Override
        protected Void doInBackground(Loan... Loans) {
            mLoanDao.insert(Loans[0]);
            return null;
        }
    }

    private static class DeleteAlarmAsyncTask extends AsyncTask<Loan,Void,Void>
    {
        private LoanDao mLoanDao;

        private DeleteAlarmAsyncTask(LoanDao mLoanDao)
        {
            this.mLoanDao=mLoanDao;
        }

        @Override
        protected Void doInBackground(Loan... Loans) {
            mLoanDao.delete(Loans[0]);
            return null;
        }
    }

    private static class UpdateAlarmAsyncTask extends AsyncTask<Loan,Void,Void>
    {
        private LoanDao mLoanDao;

        private  UpdateAlarmAsyncTask(LoanDao mLoanDao)
        {
            this.mLoanDao=mLoanDao;
        }

        @Override
        protected Void doInBackground(Loan... Loans) {
            mLoanDao.update(Loans[0]);
            return null;
        }
    }

    private static class DeleteAllAlarmAsyncTask extends AsyncTask<Loan,Void,Void>
    {
        private LoanDao mLoanDao;

        private  DeleteAllAlarmAsyncTask(LoanDao mLoanDao)
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
