package com.albuquerque.movietvshow.modules.shows.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.albuquerque.movietvshow.core.livedata.SingleLiveEvent
import com.albuquerque.movietvshow.core.utils.ErrorUtils
import com.albuquerque.movietvshow.modules.shows.business.ShowsBusiness
import com.albuquerque.movietvshow.modules.shows.database.ShowDatabase
import com.albuquerque.movietvshow.modules.shows.model.Show

class ShowViewModel(var id: Int): ViewModel() {

    var onError = SingleLiveEvent<String>()
    var onFavorite = SingleLiveEvent<Show>()

    lateinit var show: MutableLiveData<Show>

    fun getShow(): LiveData<Show> {

        if(!::show.isInitialized){
            show = MutableLiveData()

            ShowsBusiness.getShow(id,
                    {
                        it.isFavorite = ShowsBusiness.isShowFavorite(id)
                        show.value = it
                    },
                    {
                        onError.value = ErrorUtils.geErrorMessage(it) ?: "Erro getShow!!"
                    }
            )

        }

        return show
    }

    fun handleFavoriteClick(){

        show.value?.let { s ->

            s.isFavorite = !s.isFavorite

            ShowsBusiness.markAsFavorite(s,
                    {
                        if(s.isFavorite) {
                            ShowDatabase.salveOrUpdateAsync(s, onNext = {
                                onFavorite.value = s
                            })
                        } else {
                            ShowDatabase.removeFavorite(s.id)

                            onFavorite.value = s
                        }

                    },
                    {
                        onError.value = ErrorUtils.geErrorMessage(it) ?: "Erro fav"
                    }
            )

        }
    }

}