package com.aja.loancare.mvvmmodel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="loan_table")
public class Loan {

    @PrimaryKey(autoGenerate = true)
    public int loan_id;
    @ColumnInfo(name="bank_name")
    public String BankName;
    @ColumnInfo(name="loan_type")
    public String LoanType;
    @ColumnInfo(name="progress")
    public String Progress;
    @ColumnInfo(name="interest_rate")
    public float interest_rate;
    @ColumnInfo(name="principal")
    public float Principal;
    @ColumnInfo(name="years")
    public int years;
    @ColumnInfo(name="emi")
    public int emi;
    @ColumnInfo(name="date")
    public String date;

    public Loan(int loan_id, String bankName, String loanType, String progress, float interest_rate, float principal, int years, int emi, String date) {
        this.loan_id = loan_id;
        BankName = bankName;
        LoanType = loanType;
        Progress = progress;
        this.interest_rate = interest_rate;
        Principal = principal;
        this.years = years;
        this.emi = emi;
        this.date = date;
    }

    public Loan() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(int loan_id) {
        this.loan_id = loan_id;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getLoanType() {
        return LoanType;
    }

    public void setLoanType(String loanType) {
        LoanType = loanType;
    }

    public String getProgress() {
        return Progress;
    }

    public void setProgress(String progress) {
        Progress = progress;
    }

    public float getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(float interest_rate) {
        this.interest_rate = interest_rate;
    }

    public float getPrincipal() {
        return Principal;
    }

    public void setPrincipal(float principal) {
        Principal = principal;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getEmi() {
        return emi;
    }

    public void setEmi(int emi) {
        this.emi = emi;
    }
}
