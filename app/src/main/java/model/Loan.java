package model;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

public class Loan {
    public String  loantype,textpgr;
   public Integer loanimg;
    public Loan(String loantype, String txtpgr, int loanimg) {
        this.loantype=loantype;
        this.textpgr=txtpgr;
        this.loanimg=loanimg;
    }


}
