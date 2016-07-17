package com.crystal.crystalrangeseekbar.widgets;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.animation.OvershootInterpolator;

import com.example.crystalrangeseekbar.R;

/**
 * Created by owais.ali on 7/12/2016.
 */
public class BubbleThumbRangeSeekbar extends CrystalRangeSeekbar {

    //////////////////////////////////////////
    // PRIVATE CONSTANTS
    //////////////////////////////////////////

    //private static final float BUBBLE_WITH   = 200f;
    //private static final float BUBBLE_HEIGHT = 200f;

    //////////////////////////////////////////
    // PRIVATE VAR
    //////////////////////////////////////////

    private boolean animate;
    private boolean isPressedLeftThumb;
    private boolean isPressedRightThumb;
    private BubbleRect thumbPressedRect;

    //////////////////////////////////////////
    // CONSTRUCTOR
    //////////////////////////////////////////

    public BubbleThumbRangeSeekbar(Context context) {
        super(context);
    }

    public BubbleThumbRangeSeekbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BubbleThumbRangeSeekbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //////////////////////////////////////////
    // INITIALIZATION
    //////////////////////////////////////////

    @Override
    protected void init(){
        thumbPressedRect = new BubbleRect();
        super.init();
    }

    //////////////////////////////////////////
    // OVERRIDE METHODS
    //////////////////////////////////////////

    @Override
    protected void touchDown(float x, float y) {
        super.touchDown(x, y);

        animate = true;
        if(Thumb.MIN.equals(getPressedThumb())){
            isPressedLeftThumb = true;
            startAnimationUp(Thumb.MIN);
        }
        else if(Thumb.MAX.equals(getPressedThumb())){
            isPressedRightThumb = true;
            startAnimationUp(Thumb.MAX);
        }
    }

    @Override
    protected void touchUp(float x, float y) {
        super.touchUp(x, y);

        animate = true;
        if(Thumb.MIN.equals(getPressedThumb())){
            startAnimationDown(Thumb.MIN);
        }
        else{
            startAnimationDown(Thumb.MAX);
        }
    }

    @Override
    protected void drawLeftThumbWithColor(Canvas canvas, Paint paint, RectF rect) {
        if(isPressedLeftThumb){

            if(! animate){
                rect.left   = rect.left - ((getBubbleWith() / 2) - (getThumbWidth() / 2));
                rect.right  = rect.left + getBubbleWith();
                rect.top    = getLeftThumbRect().top - ((getBubbleHeight() / 2) - (getThumbHeight() / 2));
                rect.bottom = getLeftThumbRect().bottom + ((getBubbleHeight() / 2) - (getThumbHeight() / 2));
            }
            else{

                rect.left   = thumbPressedRect.left;
                rect.right  = thumbPressedRect.right;
                rect.top    = thumbPressedRect.top;
                rect.bottom = thumbPressedRect.bottom;
            }

            canvas.drawOval(rect, paint);
        }
        else {
            canvas.drawOval(rect, paint);
        }
    }

    @Override
    protected void drawRightThumbWithColor(Canvas canvas, Paint paint, RectF rect) {
        if(isPressedRightThumb){

            if(! animate){
                rect.left   = rect.left - ((getBubbleWith() / 2) - (getThumbWidth() / 2));
                rect.right  = rect.left + getBubbleWith();
                rect.top    = getRightThumbRect().top - ((getBubbleHeight() / 2) - (getThumbHeight() / 2));
                rect.bottom = getRightThumbRect().bottom + ((getBubbleHeight() / 2) - (getThumbHeight() / 2));
            }
            else{

                rect.left   = thumbPressedRect.left;
                rect.right  = thumbPressedRect.right;
                rect.top    = thumbPressedRect.top;
                rect.bottom = thumbPressedRect.bottom;
            }

            canvas.drawOval(rect, paint);
        }
        else {
            canvas.drawOval(rect, paint);
        }
    }

