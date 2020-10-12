package com.aja.loancare.mvvmmodel;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LoanDao {

    @Insert
    long insert(Loan loan);
    @Delete
    void delete(Loan loan);
    @Update
    void update(Loan loan);

    @Query("DELETE FROM loan_table")
    void deleteall();
    @Query("SELECT * FROM loan_table ORDER BY loan_id DESC")
    LiveData<List<Loan>> getAllalarm();

    @Query("SELECT * FROM loan_table WHERE loan_id=:id")
    Loan getById(int id);

}
