package com.crystal.crystalrangeseekbar.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.R;
import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.BubbleThumbSeekbar;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.crystal.crystalrangeseekbar.widgets.CrystalSeekbar;
import com.crystal.crystalrangeseekbar.widgets.MySeekbar;

/**
 * Created by owais.ali on 7/15/2016.
 */
public class Seekbar extends Fragment {

    protected View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null)
                parent.removeView(rootView);
        }

        try {
            rootView = inflater.inflate(R.layout.seekbar, container, false);
        } catch (InflateException e) {
            e.printStackTrace();
        }

        return rootView;
    }

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    public void init() {

        setRangeSeekbar1();
        setRangeSeekbar2();
        setRangeSeekbar3();
        setRangeSeekbar4();
        setRangeSeekbar5();
        setRangeSeekbar6();
        setRangeSeekbar7();
        setRangeSeekbar8();
    }

    private void setRangeSeekbar1(){

        // get seekbar from view
        final CrystalSeekbar seekbar = (CrystalSeekbar) rootView.findViewById(R.id.rangeSeekbar1);

        // get min and max text view
        final TextView tvMin = (TextView) rootView.findViewById(R.id.textMin1);

        // set listener
        seekbar.setOnSeekbarChangeListener(new OnSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue) {
                tvMin.setText(String.valueOf(minValue));
            }
        });

        // set final value listener
        seekbar.setOnSeekbarFinalValueListener(new OnSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number value) {
                Log.d("CRS=>", String.valueOf(value));
            }
        });
    }

    private void setRangeSeekbar2(){

        // get seekbar from view
        final BubbleThumbSeekbar rangeSeekbar = (BubbleThumbSeekbar) rootView.findViewById(R.id.rangeSeekbar2);

        // get min and max text view
        final TextView tvMin = (TextView) rootView.findViewById(R.id.textMin2);

        // set listener
        rangeSeekbar.setOnSeekbarChangeListener(new OnSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue) {
                tvMin.setText(String.valueOf(minValue));
            }
        });
    }

    private void setRangeSeekbar3(){

        // get seekbar from view
        final BubbleThumbSeekbar rangeSeekbar = (BubbleThumbSeekbar) rootView.findViewById(R.id.rangeSeekbar3);

        // get min and max text view
        final TextView tvMin = (TextView) rootView.findViewById(R.id.textMin3);
        final TextView tvMax = (TextView) rootView.findViewById(R.id.textMax3);

        // set listener
        rangeSeekbar.setOnSeekbarChangeListener(new OnSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue) {
                tvMin.setText(String.valueOf(minValue));
            }
        });
    }

    private void setRangeSeekbar4(){

        // get seekbar from view
        final CrystalSeekbar rangeSeekbar = (CrystalSeekbar) rootView.findViewById(R.id.rangeSeekbar4);

        // get min and max text view
        final TextView tvMin = (TextView) rootView.findViewById(R.id.textMin4);
        final TextView tvMax = (TextView) rootView.findViewById(R.id.textMax4);

        // set properties
        rangeSeekbar
                .setCornerRadius(10f)
                .setBarColor(Color.parseColor("#93F9B5"))
                .setBarHighlightColor(Color.parseColor("#16E059"))
                .setMinValue(400)
                .setMaxValue(800)
                .setSteps(100)
                .setLeftThumbDrawable(R.drawable.thumb_android)
                .setLeftThumbHighlightDrawable(R.drawable.thumb_android_pressed)
                .setDataType(CrystalRangeSeekbar.DataType.INTEGER)
                .apply();

        // set listener
        rangeSeekbar.setOnSeekbarChangeListener(new OnSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue) {
                tvMin.setText(String.valueOf(minValue));
            }
        });
    }

    private void setRangeSeekbar5(){

        // get seekbar from view
        final CrystalSeekbar rangeSeekbar = new CrystalSeekbar(getActivity());

        // get min and max text view
        final TextView tvMin = (TextView) rootView.findViewById(R.id.textMin5);
        final TextView tvMax = (TextView) rootView.findViewById(R.id.textMax5);

        // set listener
        rangeSeekbar.setOnSeekbarChangeListener(new OnSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue) {
                tvMin.setText(String.valueOf(minValue));
            }
        });

        // get range seekbar container
        RelativeLayout container = (RelativeLayout) rootView.findViewById(R.id.contRangeSeekbar5);
        container.addView(rangeSeekbar);
    }

    private void setRangeSeekbar6(){

        // get seekbar from view
        final MySeekbar rangeSeekbar = (MySeekbar) rootView.findViewById(R.id.rangeSeekbar6);

        // get min and max text view
        final TextView tvMin = (TextView) rootView.findViewById(R.id.textMin6);
        final TextView tvMax = (TextView) rootView.findViewById(R.id.textMax6);

        // set listener
        rangeSeekbar.setOnSeekbarChangeListener(new OnSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue) {
                tvMin.setText(String.valueOf(minValue));
            }
        });
    }

    private void setRangeSeekbar7(){

        // get seekbar from view
        final CrystalSeekbar rangeSeekbar = (CrystalSeekbar) rootView.findViewById(R.id.rangeSeekbar7);

        // get min and max text view
        final TextView tvMin = (TextView) rootView.findViewById(R.id.textMin7);
        final TextView tvMax = (TextView) rootView.findViewById(R.id.textMax7);

        // set listener
        rangeSeekbar.setOnSeekbarChangeListener(new OnSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue) {
                tvMin.setText(String.valueOf(minValue));
            }
        });

    }

    private void setRangeSeekbar8(){

        // get seekbar from view
        final CrystalSeekbar rangeSeekbar = (CrystalSeekbar) rootView.findViewById(R.id.rangeSeekbar8);

        // get min and max text view
        final TextView tvMin = (TextView) rootView.findViewById(R.id.textMin8);
        final TextView tvMax = (TextView) rootView.findViewById(R.id.textMax8);

        // change position left to right
        rangeSeekbar.setPosition(CrystalSeekbar.Position.RIGHT).apply();

        // set listener
        rangeSeekbar.setOnSeekbarChangeListener(new OnSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue) {
                tvMin.setText(String.valueOf(minValue));
            }
        });

    }

}