    @Override
    protected void drawLeftThumbWithImage(Canvas canvas, Paint paint, RectF rect, Bitmap image) {
        if(isPressedLeftThumb){

            if(! animate){
                image = resizeImage((int) getBubbleWith(), (int) getBubbleHeight(), image);
                rect.top = getLeftThumbRect().top - ((getBubbleHeight() / 2) - (getThumbHeight() / 2));
                rect.left = rect.left - ((getBubbleWith() / 2) - (getThumbWidth() / 2));
            }
            else{
                image = resizeImage((int) thumbPressedRect.imageWith, (int) thumbPressedRect.imageHeight, image);
                rect.top = thumbPressedRect.top;
                rect.left = thumbPressedRect.left;
            }

            canvas.drawBitmap(image, rect.left, rect.top, paint);
        }
        else{
            canvas.drawBitmap(image, rect.left, rect.top, paint);
        }
    }

    @Override
    protected void drawRightThumbWithImage(Canvas canvas, Paint paint, RectF rect, Bitmap image) {
        if(isPressedRightThumb){

            if(! animate){
                image = resizeImage((int) getBubbleWith(), (int) getBubbleHeight(), image);
                rect.top = getRightThumbRect().top - ((getBubbleHeight() / 2) - (getThumbHeight() / 2));
                rect.left = rect.left - ((getBubbleWith() / 2) - (getThumbWidth() / 2));
            }
            else{
                image = resizeImage((int) thumbPressedRect.imageWith, (int) thumbPressedRect.imageHeight, image);
                rect.top = thumbPressedRect.top;
                rect.left = thumbPressedRect.left;
            }

            canvas.drawBitmap(image, rect.left, rect.top, paint);
        }
        else{
            canvas.drawBitmap(image, rect.left, rect.top, paint);
        }
    }

    //////////////////////////////////////////
    // PROTECTED METHODS
    //////////////////////////////////////////

    protected float getBubbleWith(){
        return getResources().getDimension(R.dimen.bubble_thumb_width);
    }

    protected float getBubbleHeight(){
        return getResources().getDimension(R.dimen.bubble_thumb_height);
    }

