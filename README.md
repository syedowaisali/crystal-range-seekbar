[ ![Download](https://api.bintray.com/packages/syedowaisali/maven/crystalrangeseekbar/images/download.svg) ](https://bintray.com/syedowaisali/maven/crystalrangeseekbar/_latestVersion) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Crystal%20Range%20Seekbar-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/3996) [![Coding Signals](https://img.shields.io/badge/Coding%20Signals-Crystal%20Range%20Seekbar-orange.svg)](http://codingsignals.com/crystal-range-seekbar-in-android/)



# Crystal Range Seekbar

An extended version of seekbar and range seekbar with basic and advanced customization.

![alt tag](https://drive.google.com/uc?export=view&id=0B9bDENyIABT6cnh3MXY3TWstQWM)

# Usage
Add a dependency to your `build.gradle`:
```groovy
dependencies {
    compile 'com.crystal:crystalrangeseekbar:1.1.1'
}
```

# Features
- Customize with xml using custom handy attributes.
- Customize in your activity, fragment or dialog.
- Styling with your own widget.
- Creating newly widget from activity, fragment or dialog.

# Sample usage - Seekbar
![alt tag](https://drive.google.com/uc?export=view&id=0B9bDENyIABT6eFZkcFZKbWUxY1E)

Default style using xml.
```groovy
<com.crystal.crystalrangeseekbar.widgets.CrystalSeekbar
    android:id="@+id/rangeSeekbar1"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```
---
![alt tag](https://drive.google.com/uc?export=view&id=0B9bDENyIABT6Snk1Q21TbjhkWjQ)

Styling with bubble animation using custom widget `BubbleThumbSeekbar`.
```groovy
<com.crystal.crystalrangeseekbar.widgets.BubbleThumbSeekbar
    android:id="@+id/rangeSeekbar2"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:corner_radius="10"
    app:min_value="50"
    app:max_value="150"
    app:bar_color="#C69E89"
    app:bar_highlight_color="#A54B17"
    app:left_thumb_color="#775E4F"
    app:left_thumb_color_pressed="#4C2D1A"
    app:data_type="_integer"/>
```
---
![alt tag](https://drive.google.com/uc?export=view&id=0B9bDENyIABT6cHBraW9fUDBMaEU)

Styling with bubble animation with drawable using custom widget `BubbleThumbSeekbar`.
```groovy
<com.crystal.crystalrangeseekbar.widgets.BubbleThumbSeekbar
    android:id="@+id/rangeSeekbar3"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:corner_radius="10"
    app:min_value="0"
    app:max_value="100"
    app:steps="5"
    app:bar_color="#F7BB88"
    app:bar_highlight_color="#E07416"
    app:left_thumb_image="@drawable/thumb"
    app:left_thumb_image_pressed="@drawable/thumb_pressed"
    app:data_type="_integer"/>
```                    
---
![alt tag](https://drive.google.com/uc?export=view&id=0B9bDENyIABT6c0FnSDlVYnJyNVE)

Right to Left position (rtl)
```groovy
<com.crystal.crystalrangeseekbar.widgets.CrystalSeekbar
    android:id="@+id/rangeSeekbar7"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:position="right"/>
```                    
---
![alt tag](https://drive.google.com/uc?export=view&id=0B9bDENyIABT6RncwVndkSFFqMFE)

Right to Left position with drawable position update from code (rtl)
```groovy
<com.crystal.crystalrangeseekbar.widgets.CrystalSeekbar
    android:id="@+id/rangeSeekbar8"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:min_value="100"
    app:max_value="200"
    app:steps="5"
    app:bar_color="#F7BB88"
    app:bar_highlight_color="#E07416"
    app:left_thumb_image="@drawable/thumb"
    app:left_thumb_image_pressed="@drawable/thumb_pressed"
    app:data_type="_integer"/>
```                    
```java
// get seekbar from view
final CrystalSeekbar rangeSeekbar = (CrystalSeekbar) rootView.findViewById(R.id.rangeSeekbar8);

// change position left to right
rangeSeekbar.setPosition(CrystalSeekbar.Position.RIGHT).apply();
```
---
![alt tag](https://drive.google.com/uc?export=view&id=0B9bDENyIABT6eFZkcFZKbWUxY1E)

Create new seekbar from code and add to any view.
```java
// get seekbar from view
final CrystalSeekbar seekbar = new CrystalSeekbar(getActivity());

// get min and max text view
final TextView tvMin = (TextView) rootView.findViewById(R.id.textMin5);
final TextView tvMax = (TextView) rootView.findViewById(R.id.textMax5);

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
        
// get range seekbar container
RelativeLayout container = (RelativeLayout) rootView.findViewById(R.id.contRangeSeekbar5);
container.addView(rangeSeekbar);
```
---
![alt tag](https://drive.google.com/uc?export=view&id=0B9bDENyIABT6NloxcEJOVTJzQ00)

Styling with create custom widget
```java
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
}
```

# Sample usage - Range Seekbar
![alt tag](https://drive.google.com/uc?export=view&id=0B9bDENyIABT6Q19QMzhoZzZubFE)

Default style using xml.
```groovy
<com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar
    android:id="@+id/rangeSeekbar1"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```

```java
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
```
---
![alt tag](https://drive.google.com/uc?export=view&id=0B9bDENyIABT6UW04d01ORk1Ob0E)

Styling with bubble animation with drawable using custom widget `BubbleThumbRangeSeekbar`.
```groovy
<com.crystal.crystalrangeseekbar.widgets.BubbleThumbRangeSeekbar
    android:id="@+id/rangeSeekbar5"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:corner_radius="10"
    app:min_value="0"
    app:max_value="100"
    app:steps="5"
    app:bar_color="#F7BB88"
    app:bar_highlight_color="#E07416"
    app:left_thumb_image="@drawable/thumb"
    app:right_thumb_image="@drawable/thumb"
    app:left_thumb_image_pressed="@drawable/thumb_pressed"
    app:right_thumb_image_pressed="@drawable/thumb_pressed"
    app:data_type="_integer"/>
```
---
![alt tag](https://drive.google.com/uc?export=view&id=0B9bDENyIABT6bnZJWVZwUGV6TGc)

Set minimum range (gap).
```groovy
<com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar
    android:id="@+id/rangeSeekbar3"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:corner_radius="10"
    app:min_value="50"
    app:max_value="100"
    app:gap="20"
    app:bar_color="#8990C4"
    app:bar_highlight_color="#2434AD"
    app:left_thumb_color="#1A246D"
    app:right_thumb_color="#1A246D"
    app:left_thumb_color_pressed="#030B47"
    app:right_thumb_color_pressed="#030B47"
    app:data_type="_integer"/>
```
---
![alt tag](https://drive.google.com/uc?export=view&id=0B9bDENyIABT6eXRMQlkzNjA5cDg)

Set fix range (gap).
```groovy
<com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar
    android:id="@+id/rangeSeekbar4"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:corner_radius="10"
    app:min_value="0"
    app:max_value="100"
    app:fix_gap="30"
    app:bar_color="#EE88F7"
    app:bar_highlight_color="#D810EA"
    app:left_thumb_color="#8D0D99"
    app:right_thumb_color="#8D0D99"
    app:left_thumb_color_pressed="#56005E"
    app:right_thumb_color_pressed="#56005E"
    app:data_type="_integer"/>
```
---

__Available attributes__

+ ``corner_radius``: corner radius to be used seekbar, default ``0f``
+ ``min_value``: minimum value of seekbar, default ``0``
+ ``max_value``: maximum value of seekbar, default ``100``
+ ``min_start_value``: minimum start value must be equal or greater than min value, default ``min_value``
+ ``max_start_value``: maximum start value must be equal or less than max value, default ``max_value``
+ ``steps``: minimum steps between range, default NO_STEP ``-1f``
+ ``gap``: maintain minimum range between two thumbs, range must be greater >= min value && <= max value, default ``0f``
+ ``fix_gap``: maintain fix range between two thumbs, range must be greater >= min value && <= max value, default NO_FIXED_GAP ``-1f``
+ ``bar_color`` inactive bar background color, default ``Color.GRAY``
+ ``bar_highlight_color`` active bar background color, default ``Color.BLACK``
+ ``left_thumb_color`` default left thumb color, default ``Color.BLACK``
+ ``left_thumb_color_pressed`` active left thumb color, default ``Color.DKGRAY``
+ ``left_thumb_image`` left thumb drawable, default ``null``
+ ``left_thumb_image_pressed`` active left thumb drawable, default ``null``
+ ``right_thumb_color`` default right thumb color, default ``Color.BLACK``
+ ``right_thumb_color_pressed`` active right thumb color, default ``Color.DKGRAY``
+ ``right_thumb_image`` right thumb drawable, default ``null``
+ ``right_thumb_image_pressed`` active right thumb drawable, default ``null``
+ ``position`` can be ``left`` or ``right``, default ``left``
+ ``data_type`` can be ``_long`` or ``_double`` or ``_integer`` or ``_float`` or ``_short`` or ``_byte``, default ``_integer``

## Changelog

##### 1.1.2 - 24-Aug-2016
- Bug fixed when update property programmatically.

##### 1.1.1 - 21-Aug-2016
- Added new feature. ```OnRangeSeekbarFinalValueListener, OnSeekbarFinalValueListener```.

##### 1.0.1 - 9-Aug-2016
- Bug fixed setMinStartValue and setMaxStartValue programmatically.

##### 1.0.0 - 17-July-2016
- Add New Project.

# LICENSE

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

##Authors

* [Syed Owais Ali](https://github.com/syedowaisali) *original Author*
