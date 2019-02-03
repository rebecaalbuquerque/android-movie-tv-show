package com.albuquerque.movietvshow.modules.shows.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ShowViewModelFactory (private val id: Int) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShowViewModel(id) as T
    }

}