    protected void startAnimationUp(final Thumb thumb){

        BubbleRect toRect = new BubbleRect();
        RectF fromRect;

        // left thumb
        if(Thumb.MIN.equals(thumb)){
            fromRect = getLeftThumbRect();
        }
        else{
            fromRect = getRightThumbRect();
        }

        toRect.left        = fromRect.left - ((getBubbleWith() / 2) - (getThumbWidth() / 2));
        toRect.right       = toRect.left + getBubbleWith();
        toRect.top         = fromRect.top - ((getBubbleHeight() / 2) - (getThumbHeight() / 2));
        toRect.bottom      = fromRect.bottom + ((getBubbleHeight() / 2) - (getThumbHeight() / 2));

        PropertyValuesHolder leftValueHolder        = PropertyValuesHolder.ofFloat("left",   fromRect.left,    toRect.left);
        PropertyValuesHolder rightValueHolder       = PropertyValuesHolder.ofFloat("right",  fromRect.right,   toRect.right);
        PropertyValuesHolder topValueHolder         = PropertyValuesHolder.ofFloat("top",    fromRect.top,     toRect.top);
        PropertyValuesHolder bottomValueHolder      = PropertyValuesHolder.ofFloat("bottom", fromRect.bottom,  toRect.bottom);
        PropertyValuesHolder imageWithValueHolder   = PropertyValuesHolder.ofFloat("width",  getThumbWidth(),  getBubbleWith());
        PropertyValuesHolder imageHeightValueHolder = PropertyValuesHolder.ofFloat("height", getThumbHeight(), getBubbleHeight());

        ValueAnimator animation = ValueAnimator.ofPropertyValuesHolder(leftValueHolder, rightValueHolder, topValueHolder, bottomValueHolder, imageWithValueHolder, imageHeightValueHolder);
        animation.setDuration(200);
        animation.setInterpolator(new OvershootInterpolator(5f));
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                thumbPressedRect.left        = (float)animation.getAnimatedValue("left");
                thumbPressedRect.right       = (float)animation.getAnimatedValue("right");
                thumbPressedRect.top         = (float)animation.getAnimatedValue("top");
                thumbPressedRect.bottom      = (float)animation.getAnimatedValue("bottom");
                thumbPressedRect.imageWith   = (float)animation.getAnimatedValue("width");
                thumbPressedRect.imageHeight = (float)animation.getAnimatedValue("height");
                invalidate();
            }
        });
        animation.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animate = false;
            }
        }, 200);
    }

    protected void startAnimationDown(Thumb thumb){

        RectF toRect = new RectF();
        RectF fromRect;

        // left thumb
        if(Thumb.MIN.equals(thumb)){
            fromRect = getLeftThumbRect();
        }
        else{
            fromRect = getRightThumbRect();
        }

        toRect.left   = fromRect.left + ((getBubbleWith() / 2) - (getThumbWidth() / 2));
        toRect.right  = toRect.left + getThumbWidth();
        toRect.top    = 0f;
        toRect.bottom = getThumbHeight();

        PropertyValuesHolder leftValueHolder        = PropertyValuesHolder.ofFloat("left",   fromRect.left,     toRect.left);
        PropertyValuesHolder rightValueHolder       = PropertyValuesHolder.ofFloat("right",  fromRect.right,    toRect.right);
        PropertyValuesHolder topValueHolder         = PropertyValuesHolder.ofFloat("top",    fromRect.top,      toRect.top);
        PropertyValuesHolder bottomValueHolder      = PropertyValuesHolder.ofFloat("bottom", fromRect.bottom,   toRect.bottom);
        PropertyValuesHolder imageWithValueHolder   = PropertyValuesHolder.ofFloat("width",  getBubbleWith(),   getThumbWidth());
        PropertyValuesHolder imageHeightValueHolder = PropertyValuesHolder.ofFloat("height", getBubbleHeight(), getThumbHeight());

        ValueAnimator animation = ValueAnimator.ofPropertyValuesHolder(leftValueHolder, rightValueHolder, topValueHolder, bottomValueHolder, imageWithValueHolder, imageHeightValueHolder);
        animation.setDuration(300);
        animation.setInterpolator(new OvershootInterpolator(3f));
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                thumbPressedRect.left   = (float)animation.getAnimatedValue("left");
                thumbPressedRect.right  = (float)animation.getAnimatedValue("right");
                thumbPressedRect.top    = (float)animation.getAnimatedValue("top");
                thumbPressedRect.bottom = (float)animation.getAnimatedValue("bottom");
                thumbPressedRect.imageWith   = (float)animation.getAnimatedValue("width");
                thumbPressedRect.imageHeight = (float)animation.getAnimatedValue("height");
                invalidate();
            }
        });
        animation.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animate = false;
                isPressedLeftThumb  = false;
                isPressedRightThumb = false;
            }
        }, 300);
    }

    //////////////////////////////////////////
    // PUBLIC METHODS
    //////////////////////////////////////////


    //////////////////////////////////////////
    // PRIVATE METHODS
    //////////////////////////////////////////

    private Bitmap resizeImage( int iconWidth, int iconHeight, Bitmap bmp) {


        int width = bmp.getWidth();
        int height = bmp.getHeight();

        // calculate the scale
        float scaleWidth = ((float) iconWidth) / width;
        float scaleHeight = ((float) iconHeight) / height;

        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the Bitmap
        matrix.postScale(scaleWidth, scaleHeight);

        // if you want to rotate the Bitmap
        // matrix.postRotate(45);

        // recreate the new Bitmap

        // make a Drawable from Bitmap to allow to set the Bitmap
        // to the ImageView, ImageButton or what ever
        return Bitmap.createBitmap(bmp, 0, 0, width, height, matrix, true);

    }

    //////////////////////////////////////////
    // PRIVATE CLASS
    //////////////////////////////////////////

    private class BubbleRect{
        public float left;
        public float right;
        public float top;
        public float bottom;
        public float imageWith;
        public float imageHeight;
    }

}
