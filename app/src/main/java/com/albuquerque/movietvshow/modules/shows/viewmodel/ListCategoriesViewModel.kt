package com.albuquerque.movietvshow.modules.shows.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.albuquerque.movietvshow.core.livedata.SingleLiveEvent
import com.albuquerque.movietvshow.core.utils.ErrorUtils
import com.albuquerque.movietvshow.modules.shows.business.ShowsBusiness
import com.albuquerque.movietvshow.modules.shows.model.Category

class ListCategoriesViewModel: ViewModel() {

    var onError = SingleLiveEvent<String>()
    var onRequestStarted = SingleLiveEvent<Void>()
    var onRequestFinished = SingleLiveEvent<Void>()

    var categories: MutableLiveData<List<Category>> = MutableLiveData()

    init {
        updateCategories()
    }

    fun updateCategories() {
        onRequestStarted.call()

        ShowsBusiness.getCategories(
                {
                    categories.value = it
                    onRequestFinished.call()
                },
                {
                    onError.value = ErrorUtils.geErrorMessage(it) ?: "Erro getCategories"
                    onRequestFinished.call()
                }
        )
    }

}