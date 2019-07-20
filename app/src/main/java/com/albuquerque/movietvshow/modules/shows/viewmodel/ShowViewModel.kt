package com.albuquerque.movietvshow.modules.shows.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.albuquerque.movietvshow.core.livedata.SingleLiveEvent
import com.albuquerque.movietvshow.core.utils.ErrorUtils
import com.albuquerque.movietvshow.modules.shows.business.ShowsBusiness
import com.albuquerque.movietvshow.modules.shows.database.ShowDatabase
import com.albuquerque.movietvshow.modules.shows.model.Show

class ShowViewModel(var id: Int) : ViewModel() {

    var onError = SingleLiveEvent<String>()
    var onErrorDB = SingleLiveEvent<String>()
    var onSelectedFavorite = SingleLiveEvent<Void>()
    var onUnselectedFavorite = SingleLiveEvent<Void>()
    var onRequestStarted = SingleLiveEvent<Void>()
    var onRequestFinished = SingleLiveEvent<Void>()

    var show: MutableLiveData<Show> = MutableLiveData()

    init {
        // se ta no BD é porque é favorita
        show.value = ShowsBusiness.getShowFromDB(id)?.apply { isFavorite = true }
        updateShow()
    }

    fun updateShow() {
        onRequestStarted.call()

        ShowsBusiness.getShowFromAPI(id,
                {
                    onRequestFinished.call()

                    ShowsBusiness.getShowFromDB(id)?.let {
                        it.isFavorite = true
                        show.value = it
                    } ?: kotlin.run {
                        show.value = it
                    }
                },
                { error ->
                    onRequestFinished.call()

                    show.value?.let {
                        onError.value = ErrorUtils.geErrorMessage(error) ?: "Erro getShow!!"
                    } ?: kotlin.run {
                        onErrorDB.value = ErrorUtils.geErrorMessage(error) ?: "Erro getShow!!"
                    }
                }
        )

    }

    fun handleFavoriteClick() {

        show.value?.let { currentShow ->

            currentShow.isFavorite = !currentShow.isFavorite
            onRequestStarted.call()

            ShowsBusiness.markAsFavorite(currentShow,
                    {
                        onRequestFinished.call()

                        if (currentShow.isFavorite) {
                            ShowDatabase.salveOrUpdateAsync(currentShow, onNext = {
                                onSelectedFavorite.call()
                            })
                        } else {
                            ShowDatabase.removeFavorite(currentShow.id)
                            onUnselectedFavorite.call()
                        }

                    },
                    {
                        onRequestFinished.call()
                        onError.value = ErrorUtils.geErrorMessage(it) ?: "Erro fav"
                    }
            )

        }
    }

}