package com.crystal.crystalrangeseekbar.adapters;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by owais.ali on 7/15/2016.
 */
public class RangeSeekbarPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragments;
    private final List<String> titles;

    public RangeSeekbarPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles    = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
