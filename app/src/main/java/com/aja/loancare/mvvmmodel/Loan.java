package com.aja.loancare.mvvmmodel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName="loan_table")
public class Loan {

    @PrimaryKey(autoGenerate = true)
    public int loan_id;
    @ColumnInfo(name="bank_name")
    public String BankName;
    @ColumnInfo(name="loan_type")
    public String LoanType;
    @ColumnInfo(name="progress")
    public int Progress;
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
    @ColumnInfo(name="sday")
    public int sday;
    @ColumnInfo(name="smonth")
    public int smonth;
    @ColumnInfo(name="syear")
    public int syear;
    @ColumnInfo(name="rday")
    public int rday;
    @ColumnInfo(name="rmonth")
    public int rmonth;
    @ColumnInfo(name="ryear")
    public int ryear;
    @ColumnInfo(name="ring_date")
    public String ring_date;

    public String getRing_date() {
        return ring_date;
    }

    public void setRing_date(String ring_date) {
        this.ring_date = ring_date;
    }

    public Loan(int loan_id, String bankName, String loanType, int progress, float interest_rate, float principal, int years, int emi, String date, int sday, int smonth, int syear, int rday, int rmonth, int ryear, String ring_date) {
        this.loan_id = loan_id;
        BankName = bankName;
        LoanType = loanType;
        Progress = progress;
        this.interest_rate = interest_rate;
        Principal = principal;
        this.years = years;
        this.emi = emi;
        this.date = date;
        this.sday = sday;
        this.smonth = smonth;
        this.syear = syear;
        this.rday = rday;
        this.rmonth = rmonth;
        this.ryear = ryear;
        this.ring_date = ring_date;
    }

    public int getSday() {
        return sday;
    }

    public void setSday(int sday) {
        this.sday = sday;
    }

    public int getSmonth() {
        return smonth;
    }

    public void setSmonth(int smonth) {
        this.smonth = smonth;
    }

    public int getSyear() {
        return syear;
    }

    public void setSyear(int syear) {
        this.syear = syear;
    }

    public int getRday() {
        return rday;
    }

    public void setRday(int rday) {
        this.rday = rday;
    }

    public int getRmonth() {
        return rmonth;
    }

    public void setRmonth(int rmonth) {
        this.rmonth = rmonth;
    }

    public int getRyear() {
        return ryear;
    }

    public void setRyear(int ryear) {
        this.ryear = ryear;
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

    public int getProgress() {
        return Progress;
    }

    public void setProgress(int progress) {
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
