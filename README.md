# Crystal Range Seekbar

An extended version of seekbar and range seekbar with basic and advanced customization.

![alt tag](https://drive.google.com/uc?export=view&id=0B9bDENyIABT6cnh3MXY3TWstQWM)

# Usage
Add a dependency to your `build.gradle`:
```groovy
dependencies {
    compile 'com.crystal:crystalrangeseekbar:1.0.0'
}
```

# Features
- Customize with xml using custom handy attributes.
- Customize in your activity, fragment or dialog.
- Styling with your own widget.
- Creating newly widget from activity, fragment or dialog.

# Sample usage
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
```
