package com.albuquerque.tvshow.modules.shows.network

import com.albuquerque.tvshow.core.network.BaseNetwork
import com.albuquerque.tvshow.modules.auth.model.AuthResponse
import com.albuquerque.tvshow.modules.shows.model.Category
import com.albuquerque.tvshow.modules.shows.model.Favorite
import com.albuquerque.tvshow.modules.shows.model.Picture
import com.albuquerque.tvshow.modules.shows.model.Show

object ShowNetwork: BaseNetwork() {

    private val api by lazy { getRetrofitBuilder().build().create(ShowAPI::class.java) }

    fun postAsFavorite(userId: Int, favorite: Favorite, sessionId: String, onSuccess: (response: AuthResponse) -> Unit, onError: (error: Throwable) -> Unit){
        doRequest(api, onSuccess, onError){
            postFavorite(userId, favorite, sessionId, API_KEY)
        }
    }

    fun fetchPictures(id: Int, onSuccess: (picture: Picture) -> Unit, onError: (error: Throwable) -> Unit){
        doRequest(api, onSuccess, onError){
            fetchPictures(id, API_KEY)
        }
    }

    fun fetchShow(id: Int, onSuccess: (show: Show) -> Unit, onError: (error: Throwable) -> Unit){
        doRequest(api, onSuccess, onError){
            fetchShow(id, API_KEY)
        }
    }

    fun fetchAiringToday(onSuccess: (shows: Category) -> Unit, onError: (error: Throwable) -> Unit){
        doRequest(api, onSuccess, onError){
            fetchAiringToday(API_KEY)
        }
    }

    fun fetchPopular(onSuccess: (shows: Category) -> Unit, onError: (error: Throwable) -> Unit){
        doRequest(api, onSuccess, onError){
            fetchPopular(API_KEY)
        }
    }

    fun fetchTopRated(onSuccess: (shows: Category) -> Unit, onError: (error: Throwable) -> Unit){
        doRequest(api, onSuccess, onError){
            fetchTopRated(API_KEY)
        }
    }

}