package com.lukakordic.weatherapp.utils

import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.View
import com.lukakordic.weatherapp.R
import kotlinx.android.synthetic.main.pick_dialog_layout.view.*

object DialogHelper {

    fun showPickerDialog(context: Context, textSelected: () -> Unit, mapSelected: () -> Unit) {
        val dialogView = View.inflate(context, R.layout.pick_dialog_layout, null)
        val alertDialog = AlertDialog.Builder(context).create()

        with(dialogView) {
            selectText.setOnClickListener {
                textSelected()
                alertDialog.dismiss()
            }
            selectMap.setOnClickListener {
                mapSelected()
                alertDialog.dismiss()
            }
        }
        alertDialog.setView(dialogView)
        alertDialog.show()
    }
}