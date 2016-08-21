package com.crystal.crystalrangeseekbar.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarFinalValueListener;
import com.example.crystalrangeseekbar.R;


/**
 * Created by owais.ali on 6/20/2016.
 */
public class CrystalSeekbar extends View {

    //////////////////////////////////////////
    // PRIVATE CONSTANTS
    //////////////////////////////////////////

    private static final int INVALID_POINTER_ID = 255;
    //private static int DEFAULT_THUMB_WIDTH ;
    //private static int DEFAULT_THUMB_HEIGHT;

    private final float NO_STEP = -1f;

    //////////////////////////////////////////
    // PUBLIC CONSTANTS CLASS
    //////////////////////////////////////////

    public static final class DataType{
        public static final int LONG        = 0;
        public static final int DOUBLE      = 1;
        public static final int INTEGER     = 2;
        public static final int FLOAT       = 3;
        public static final int SHORT       = 4;
        public static final int BYTE        = 5;
    }

    public static final class Position{
        public static final int LEFT  = 0;
        public static final int RIGHT = 1;
    }

    //////////////////////////////////////////
    // PRIVATE VAR
    //////////////////////////////////////////

    private OnSeekbarChangeListener onSeekbarChangeListener;
    private OnSeekbarFinalValueListener onSeekbarFinalValueListener;

    private float absoluteMinValue;
    private float absoluteMaxValue;
    private float minValue;
    private float maxValue;
    private float minStartValue;
    private float steps;


    private int mActivePointerId = INVALID_POINTER_ID;

    private int position;
    private int nextPosition;
    private int dataType;
    private float cornerRadius;
    private int barColor;
    private int barHighlightColor;
    private int leftThumbColor;
    private int leftThumbColorNormal;
    private int leftThumbColorPressed;
    private float barPadding;
    private float barHeight;
    private float thumbWidth;
    private float thumbHeight;
    private Drawable leftDrawable;
    private Drawable leftDrawablePressed;
    private Bitmap leftThumb;
    private Bitmap leftThumbPressed;
    private Thumb pressedThumb;
    private double normalizedMinValue = 0d;
    private double normalizedMaxValue = 100d;
    private int pointerIndex;

    private RectF _rect;
    private Paint _paint;

    private RectF rectLeftThumb;

    private boolean mIsDragging;

    //////////////////////////////////////////
    // ENUMERATION
    //////////////////////////////////////////

    protected enum Thumb{ MIN }

    //////////////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////////////

    public CrystalSeekbar(Context context) {
        this(context, null);
    }

