package com.albuquerque.tvshow.modules.shows.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.albuquerque.tvshow.core.livedata.SingleLiveEvent
import com.albuquerque.tvshow.modules.auth.utils.AuthUtils
import com.albuquerque.tvshow.modules.shows.business.ShowsBusiness
import com.albuquerque.tvshow.modules.shows.model.CategoryShow
import com.albuquerque.tvshow.modules.shows.model.Show

class ListShowsViewModel: ViewModel() {

    var onError = SingleLiveEvent<String>()
    var onShowClicked = SingleLiveEvent<Void>()

    private lateinit var categories: MutableLiveData<List<CategoryShow>>

    fun getCategories(): LiveData<List<CategoryShow>> {

        if (!::categories.isInitialized) {
            categories = MutableLiveData()
            ShowsBusiness.getCategories(
                    {
                        categories.value = it
                    },
                    {
                        onError.value = AuthUtils.geErrorMessage(it) ?: "Erro!!"
                        onError.call()
                    }
            )


        }

        return categories
    }

    fun handleShowClick(item: Show){

    }

}