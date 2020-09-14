package com.aja.loancare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class PersonalRecyclerAdapter extends RecyclerView.Adapter<PersonalRecyclerAdapter.PersonalRecyclerHolder> {
    public PersonalRecyclerAdapter(FragmentActivity activity) {

    }
    public class PersonalRecyclerHolder extends RecyclerView.ViewHolder {
        public PersonalRecyclerHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


    @NonNull
    @Override
    public PersonalRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_personal_sigle,parent,false);
        return new PersonalRecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalRecyclerHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
