package com.aja.loancare;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.aja.loancare.mvvmmodel.Loan;

import java.util.ArrayList;
import java.util.List;


public class PersonalRecyclerAdapter extends RecyclerView.Adapter<PersonalRecyclerAdapter.PersonalRecyclerHolder> {
    List<Loan> ll=new ArrayList<>();

    Context context;
    LoanHandler mLoanHandler;
    private onPersonalItemisCLick mOnPersonalItemisCLick;
    onPersonalItemModifyCLick mOnPersonalItemisModifyCLick;

    public PersonalRecyclerAdapter(Context context) {
        this.context=context;
        mLoanHandler=new LoanHandler(context);
    }

    @NonNull
    @Override
    public PersonalRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_personal_sigle,parent,false);
        return new PersonalRecyclerHolder(view);
    }

    public void changed(List<Loan> ll)
    {
        this.ll=ll;
    }
    public void setLoans(List<Loan> loans)
    {
        this.ll=loans;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull PersonalRecyclerHolder holder, int position) {

        Loan loan = ll.get(position);
        holder.loantype.setText(loan.getLoanType());
        holder.bankname.setText(loan.getBankName()+" Bank");
        holder.principal.setText("Prp: "+String.valueOf(loan.getPrincipal()));
        holder.interest.setText("Int: "+String.valueOf(loan.getInterest_rate())+"%");
        holder.date.setText("Date: "+loan.getDate());
        holder.duration.setText("Duration: "+String.valueOf(loan.getYears())+" m");
        if (loan.getLoanType().equals("Car loan")){
            holder.btype.setImageResource(R.drawable.ic_car);
        }
        if (loan.getLoanType().equals("Home Loan")){
            holder.btype.setImageResource(R.drawable.ic_home);
        }
        if (loan.getLoanType().equals("Educational Loan")){
            holder.btype.setImageResource(R.drawable.ic_edu);
        }
        if (loan.getLoanType().equals("Agricultural Loan")){
            holder.btype.setImageResource(R.drawable.ic_agro);
        }

        mLoanHandler.scheduleLoanAlarm(loan);
//        holder.txtpgr.setText(text.getProgress());
//        holder.pgr.setProgress(Integer.parseInt(text.getProgress()));

    }

    @Override
    public int getItemCount() {
        return ll.size();
    }
    public class PersonalRecyclerHolder extends RecyclerView.ViewHolder {
//        ImageView loanimg= itemView.findViewById(R.id.loanimg);
        TextView loantype=itemView.findViewById(R.id.loantype);
        TextView bankname=itemView.findViewById(R.id.bank);
        ImageView btype=itemView.findViewById(R.id.btype);
        TextView principal=itemView.findViewById(R.id.prp);
        TextView interest=itemView.findViewById(R.id.intrst);
        TextView duration=itemView.findViewById(R.id.dur);
        TextView date=itemView.findViewById(R.id.date);
        ProgressBar pgr=itemView.findViewById(R.id.progress_bar);
        Button mButton=itemView.findViewById(R.id.mod);
        TextView txtpgr=itemView.findViewById(R.id.text_view_progress);

        public PersonalRecyclerHolder(@NonNull final View itemView) {
            super(itemView);
            context=itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnPersonalItemisCLick.onClickListener(getAdapterPosition());
                }
            });
                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mOnPersonalItemisModifyCLick.onModifyClickListener(ll.get(getAdapterPosition()));
                    }
                });
        }
    }
    public interface onPersonalItemisCLick{
        void onClickListener( int position);
    }

    public void onPersonalItemisCLickListener(onPersonalItemisCLick onItemisCLick)
    {
        this.mOnPersonalItemisCLick=onItemisCLick;
    }

    public interface onPersonalItemModifyCLick{
        void onModifyClickListener(Loan loan);
    }
    public void onPersonalItemisModifyCLickListener(onPersonalItemModifyCLick onModifyClick)
    {
        this.mOnPersonalItemisModifyCLick=onModifyClick;
    }

    public Loan getLoanAt(int position)
    {
        return ll.get(position);
    }
}
