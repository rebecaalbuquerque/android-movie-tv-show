package com.albuquerque.tvshow.modules.shows.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.albuquerque.tvshow.core.livedata.SingleLiveEvent
import com.albuquerque.tvshow.core.utils.ErrorUtils
import com.albuquerque.tvshow.modules.shows.business.ShowsBusiness
import com.albuquerque.tvshow.modules.shows.model.Category

class ListCategoriesViewModel: ViewModel() {

    var onError = SingleLiveEvent<String>()

    private lateinit var categories: MutableLiveData<List<Category>>

    fun getCategories(): MutableLiveData<List<Category>> {

        if (!::categories.isInitialized) {
            categories = MutableLiveData()
            ShowsBusiness.getCategories(
                    {
                        categories.value = it
                    },
                    {
                        onError.value = ErrorUtils.geErrorMessage(it) ?: "Erro getCategories"
                    }
            )


        }

        return categories
    }

}