    public CrystalSeekbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CrystalSeekbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // prevent render is in edit mode
        if(isInEditMode()) return;

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CrystalRangeSeekbar);
        try{
            cornerRadius           = getCornerRadius(array);
            minValue               = getMinValue(array);
            maxValue               = getMaxValue(array);
            minStartValue          = getMinStartValue(array);
            steps                  = getSteps(array);
            barColor               = getBarColor(array);
            barHighlightColor      = getBarHighlightColor(array);
            leftThumbColorNormal   = getLeftThumbColor(array);
            leftThumbColorPressed  = getLeftThumbColorPressed(array);
            leftDrawable           = getLeftDrawable(array);
            leftDrawablePressed    = getLeftDrawablePressed(array);
            dataType               = getDataType(array);
            position               = getPosition(array);
            nextPosition           = position;
        }
        finally {
            array.recycle();
        }

        init();
    }

    //////////////////////////////////////////
    // INITIALIZING
    //////////////////////////////////////////

    protected void init(){
        absoluteMinValue = minValue;
        absoluteMaxValue = maxValue;
        leftThumbColor = leftThumbColorNormal;
        leftThumb = getBitmap(leftDrawable);
        leftThumbPressed = getBitmap(leftDrawablePressed);
        leftThumbPressed = (leftThumbPressed == null) ? leftThumb : leftThumbPressed;

        thumbWidth  = getThumbWidth();
        thumbHeight = getThumbHeight();

        barHeight = getBarHeight();
        barPadding = getBarPadding();

        _paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        _rect = new RectF();
        rectLeftThumb = new RectF();

        pressedThumb = null;

        setMinStartValue();
    }

    //////////////////////////////////////////
    // PUBLIC METHODS
    //////////////////////////////////////////

    public CrystalSeekbar setCornerRadius(float cornerRadius){
        this.cornerRadius = cornerRadius;
        return this;
    }

    public CrystalSeekbar setMinValue(float minValue){
        this.minValue = minValue;
        this.absoluteMinValue = minValue;
        return this;
    }

    public CrystalSeekbar setMaxValue(float maxValue){
        this.maxValue = maxValue;
        this.absoluteMaxValue = maxValue;
        return this;
    }

    public CrystalSeekbar setMinStartValue(float minStartValue){
        this.minStartValue = minStartValue;
        return this;
    }

    public CrystalSeekbar setSteps(float steps){
        this.steps = steps;
        return this;
    }

    public CrystalSeekbar setBarColor(int barColor){
        this.barColor = barColor;
        return this;
    }

    public CrystalSeekbar setBarHighlightColor(int barHighlightColor){
        this.barHighlightColor = barHighlightColor;
        return this;
    }

    public CrystalSeekbar setLeftThumbColor(int leftThumbColorNormal){
        this.leftThumbColorNormal = leftThumbColorNormal;
        return this;
    }

    public CrystalSeekbar setLeftThumbHighlightColor(int leftThumbColorPressed){
        this.leftThumbColorPressed = leftThumbColorPressed;
        return this;
    }

    public CrystalSeekbar setLeftThumbDrawable(int resId){
        setLeftThumbDrawable(ContextCompat.getDrawable(getContext(), resId));
        return this;
    }

    public CrystalSeekbar setLeftThumbDrawable(Drawable drawable){
        setLeftThumbBitmap(getBitmap(drawable));
        return this;
    }

    public CrystalSeekbar setLeftThumbBitmap(Bitmap bitmap){
        leftThumb = bitmap;
        return this;
    }

    public CrystalSeekbar setLeftThumbHighlightDrawable(int resId){
        setLeftThumbHighlightDrawable(ContextCompat.getDrawable(getContext(), resId));
        return this;
    }

    public CrystalSeekbar setLeftThumbHighlightDrawable(Drawable drawable){
        setLeftThumbHighlightBitmap(getBitmap(drawable));
        return this;
    }

    public CrystalSeekbar setLeftThumbHighlightBitmap(Bitmap bitmap){
        leftThumbPressed = bitmap;
        return this;
    }

    public CrystalSeekbar setDataType(int dataType){
        this.dataType = dataType;
        return this;
    }

    public CrystalSeekbar setPosition(int pos){
        this.nextPosition = pos;
        return this;
    }

    public void setOnSeekbarChangeListener(OnSeekbarChangeListener onSeekbarChangeListener){
        this.onSeekbarChangeListener = onSeekbarChangeListener;
        if(this.onSeekbarChangeListener != null){
            this.onSeekbarChangeListener.valueChanged(getSelectedMinValue());
        }
    }

    public void setOnSeekbarFinalValueListener(OnSeekbarFinalValueListener onSeekbarFinalValueListener){
        this.onSeekbarFinalValueListener = onSeekbarFinalValueListener;
    }

    public Thumb getPressedThumb(){
        return pressedThumb;
    }

    public RectF getLeftThumbRect(){
        return rectLeftThumb;
    }

    public float getCornerRadius(){
        return cornerRadius;
    }

    public float getMinValue(){
        return minValue;
    }

    public float getMaxValue(){
        return maxValue;
    }

    public float getMinStartValue(){
        return minStartValue;
    }

    public float getSteps(){
        return steps;
    }

    public int getBarColor(){
        return barColor;
    }

    public int getBarHighlightColor(){
        return barHighlightColor;
    }

    public int getLeftThumbColor(){
        return leftThumbColor;
    }

    public int getLeftThumbColorPressed(){
        return leftThumbColorPressed;
    }

    public Drawable getLeftDrawable(){
        return leftDrawable;
    }

    public Drawable getLeftDrawablePressed(){
        return leftDrawablePressed;
    }

    public int getDataType(){
        return dataType;
    }

    public int getPosition(){
        return this.position;
    }

    public float getThumbWidth(){
        return (leftThumb != null)  ? leftThumb.getWidth() : getResources().getDimension(R.dimen.thumb_width);
    }

    public float getThumbHeight(){
        return (leftThumb != null)  ? leftThumb.getHeight() : getResources().getDimension(R.dimen.thumb_height);
    }

    public float getBarHeight(){
        return (thumbHeight * 0.5f) * 0.3f;
    }

    public float getBarPadding(){
        return thumbWidth * 0.5f;
    }

    public Number getSelectedMinValue(){
        double nv = normalizedMinValue;
        if(steps > 0 && steps <= ((absoluteMaxValue) / 2)){
            float stp = steps / (absoluteMaxValue - absoluteMinValue) * 100;
            double half_step = stp / 2;
            double mod = nv % stp;
            if(mod > half_step){
                nv = nv - mod;
                nv = nv + stp;
            }
            else{
                nv = nv - mod;
            }
        }
        else{
            if(steps != NO_STEP)
                throw new IllegalStateException("steps out of range " + steps);
        }

        nv = (position == Position.LEFT) ? nv : Math.abs(nv - maxValue);
        return formatValue(normalizedToValue(nv));
    }

    public Number getSelectedMaxValue(){

        double nv = normalizedMaxValue;
        if(steps > 0 && steps <= (absoluteMaxValue / 2)){
            float stp = steps / (absoluteMaxValue - absoluteMinValue) * 100;
            double half_step = stp / 2;
            double mod = nv % stp;
            if(mod > half_step){
                nv = nv - mod;
                nv = nv + stp;
            }
            else{
                nv = nv - mod;
            }
        }
        else{
            if(steps != NO_STEP)
                throw new IllegalStateException("steps out of range " + steps);
        }

        return formatValue(normalizedToValue(nv));
    }

    public void apply(){

        // reset normalize min and max value
        //normalizedMinValue = 0d;
        //normalizedMaxValue = 100d;

        thumbWidth  = (leftThumb != null)  ? leftThumb.getWidth()   : getResources().getDimension(R.dimen.thumb_width);
        thumbHeight = (leftThumb != null)  ? leftThumb.getHeight() : getResources().getDimension(R.dimen.thumb_height);

        barHeight = (thumbHeight * 0.5f) * 0.3f;
        barPadding = thumbWidth * 0.5f;

        // set min start value
        if(minStartValue < minValue){
            minStartValue = 0;
            setNormalizedMinValue(minStartValue);
        }
        else if(minStartValue > maxValue){
            minStartValue = maxValue;
            setNormalizedMinValue(minStartValue);
        }
        else{
            //minStartValue = (long)getSelectedMinValue();
            if(nextPosition != position){
                minStartValue = (float)Math.abs(normalizedMaxValue - normalizedMinValue);
            }
            if(minStartValue > minValue){
                minStartValue = Math.min(minStartValue, absoluteMaxValue);
                minStartValue -= absoluteMinValue;
                minStartValue = minStartValue / (absoluteMaxValue - absoluteMinValue) * 100;
            }

            setNormalizedMinValue(minStartValue);
            position = nextPosition;

        }

        invalidate();
        if (onSeekbarChangeListener != null) {
            onSeekbarChangeListener.valueChanged(getSelectedMinValue());
        }
    }

    //////////////////////////////////////////
    // PROTECTED METHODS
    //////////////////////////////////////////

    protected Bitmap getBitmap(Drawable drawable){
        return (drawable != null) ? ((BitmapDrawable) drawable).getBitmap() : null;
    }

    protected float getCornerRadius(final TypedArray typedArray){
        return typedArray.getFloat(R.styleable.CrystalRangeSeekbar_corner_radius, 0f);
    }

    protected float getMinValue(final TypedArray typedArray){
        return typedArray.getFloat(R.styleable.CrystalRangeSeekbar_min_value, 0f);
    }

    protected float getMaxValue(final TypedArray typedArray){
        return typedArray.getFloat(R.styleable.CrystalRangeSeekbar_max_value, 100f);
    }

    protected float getMinStartValue(final TypedArray typedArray){
        return typedArray.getFloat(R.styleable.CrystalRangeSeekbar_min_start_value, minValue);
    }

    protected float getSteps(final TypedArray typedArray){
        return typedArray.getFloat(R.styleable.CrystalRangeSeekbar_steps, NO_STEP);
    }

    protected int getBarColor(final TypedArray typedArray){
        return typedArray.getColor(R.styleable.CrystalRangeSeekbar_bar_color, Color.GRAY);
    }

    protected int getBarHighlightColor(final TypedArray typedArray){
        return typedArray.getColor(R.styleable.CrystalRangeSeekbar_bar_highlight_color, Color.BLACK);
    }

    protected int getLeftThumbColor(final TypedArray typedArray){
        return typedArray.getColor(R.styleable.CrystalRangeSeekbar_left_thumb_color, Color.BLACK);
    }

    protected int getLeftThumbColorPressed(final TypedArray typedArray){
        return typedArray.getColor(R.styleable.CrystalRangeSeekbar_left_thumb_color_pressed, Color.DKGRAY);
    }

    protected Drawable getLeftDrawable(final TypedArray typedArray){
        return typedArray.getDrawable(R.styleable.CrystalRangeSeekbar_left_thumb_image);
    }

    protected Drawable getLeftDrawablePressed(final TypedArray typedArray){
        return typedArray.getDrawable(R.styleable.CrystalRangeSeekbar_left_thumb_image_pressed);
    }

    protected int getDataType(final TypedArray typedArray){
        return typedArray.getInt(R.styleable.CrystalRangeSeekbar_data_type, DataType.INTEGER);
    }

    protected final int getPosition(final TypedArray typedArray){
        final int pos = typedArray.getInt(R.styleable.CrystalRangeSeekbar_position, Position.LEFT);

        normalizedMinValue = (pos == Position.LEFT) ? normalizedMinValue : normalizedMaxValue;
        return pos;
    }

    protected void setupBar(final Canvas canvas, final Paint paint, final RectF rect){
        rect.left   = barPadding;
        rect.top    = 0.5f * (getHeight() - barHeight);
        rect.right  = getWidth() - barPadding;
        rect.bottom = 0.5f * (getHeight() + barHeight);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(barColor);
        paint.setAntiAlias(true);
        drawBar(canvas, paint, rect);
    }

    protected void drawBar(final Canvas canvas, final Paint paint, final RectF rect){
        canvas.drawRoundRect(rect, cornerRadius, cornerRadius, paint);
    }

    protected void setupHighlightBar(final Canvas canvas, final Paint paint, final RectF rect){
        if(position == Position.RIGHT){
            rect.left  = normalizedToScreen(normalizedMinValue) + (getThumbWidth() / 2);
            rect.right = getWidth() - (getThumbWidth() / 2);
        }
        else{
            rect.left  = getThumbWidth() / 2;
            rect.right = normalizedToScreen(normalizedMinValue) + (getThumbWidth() / 2);
        }

        paint.setColor(barHighlightColor);
        drawHighlightBar(canvas, paint, rect);
    }

    protected void drawHighlightBar(final Canvas canvas, final Paint paint, final RectF rect){
        canvas.drawRoundRect(rect, cornerRadius, cornerRadius, paint);
    }

    protected void setupLeftThumb(final Canvas canvas, final Paint paint, final RectF rect){

        leftThumbColor = (Thumb.MIN.equals(pressedThumb)) ? leftThumbColorPressed : leftThumbColorNormal;
        paint.setColor(leftThumbColor);

        rectLeftThumb.left   = normalizedToScreen(normalizedMinValue);
        rectLeftThumb.right  = Math.min(rectLeftThumb.left + (getThumbWidth() / 2) + barPadding, getWidth());

        rectLeftThumb.top    = 0f;
        rectLeftThumb.bottom = thumbHeight;

        if(leftThumb != null){
            Bitmap lThumb = (Thumb.MIN.equals(pressedThumb)) ? leftThumbPressed : leftThumb;
            drawLeftThumbWithImage(canvas, paint, rectLeftThumb, lThumb);
        }
        else{
            drawLeftThumbWithColor(canvas, paint, rectLeftThumb);
        }
    }

    protected void drawLeftThumbWithColor(final Canvas canvas, final Paint paint, final RectF rect){
        canvas.drawOval(rect, paint);
    }

    protected void drawLeftThumbWithImage(final Canvas canvas, final Paint paint, final RectF rect, final Bitmap image){
        canvas.drawBitmap(image, rect.left, rect.top, paint);
    }

    protected void trackTouchEvent(MotionEvent event){
        final int pointerIndex = event.findPointerIndex(mActivePointerId);
        try{
            final float x = event.getX(pointerIndex);

            if (Thumb.MIN.equals(pressedThumb)) {
                setNormalizedMinValue(screenToNormalized(x));
            }
        }
        catch (Exception ignored){}
    }

    protected void touchDown(final float x, final float y){

    }

    protected void touchMove(final float x, final float y){

    }

    protected void touchUp(final float x, final float y){

    }

    protected int getMeasureSpecWith(int widthMeasureSpec){
        int width = 200;
        if (MeasureSpec.UNSPECIFIED != MeasureSpec.getMode(widthMeasureSpec)) {
            width = MeasureSpec.getSize(widthMeasureSpec);
        }
        return width;
    }

    protected int getMeasureSpecHeight(int heightMeasureSpec){
        int height = Math.round(thumbHeight);
        if (MeasureSpec.UNSPECIFIED != MeasureSpec.getMode(heightMeasureSpec)) {
            height = Math.min(height, MeasureSpec.getSize(heightMeasureSpec));
        }
        return height;
    }

    protected final void log(Object object){
        Log.d("CRS=>", String.valueOf(object));
    }

    //////////////////////////////////////////
    // PRIVATE METHODS
    //////////////////////////////////////////

    private void setMinStartValue(){
        if(minStartValue > minValue && minStartValue < maxValue){
            minStartValue = Math.min(minStartValue, absoluteMaxValue);
            minStartValue -= absoluteMinValue;
            minStartValue = minStartValue / (absoluteMaxValue - absoluteMinValue) * 100;
            setNormalizedMinValue(minStartValue);
        }
    }

    private Thumb evalPressedThumb(float touchX){
        Thumb result = null;

        boolean minThumbPressed = isInThumbRange(touchX, normalizedMinValue);
        if (minThumbPressed) {
            // if both thumbs are pressed (they lie on top of each other), choose the one with more room to drag. this avoids "stalling" the thumbs in a corner, not being able to drag them apart anymore.
            result = Thumb.MIN;
        }
        return result;
    }

    private boolean isInThumbRange(float touchX, double normalizedThumbValue){
        float thumbPos = normalizedToScreen(normalizedThumbValue);
        float left = thumbPos - (getThumbWidth() / 2);
        float right = thumbPos + (getThumbWidth() / 2);
        float x = touchX - (getThumbWidth() / 2);
        if(thumbPos > (getWidth() - thumbWidth)) x = touchX;
        return (x >= left && x <= right);
    }

    private void onStartTrackingTouch(){
        mIsDragging = true;
    }

    private void onStopTrackingTouch(){
        mIsDragging = false;
    }

    private float normalizedToScreen(double normalizedCoord){

        float width = getWidth() - (barPadding * 2);
        return (float) normalizedCoord / 100f * width;
    }

    private double screenToNormalized(float screenCoord){
        double width = getWidth();

        if (width <= 2 * barPadding) {
            // prevent division by zero, simply return 0.
            return 0d;
        } else {
            width = width - (barPadding * 2);
            double result = screenCoord / width * 100d;
            result = result - (barPadding / width * 100d);
            result = Math.min(100d, Math.max(0d, result));
            return result;

        }
    }

    private void setNormalizedMinValue(double value) {
        normalizedMinValue = Math.max(0d, Math.min(100d, Math.min(value, normalizedMaxValue)));
        invalidate();
    }

    private void setNormalizedMaxValue(double value) {
        normalizedMaxValue = Math.max(0d, Math.min(100d, Math.max(value, normalizedMinValue)));
        invalidate();
    }

    private double normalizedToValue(double normalized) {
        double val = normalized / 100 * (maxValue - minValue);
        val = (position == Position.LEFT) ? val + minValue : val;
        return val;
    }

    private void attemptClaimDrag() {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
    }

    private <T extends Number> Number formatValue(T value) throws IllegalArgumentException{
        final Double v = (Double) value;
        if (dataType == DataType.LONG) {
            return v.longValue();
        }
        if (dataType == DataType.DOUBLE) {
            return v;
        }
        if (dataType == DataType.INTEGER) {
            return Math.round(v);
        }
        if (dataType == DataType.FLOAT) {
            return v.floatValue();
        }
        if (dataType == DataType.SHORT) {
            return v.shortValue();
        }
        if (dataType == DataType.BYTE) {
            return v.byteValue();
        }
        throw new IllegalArgumentException("Number class '" + value.getClass().getName() + "' is not supported");
    }

    //////////////////////////////////////////
    // OVERRIDE METHODS
    //////////////////////////////////////////

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // prevent render is in edit mode
        if(isInEditMode()) return;

        // setup bar
        setupBar(canvas, _paint, _rect);

        // setup seek bar active range line
        setupHighlightBar(canvas, _paint ,_rect);

        // draw left thumb
        setupLeftThumb(canvas, _paint, _rect);

    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getMeasureSpecWith(widthMeasureSpec), getMeasureSpecHeight(heightMeasureSpec));
    }

    /**
     * Handles thumb selection and movement. Notifies listener callback on certain events.
     */
    @Override
    public synchronized boolean onTouchEvent(MotionEvent event) {

        if (!isEnabled())
            return false;



        final int action = event.getAction();

        switch (action & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:
                mActivePointerId = event.getPointerId(event.getPointerCount() - 1);
                pointerIndex = event.findPointerIndex(mActivePointerId);
                float mDownMotionX = event.getX(pointerIndex);

                pressedThumb = evalPressedThumb(mDownMotionX);

                if(pressedThumb == null) return super.onTouchEvent(event);

                touchDown(event.getX(pointerIndex), event.getY(pointerIndex));
                setPressed(true);
                invalidate();
                onStartTrackingTouch();
                trackTouchEvent(event);
                attemptClaimDrag();

                break;

            case MotionEvent.ACTION_MOVE:
                if (pressedThumb != null) {

                    if (mIsDragging) {
                        touchMove(event.getX(pointerIndex), event.getY(pointerIndex));
                        trackTouchEvent(event);
                    }

                    if (onSeekbarChangeListener != null) {
                        onSeekbarChangeListener.valueChanged(getSelectedMinValue());
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mIsDragging) {
                    trackTouchEvent(event);
                    onStopTrackingTouch();
                    setPressed(false);
                    touchUp(event.getX(pointerIndex), event.getY(pointerIndex));
                    if(onSeekbarFinalValueListener != null){
                        onSeekbarFinalValueListener.finalValue(getSelectedMinValue());
                    }
                } else {
                    // Touch up when we never crossed the touch slop threshold
                    // should be interpreted as a tap-seek to that location.
                    onStartTrackingTouch();
                    trackTouchEvent(event);
                    onStopTrackingTouch();
                }

                pressedThumb = null;
                invalidate();
                if (onSeekbarChangeListener != null) {
                    onSeekbarChangeListener.valueChanged(getSelectedMinValue());
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN: {
                //final int index = event.getPointerCount() - 1;
                // final int index = ev.getActionIndex();
                /*mDownMotionX = event.getX(index);
                mActivePointerId = event.getPointerId(index);
                invalidate();*/
                break;
            }
            case MotionEvent.ACTION_POINTER_UP:
                /*onSecondaryPointerUp(event);*/
                invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                if (mIsDragging) {
                    onStopTrackingTouch();
                    setPressed(false);
                    touchUp(event.getX(pointerIndex), event.getY(pointerIndex));
                }
                invalidate(); // see above explanation
                break;
        }

        return true;

    }
}
