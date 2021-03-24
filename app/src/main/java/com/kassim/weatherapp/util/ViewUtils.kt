package com.kassim.weatherapp.util

import android.widget.ProgressBar
import android.view.View



fun ProgressBar.show(){
    visibility = View.VISIBLE
}

fun ProgressBar.hide(){
    visibility = View.INVISIBLE
}