package com.albuquerque.movietvshow.modules.shows.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.albuquerque.movietvshow.modules.shows.enum.TypeCategory

class ListShowsViewModelFactory(private val category: TypeCategory) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListShowsViewModel(category) as T
    }

}