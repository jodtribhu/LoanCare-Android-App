package com.aja.loancare;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static androidx.recyclerview.widget.RecyclerView.*;


public class PersonalFragment extends Fragment {
    private Object recyclerAdapter;
    private  Object layoutmanager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        RecyclerView lv = view.findViewById(R.id.recyclerpersonal);
         layoutmanager = new LinearLayoutManager(getActivity());
        recyclerAdapter= new PersonalRecyclerAdapter(getActivity());
        lv.setAdapter((Adapter) recyclerAdapter);
        lv.setLayoutManager((LayoutManager) layoutmanager);
         return view;

    }
}
