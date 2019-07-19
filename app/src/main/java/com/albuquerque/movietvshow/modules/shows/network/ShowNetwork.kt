package com.albuquerque.movietvshow.modules.shows.network

import com.albuquerque.movietvshow.core.network.BaseNetwork
import com.albuquerque.movietvshow.modules.auth.model.AuthResponse
import com.albuquerque.movietvshow.modules.shows.model.Category
import com.albuquerque.movietvshow.modules.shows.model.Favorite
import com.albuquerque.movietvshow.modules.shows.model.Show

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

    fun requestAiringToday(page:Int, onSuccess: (shows: Category) -> Unit, onError: (error: Throwable) -> Unit){
        doRequest(api, onSuccess, onError){
            fetchAiringToday(API_KEY, page)
        }
    }

    fun requestPopular(page:Int, onSuccess: (shows: Category) -> Unit, onError: (error: Throwable) -> Unit){
        doRequest(api, onSuccess, onError){
            fetchPopular(API_KEY, page)
        }
    }

    fun requestTopRated(page:Int, onSuccess: (shows: Category) -> Unit, onError: (error: Throwable) -> Unit){
        doRequest(api, onSuccess, onError){
            fetchTopRated(API_KEY, page)
        }
    }

    fun requestFavorites(id:Int, sessionId: String, page:Int, onSuccess: (shows: Category) -> Unit, onError: (error: Throwable) -> Unit){
        doRequest(api, onSuccess, onError){
            fetchFavorites(id, sessionId, API_KEY, page)
        }
    }

}