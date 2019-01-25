package com.albuquerque.tvshow.modules.shows.network

import com.albuquerque.tvshow.core.network.BaseNetwork
import com.albuquerque.tvshow.modules.auth.model.AuthResponse
import com.albuquerque.tvshow.modules.shows.model.Category
import com.albuquerque.tvshow.modules.shows.model.Favorite
import com.albuquerque.tvshow.modules.shows.model.Image
import com.albuquerque.tvshow.modules.shows.model.Show

object ShowNetwork: BaseNetwork() {

    private val api by lazy { getRetrofitBuilder().build().create(ShowAPI::class.java) }

    fun postAsFavorite(userId: Int, favorite: Favorite, sessionId: String, onSuccess: (response: AuthResponse) -> Unit, onError: (error: Throwable) -> Unit){
        doRequest(api, onSuccess, onError){
            postFavorite(userId, favorite, sessionId, API_KEY)
        }
    }

    fun requestShow(id: Int, onSuccess: (show: Show) -> Unit, onError: (error: Throwable) -> Unit){
        doRequest(api, onSuccess, onError){
            fetchShow(id, API_KEY, "images")
        }
    }

    fun requestAiringToday(onSuccess: (shows: Category) -> Unit, onError: (error: Throwable) -> Unit){
        doRequest(api, onSuccess, onError){
            fetchAiringToday(API_KEY)
        }
    }

    fun requestPopular(onSuccess: (shows: Category) -> Unit, onError: (error: Throwable) -> Unit){
        doRequest(api, onSuccess, onError){
            fetchPopular(API_KEY)
        }
    }

    fun requestTopRated(onSuccess: (shows: Category) -> Unit, onError: (error: Throwable) -> Unit){
        doRequest(api, onSuccess, onError){
            fetchTopRated(API_KEY)
        }
    }

}