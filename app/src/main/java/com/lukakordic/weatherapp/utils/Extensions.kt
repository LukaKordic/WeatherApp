package com.lukakordic.weatherapp.utils

import android.support.v4.app.Fragment
import android.widget.Toast

fun Fragment.toast(message: String) {
    Toast.makeText(this.activity, message, Toast.LENGTH_LONG).show()
}