package com.albuquerque.tvshow.core.extensions

import android.support.v7.app.ActionBar

fun ActionBar.setTitleAndBackButton(newTitle: String, allowGoBack: Boolean = true){
    this.title = newTitle
    this.setDisplayHomeAsUpEnabled(allowGoBack)
}