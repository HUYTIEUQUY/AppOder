package com.example.appoder;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewTabAdapter extends FragmentStatePagerAdapter {
    public ViewTabAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return  new Tab_Home();
            case 1:
                return  new Tab_Oder();
            case 2:
                return  new Tab_User();
            default:
                return new Tab_Oder();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
