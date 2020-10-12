package com.aja.loancare;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class emicalcAdapter extends FragmentStateAdapter {
    public emicalcAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new fragment_emi_calculations();
            default:
                return new fragment_emi_compare();

        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
