package com.crystal.crystalrangeseekbar.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.crystal.crystalrangeseekbar.R;


/**
 * Created by owais.ali on 7/12/2016.
 */
public class MySeekbar extends CrystalSeekbar {

    public MySeekbar(Context context) {
        super(context);
    }

    public MySeekbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySeekbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected float getCornerRadius(TypedArray typedArray) {
        return super.getCornerRadius(typedArray);
    }

    @Override
    protected float getMinValue(TypedArray typedArray) {
        return 5f;
    }

    @Override
    protected float getMaxValue(TypedArray typedArray) {
        return 90f;
    }

    @Override
    protected float getMinStartValue(TypedArray typedArray) {
        return 20f;
    }

    @Override
    protected float getSteps(TypedArray typedArray) {
        return super.getSteps(typedArray);
    }

    @Override
    protected int getBarColor(TypedArray typedArray) {
        return Color.parseColor("#A0E3F7");
    }

    @Override
    protected int getBarHighlightColor(TypedArray typedArray) {
        return Color.parseColor("#53C9ED");
    }

    @Override
    protected int getLeftThumbColor(TypedArray typedArray) {
        return Color.parseColor("#058EB7");
    }

    @Override
    protected int getLeftThumbColorPressed(TypedArray typedArray) {
        return Color.parseColor("#046887");
    }

    @Override
    protected Drawable getLeftDrawable(TypedArray typedArray) {
        return ContextCompat.getDrawable(getContext(), R.drawable.thumb);
    }

    @Override
    protected Drawable getLeftDrawablePressed(TypedArray typedArray) {
        return ContextCompat.getDrawable(getContext(), R.drawable.thumb_pressed);
    }

    @Override
    protected int getDataType(TypedArray typedArray) {
        return super.getDataType(typedArray);
    }
}
