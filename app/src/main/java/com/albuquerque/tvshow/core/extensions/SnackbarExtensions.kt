package com.albuquerque.tvshow.core.extensions

import android.graphics.Color
import android.support.design.widget.Snackbar

fun Snackbar.showError(){
    this.view.setBackgroundColor(Color.parseColor("#e60000"))
    return this.show()
}