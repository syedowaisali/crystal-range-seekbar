package com.crystal.crystalrangeseekbar.ui.activities;

import android.os.Bundle;

import com.crystal.crystalrangeseekbar.R;
import com.crystal.crystalrangeseekbar.adapters.RangeSeekbarPagerAdapter;
import com.crystal.crystalrangeseekbar.ui.fragments.RangeSeekbar;
import com.crystal.crystalrangeseekbar.ui.fragments.Seekbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by owais.ali on 6/19/2016.
 */
public class RangeSlider extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.range_slider);

        init();
    }

    public final <T> T getView(int resId){
        return (T)findViewById(resId);
    }

    private void init(){
        final TabLayout tabLayout = getView(R.id.tlSeekbar);
        final ViewPager viewPager = getView(R.id.vpSeekbar);

        // set fragments list
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new Seekbar());
        fragments.add(new RangeSeekbar());

        // set tabs title
        final List<String> tabTitles = new ArrayList<>();
        tabTitles.add("Seekbar");
        tabTitles.add("Range Seekbar");


        // create view pager adapter
        RangeSeekbarPagerAdapter adapter = new RangeSeekbarPagerAdapter(getSupportFragmentManager(), fragments, tabTitles);

        // set adapter to pager
        viewPager.setAdapter(adapter);

        // set view pager to tab layout
        tabLayout.setupWithViewPager(viewPager);
    }
}
