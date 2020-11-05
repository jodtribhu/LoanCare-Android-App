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

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.aja.loancare.mvvmmodel.Loan;

import java.util.ArrayList;
import java.util.List;


public class PersonalRecyclerAdapter extends RecyclerView.Adapter<PersonalRecyclerAdapter.PersonalRecyclerHolder> {
    List<Loan> ll=new ArrayList<>();

    Context context;
    private onPersonalItemisCLick mOnPersonalItemisCLick;
    onPersonalItemModifyCLick mOnPersonalItemisModifyCLick;

    public PersonalRecyclerAdapter(Context context) {
        this.context=context;
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

        Loan text = ll.get(position);
        holder.loantype.setText(text.getLoanType());
        holder.bankname.setText(text.getBankName()+" Bank");
        holder.principal.setText("Principal: "+String.valueOf(text.getPrincipal()));
        holder.interest.setText("Interest: "+String.valueOf(text.getInterest_rate()));
        holder.date.setText("Date: "+text.getDate());
        holder.duration.setText("Duration: "+String.valueOf(text.getYears()));
        if (text.getBankName().contains("SBI")){
            holder.txtpgr.setText("50%");
            holder.pgr.setProgress(50);
        }
        if (text.getBankName().contains("HDFC")){
            holder.txtpgr.setText("60%");
            holder.pgr.setProgress(60);
        }
        if (text.getBankName().contains("PNB")){
            holder.txtpgr.setText("40%");
            holder.pgr.setProgress(40);
        }
        if (text.getBankName().contains("Axis")){
            holder.txtpgr.setText("80%");
            holder.pgr.setProgress(80);
        }
    }

    @Override
    public int getItemCount() {
        return ll.size();
    }
    public class PersonalRecyclerHolder extends RecyclerView.ViewHolder {
        //ImageView loanimg= itemView.findViewById(R.id.loanimg);
        TextView loantype=itemView.findViewById(R.id.loantype);
        TextView bankname=itemView.findViewById(R.id.bank);
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
