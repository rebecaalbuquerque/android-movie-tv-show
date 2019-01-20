package com.albuquerque.tvshow.modules.shows.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.albuquerque.tvshow.core.livedata.SingleLiveEvent
import com.albuquerque.tvshow.core.utils.ErrorUtils
import com.albuquerque.tvshow.modules.shows.business.ShowsBusiness
import com.albuquerque.tvshow.modules.shows.database.ShowDatabase
import com.albuquerque.tvshow.modules.shows.model.Image
import com.albuquerque.tvshow.modules.shows.model.Show

class ShowViewModel(var id: Int): ViewModel() {

    var onError = SingleLiveEvent<String>()
    var onFavorite = SingleLiveEvent<String>()

    lateinit var show: MutableLiveData<Show>
    lateinit var pictures: MutableLiveData<List<Image>>

    fun getShow(): LiveData<Show> {

        if(!::show.isInitialized){
            show = MutableLiveData()

            ShowsBusiness.getShow(id,
                    {
                        show.value = it
                    },
                    {
                        onError.value = ErrorUtils.geErrorMessage(it) ?: "Erro!!"
                        onError.call()
                    }
            )

        }

        return show
    }

    fun getShowPictures(): MutableLiveData<List<Image>>{
        if(!::pictures.isInitialized){
            pictures = MutableLiveData()

            ShowsBusiness.getPictures(id,
                    {
                        pictures.value = it
                    },
                    {
                        onError.value = ErrorUtils.geErrorMessage(it) ?: "Erro!!"
                        onError.call()
                    }
            )

        }

        return pictures
    }

    fun handleFavoriteClick(){

        show.value?.let { s ->

            s.isFavorite = !s.isFavorite

            ShowsBusiness.markAsFavorite(s,
                    {
                        if(s.isFavorite) {
                            ShowDatabase.salveOrUpdateAsync(s, onNext = {
                                onFavorite.value = it.statusMessage
                                onFavorite.call()
                            })
                        } else {
                            ShowDatabase.removeFavorite(s.id)
                            onFavorite.value = it.statusMessage
                            onFavorite.call()
                        }

                    },
                    {
                        onError.value = ErrorUtils.geErrorMessage(it) ?: "Erro fav"
                        onError.call()
                    }
            )

        }
    }

}