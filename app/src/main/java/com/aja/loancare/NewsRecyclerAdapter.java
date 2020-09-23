package com.aja.loancare;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.NewsRecyclerHolder> {
    public NewsRecyclerAdapter(FragmentActivity activity) {

    }

    @NonNull
    @Override
    public NewsRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRecyclerHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class NewsRecyclerHolder extends RecyclerView.ViewHolder {
        public NewsRecyclerHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
