package com.example.back.Adapters;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class ViewAdapter extends FragmentPagerAdapter {
    ArrayList<String> arrayList=new ArrayList<>();
    List<Fragment> fragmentList=new ArrayList<>();

    public void addFragment(Fragment fragment,String title){
        arrayList.add(title);
        fragmentList.add(fragment);
    }


    public ViewAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        return fragmentList.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayList.get(position);
    }
}
