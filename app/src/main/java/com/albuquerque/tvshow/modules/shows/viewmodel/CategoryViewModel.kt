package com.albuquerque.tvshow.modules.shows.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.albuquerque.tvshow.core.livedata.SingleLiveEvent
import com.albuquerque.tvshow.core.utils.ErrorUtils
import com.albuquerque.tvshow.modules.shows.business.ShowsBusiness
import com.albuquerque.tvshow.modules.shows.model.Category

class CategoryViewModel: ViewModel() {

    var onError = SingleLiveEvent<String>()

    private lateinit var categories: MutableLiveData<List<Category>>

    fun getCategories(): LiveData<List<Category>> {

        if (!::categories.isInitialized) {
            categories = MutableLiveData()
            ShowsBusiness.getCategories(
                    {
                        categories.value = it
                    },
                    {
                        onError.value = ErrorUtils.geErrorMessage(it) ?: "Erro!!"
                        onError.call()
                    }
            )


        }

        return categories
    }

    fun getCategoriesAgain(){ getCategories() }

}