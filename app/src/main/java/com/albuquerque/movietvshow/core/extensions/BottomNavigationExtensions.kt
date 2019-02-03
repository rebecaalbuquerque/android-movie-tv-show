package com.albuquerque.movietvshow.core.extensions

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

fun Fragment.openFragment(supportFragmentManager: FragmentManager, container: Int){
    supportFragmentManager.beginTransaction().replace(container, this).commit()
}