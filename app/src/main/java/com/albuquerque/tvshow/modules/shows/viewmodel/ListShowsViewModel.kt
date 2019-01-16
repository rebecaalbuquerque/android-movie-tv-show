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
        val airingToday = requestAiringToday()
        val popular = requestPopular()
        val topRated = requestTopRated()

        if (!::categories.isInitialized) {
            categories = MutableLiveData()
            categories.value = listOf( CategoryShow("Em exibição hoje", airingToday), CategoryShow("Populares", popular), CategoryShow("Melhores avaliadas", topRated) )
        }

        return categories
    }

    fun handleShowClick(item: Show){

    }

    private fun requestAiringToday(): List<Show> {
        var result = listOf<Show>()

        ShowsBusiness.getAiringTodayFromAPI(
                {
                    result = it
                },
                {
                    onError.value = AuthUtils.geErrorMessage(it) ?: "Erro!!!"
                    onError.call()
                }
        )

        return result
    }

    private fun requestPopular(): List<Show> {
        var result = listOf<Show>()

        ShowsBusiness.getPopular(
                {
                    result = it
                },
                {
                    onError.value = AuthUtils.geErrorMessage(it) ?: "Erro!!!"
                    onError.call()
                }
        )

        return result
    }

    private fun requestTopRated(): List<Show> {
        var result = listOf<Show>()

        ShowsBusiness.getTopRated(
                {
                    result = it
                },
                {
                    onError.value = AuthUtils.geErrorMessage(it) ?: "Erro!!!"
                    onError.call()
                }
        )

        return result
    }

}