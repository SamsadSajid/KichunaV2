package com.example.dolphin.kichuna;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by DOLPHIN on 12/16/2016.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments =new ArrayList<>();
    ArrayList<String> tabTitles =new ArrayList<>();

    public void addFragments(Fragment fragments, String tabTitles){
        this.fragments.add(fragments);
        this.tabTitles.add(tabTitles);
    }

    public ViewPagerAdapter(FragmentManager fm){
        super(fm);
    };

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int pos){
        return  tabTitles.get(pos);
    }
}
