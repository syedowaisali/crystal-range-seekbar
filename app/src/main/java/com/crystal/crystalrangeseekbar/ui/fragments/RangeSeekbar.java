package com.crystal.crystalrangeseekbar.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.R;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.BubbleThumbRangeSeekbar;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.crystal.crystalrangeseekbar.widgets.MyRangeSeekbar;

/**
 * Created by owais.ali on 7/15/2016.
 */
public class RangeSeekbar extends Fragment {

    protected View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null)
                parent.removeView(rootView);
        }

        try {
            rootView = inflater.inflate(R.layout.range_seekbar, container, false);
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


    private void init() {

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
        final CrystalRangeSeekbar rangeSeekbar = (CrystalRangeSeekbar) rootView.findViewById(R.id.rangeSeekbar1);

        // get min and max text view
        final TextView tvMin = (TextView) rootView.findViewById(R.id.textMin1);
        final TextView tvMax = (TextView) rootView.findViewById(R.id.textMax1);

        // set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMin.setText(String.valueOf(minValue));
                tvMax.setText(String.valueOf(maxValue));
            }
        });

        // set final value listener
        rangeSeekbar.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {
                Log.d("CRS=>", String.valueOf(minValue) + " : " + String.valueOf(maxValue));
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rangeSeekbar.setMinValue(6).setMaxValue(30).setMinStartValue(7).setMaxStartValue(10).apply();
            }
        }, 5000);
    }

    private void setRangeSeekbar2(){

        // get seekbar from view
        final BubbleThumbRangeSeekbar rangeSeekbar = (BubbleThumbRangeSeekbar) rootView.findViewById(R.id.rangeSeekbar2);

        // get min and max text view
        final TextView tvMin = (TextView) rootView.findViewById(R.id.textMin2);
        final TextView tvMax = (TextView) rootView.findViewById(R.id.textMax2);

        // set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMin.setText(String.valueOf(minValue));
                tvMax.setText(String.valueOf(maxValue));
            }
        });
    }

    private void setRangeSeekbar3(){

        // get seekbar from view
        final CrystalRangeSeekbar rangeSeekbar = (CrystalRangeSeekbar) rootView.findViewById(R.id.rangeSeekbar3);

        // get min and max text view
        final TextView tvMin = (TextView) rootView.findViewById(R.id.textMin3);
        final TextView tvMax = (TextView) rootView.findViewById(R.id.textMax3);

        // set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMin.setText(String.valueOf(minValue));
                tvMax.setText(String.valueOf(maxValue));
            }
        });
    }

    private void setRangeSeekbar4(){

        // get seekbar from view
        final CrystalRangeSeekbar rangeSeekbar = (CrystalRangeSeekbar) rootView.findViewById(R.id.rangeSeekbar4);

        // get min and max text view
        final TextView tvMin = (TextView) rootView.findViewById(R.id.textMin4);
        final TextView tvMax = (TextView) rootView.findViewById(R.id.textMax4);

        // set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMin.setText(String.valueOf(minValue));
                tvMax.setText(String.valueOf(maxValue));
            }
        });
    }

    private void setRangeSeekbar5(){

        // get seekbar from view
        final CrystalRangeSeekbar rangeSeekbar = (CrystalRangeSeekbar) rootView.findViewById(R.id.rangeSeekbar5);

        // get min and max text view
        final TextView tvMin = (TextView) rootView.findViewById(R.id.textMin5);
        final TextView tvMax = (TextView) rootView.findViewById(R.id.textMax5);

        // set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMin.setText(String.valueOf(minValue));
                tvMax.setText(String.valueOf(maxValue));
            }
        });
    }

    private void setRangeSeekbar6(){

        // get seekbar from view
        final CrystalRangeSeekbar rangeSeekbar = (CrystalRangeSeekbar) rootView.findViewById(R.id.rangeSeekbar6);

        // get min and max text view
        final TextView tvMin = (TextView) rootView.findViewById(R.id.textMin6);
        final TextView tvMax = (TextView) rootView.findViewById(R.id.textMax6);

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
                .setRightThumbDrawable(R.drawable.thumb_android)
                .setRightThumbHighlightDrawable(R.drawable.thumb_android_pressed)
                .setDataType(CrystalRangeSeekbar.DataType.INTEGER)
                .apply();

        // set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMin.setText(String.valueOf(minValue));
                tvMax.setText(String.valueOf(maxValue));
            }
        });
    }

    private void setRangeSeekbar7(){

        // get seekbar from view
        final CrystalRangeSeekbar rangeSeekbar = new CrystalRangeSeekbar(getActivity());

        // get min and max text view
        final TextView tvMin = (TextView) rootView.findViewById(R.id.textMin7);
        final TextView tvMax = (TextView) rootView.findViewById(R.id.textMax7);

        // set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMin.setText(String.valueOf(minValue));
                tvMax.setText(String.valueOf(maxValue));
            }
        });

        // get range seekbar container
        RelativeLayout container = (RelativeLayout) rootView.findViewById(R.id.contRangeSeekbar7);
        container.addView(rangeSeekbar);
    }

    private void setRangeSeekbar8(){

        // get seekbar from view
        final MyRangeSeekbar rangeSeekbar = (MyRangeSeekbar) rootView.findViewById(R.id.rangeSeekbar8);

        // get min and max text view
        final TextView tvMin = (TextView) rootView.findViewById(R.id.textMin8);
        final TextView tvMax = (TextView) rootView.findViewById(R.id.textMax8);

        // set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMin.setText(String.valueOf(minValue));
                tvMax.setText(String.valueOf(maxValue));
            }
        });
    }
}
