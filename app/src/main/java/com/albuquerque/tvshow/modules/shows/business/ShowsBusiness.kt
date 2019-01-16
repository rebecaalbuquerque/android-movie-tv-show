package com.albuquerque.tvshow.modules.shows.business

import com.albuquerque.tvshow.core.business.BaseBusiness
import com.albuquerque.tvshow.modules.shows.model.Show
import com.albuquerque.tvshow.modules.shows.network.ShowNetwork

object ShowsBusiness: BaseBusiness() {

    fun getAiringTodayFromAPI(onSuccess: (shows: List<Show>) -> Unit, onError: (error: Throwable) -> Unit){

        ShowNetwork.fetchAiringToday(
                {
                    onSuccess(it.results)
                },
                {
                    onError(it)
                }
        )

    }

    fun getPopular(onSuccess: (shows: List<Show>) -> Unit, onError: (error: Throwable) -> Unit){

        ShowNetwork.fetchPopular(
                {
                    onSuccess(it.results)
                },
                {
                    onError(it)
                }
        )

    }

    fun getTopRated(onSuccess: (shows: List<Show>) -> Unit, onError: (error: Throwable) -> Unit){

        ShowNetwork.fetchTopRated(
                {
                    onSuccess(it.results)
                },
                {
                    onError(it)
                }
        )

    }

}