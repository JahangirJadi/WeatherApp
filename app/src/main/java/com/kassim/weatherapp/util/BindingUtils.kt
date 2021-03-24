package com.kassim.weatherapp.util

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.kassim.weatherapp.R

@BindingAdapter("image")
 fun loadImage(view:ImageView, image:Int){


    val drawableName = "weather_icon$image"

    val resId = view.resources.getIdentifier(drawableName, "drawable","com.kassim.weatherapp")

    view.setImageResource(resId)

}


