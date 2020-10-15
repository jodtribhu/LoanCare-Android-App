package com.aja.loancare;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.aja.loancare.mvvmmodel.Loan;

import java.util.ArrayList;


public class PersonalRecyclerAdapter extends RecyclerView.Adapter<PersonalRecyclerAdapter.PersonalRecyclerHolder> {
    ArrayList<Loan> ll;
    Context context;
    private onPersonalItemisCLick mOnPersonalItemisCLick;

    public PersonalRecyclerAdapter(Context context, ArrayList<Loan> loanlist) {
        this.context=context;
        this.ll=loanlist;
    }

    @NonNull
    @Override
    public PersonalRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_personal_sigle,parent,false);
        return new PersonalRecyclerHolder(view);
    }

    public void changed(ArrayList<Loan> ll)
    {
        this.ll=ll;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalRecyclerHolder holder, int position) {

        Loan text = ll.get(position);
        holder.loantype.setText(text.getLoanType());
        holder.bankname.setText(text.getBankName());
        holder.principal.setText(String.valueOf(text.getPrincipal()));
        holder.interest.setText(String.valueOf(text.getInterest_rate()));
        holder.date.setText(text.getLoanType());
        holder.duration.setText(text.getYears());
        holder.txtpgr.setText(text.getProgress());
        holder.pgr.setProgress(Integer.parseInt(text.getProgress()));
        if (holder.loantype.toString()=="SBI"){
          holder.loanimg.setBackgroundResource(R.drawable.sbi);
        }
        if (holder.loantype.toString()=="HDFC"){
            holder.loanimg.setBackgroundResource(R.drawable.hdfc);
        }
        if (holder.loantype.toString()=="PNB"){
            holder.loanimg.setBackgroundResource(R.drawable.pnb);
        }
        if (holder.loantype.toString()=="Axis"){
            holder.loanimg.setBackgroundResource(R.drawable.axis);
        }
    }

    @Override
    public int getItemCount() {
        return ll.size();
    }
    public class PersonalRecyclerHolder extends RecyclerView.ViewHolder {
        ImageView loanimg= itemView.findViewById(R.id.loanimg);
        TextView loantype=itemView.findViewById(R.id.loantype);
        TextView bankname=itemView.findViewById(R.id.bank);
        TextView principal=itemView.findViewById(R.id.prp);
        TextView interest=itemView.findViewById(R.id.intrst);
        TextView duration=itemView.findViewById(R.id.dur);
        TextView date=itemView.findViewById(R.id.date);
        ProgressBar pgr=itemView.findViewById(R.id.progress_bar);
        TextView txtpgr=itemView.findViewById(R.id.text_view_progress);
        public PersonalRecyclerHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnPersonalItemisCLick.onClickListener(getAdapterPosition());
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


}
