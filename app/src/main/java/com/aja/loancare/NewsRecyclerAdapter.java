package com.aja.loancare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.aja.loancare.model.NewsMOdel;

import java.io.File;
import java.util.ArrayList;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.NewsRecyclerHolder> {

    private ArrayList<NewsMOdel> nlist;
    Context context;
    private onItemisCLick mOnItemisCLick;

    public NewsRecyclerAdapter(Context context, ArrayList<NewsMOdel> nlist) {
    this.context=context;
    this.nlist=nlist;
    }

    @NonNull
    @Override
    public NewsRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_news_single,parent,false);
        return new NewsRecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRecyclerHolder holder, int position) {
        NewsMOdel newsMOdel=nlist.get(position);
        holder.title.setText(newsMOdel.getTitle());
        holder.Description.setText(newsMOdel.getDescription());
        holder.pubDate.setText(newsMOdel.getPubdate());
    }

    @Override
    public int getItemCount() {
        return nlist.size();
    }

    public class NewsRecyclerHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView Description;
        TextView pubDate;
        public NewsRecyclerHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.newsTitle);
            Description=itemView.findViewById(R.id.description);
            pubDate=itemView.findViewById(R.id.pubDate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemisCLick.onClickListener(getAdapterPosition());
                }
            });
        }

    }


    public interface onItemisCLick{
        void onClickListener( int position);
    }

    public void onItemisCLickListener(onItemisCLick onItemisCLick)
    {
        this.mOnItemisCLick=onItemisCLick;
    }
}
