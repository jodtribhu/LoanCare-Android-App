package com.aja.loancare.mvvmmodel;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Loan.class},version = 49,exportSchema = false)

public abstract class LoanDatabase extends RoomDatabase {
    private static LoanDatabase instance;
    public abstract LoanDao mLoanDao();

    public static synchronized LoanDatabase getInstance(Context context)
    {
        if(instance==null)
        {
            instance= Room.databaseBuilder(context.getApplicationContext(),LoanDatabase.class,"loan_database")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
