package com.aja.loancare;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class PersonalRecyclerAdapter extends RecyclerView.Adapter<PersonalRecyclerAdapter.PersonalRecyclerHolder> {
    Context context;

    public PersonalRecyclerAdapter(FragmentActivity activity) {

    }

    @NonNull
    @Override
    public PersonalRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_personal_sigle,parent,false);
        return new PersonalRecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalRecyclerHolder holder, int position) {
        Object intent;
//        holder.pers.setOnClickListener((View.OnClickListener) (intent= new Intent(context,)));
//       context.startActivity((Intent) intent);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class PersonalRecyclerHolder extends RecyclerView.ViewHolder {
        LinearLayout pers= itemView.findViewById(R.id.pers);
        public PersonalRecyclerHolder(@NonNull View itemView) {
            super(itemView);

        }
    }



}
