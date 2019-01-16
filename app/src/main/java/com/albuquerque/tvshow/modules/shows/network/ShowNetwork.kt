package com.albuquerque.tvshow.modules.shows.network

import com.albuquerque.tvshow.core.network.BaseNetwork
import com.albuquerque.tvshow.modules.shows.model.ShowListResponse

object ShowNetwork: BaseNetwork() {

    private val api by lazy { getRetrofitBuilder().build().create(ShowAPI::class.java) }

    fun fetchAiringToday(onSuccess: (shows: ShowListResponse) -> Unit, onError: (error: Throwable) -> Unit){
        doRequest(api, onSuccess, onError){
            fetchAiringToday(API_KEY)
        }
    }

    fun fetchPopular(onSuccess: (shows: ShowListResponse) -> Unit, onError: (error: Throwable) -> Unit){
        doRequest(api, onSuccess, onError){
            fetchPopular(API_KEY)
        }
    }

    fun fetchTopRated(onSuccess: (shows: ShowListResponse) -> Unit, onError: (error: Throwable) -> Unit){
        doRequest(api, onSuccess, onError){
            fetchTopRated(API_KEY)
        }
    }

}