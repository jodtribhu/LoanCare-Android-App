package com.aja.loancare;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
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
        SharedPreferences sharedPreferences= android.preference.PreferenceManager.getDefaultSharedPreferences(context);
        int c_color=sharedPreferences.getInt(fragment_settings.Card_color, Color.parseColor("#ee4c7c"));
        int t_color=sharedPreferences.getInt(fragment_settings.Text_Color, Color.parseColor("#000000"));

        //Text color setting
        holder.loantype.setTextColor(t_color);
        holder.bankname.setTextColor(t_color);
        holder.principal.setTextColor(t_color);
        holder.interest.setTextColor(t_color);
        holder.date.setTextColor(t_color);
        holder.duration.setTextColor(t_color);
        holder.txtpgr.setTextColor(t_color);


        holder.card.setCardBackgroundColor(c_color);
        Loan loan = ll.get(position);
        holder.loantype.setText(loan.getLoanType());
        holder.bankname.setText(loan.getBankName());
        holder.principal.setText("P: "+String.valueOf(loan.getPrincipal()));
        holder.interest.setText("I: "+String.valueOf(loan.getInterest_rate())+"%");
        holder.date.setText("D: "+loan.getDate());
        holder.duration.setText("Dur: "+String.valueOf(loan.getYears())+" m");
        if (loan.getLoanType().equals("Car loan")){
            holder.btype.setImageResource(R.drawable.car);
        }
        if (loan.getLoanType().equals("Home Loan")){
            holder.btype.setImageResource(R.drawable.home);
        }
        if (loan.getLoanType().equals("Educational Loan")){
            holder.btype.setImageResource(R.drawable.book1);
        }
        if (loan.getLoanType().equals("Agricultural Loan")){
            holder.btype.setImageResource(R.drawable.agro);
        }
        if (loan.getLoanType().equals("Two-Wheeler Loan")){
            holder.btype.setImageResource(R.drawable.bike);
        }
        if (loan.getLoanType().equals("Personal Loan")){
            holder.btype.setImageResource(R.drawable.person);
        }
        if (loan.getLoanType().equals("Fixed rate Loan")){
            holder.btype.setImageResource(R.drawable.lock1);
        }

        if (loan.getLoanType().equals("Variable rate Loan")){
            holder.btype.setImageResource(R.drawable.var);
        }

        if (loan.getLoanType().equals("Credit card Loan")){
            holder.btype.setImageResource(R.drawable.credit);
        }

        if (loan.getLoanType().equals("Pay-day Loan")){
            holder.btype.setImageResource(R.drawable.pay);
        }
        mLoanHandler.scheduleLoanAlarm(loan);
        holder.pgr.setMax(100);
        holder.pgr.setProgress(loan.getProgress());
//        holder.txtpgr.setText(loan.getProgress());
    }

    @Override
    public int getItemCount() {
        return ll.size();
    }
    public class PersonalRecyclerHolder extends RecyclerView.ViewHolder {
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
        CardView card=itemView.findViewById(R.id.cardrec);

        public PersonalRecyclerHolder(@NonNull final View itemView) {
            super(itemView);
            context=itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnPersonalItemisCLick.onClickListener(ll.get(getAdapterPosition()));
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
        void onClickListener( Loan loan);
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
