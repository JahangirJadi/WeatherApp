package com.kassim.weatherapp.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.kassim.weatherapp.R

@BindingAdapter("image")
 fun loadImage(view:ImageView, image:Drawable){

    val drawable = ContextCompat.getDrawable(view.context,R.drawable.weather_icon2)
    view.setImageDrawable(drawable)

}


