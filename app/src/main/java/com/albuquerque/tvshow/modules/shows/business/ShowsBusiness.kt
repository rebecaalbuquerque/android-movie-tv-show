package com.albuquerque.tvshow.modules.shows.business

import com.albuquerque.tvshow.core.business.BaseBusiness
import com.albuquerque.tvshow.modules.shows.model.CategoryShow
import com.albuquerque.tvshow.modules.shows.model.Show
import com.albuquerque.tvshow.modules.shows.network.ShowNetwork

object ShowsBusiness : BaseBusiness() {

    fun getShow(id: Int, onSuccess: (show: Show) -> Unit, onError: (error: Throwable) -> Unit){
        ShowNetwork.fetchShow(id,
                {
                    onSuccess(it)
                },
                {
                    onError(it)
                }
        )
    }

    fun getCategories(onSuccess: (categories: List<CategoryShow>) -> Unit, onError: (error: Throwable) -> Unit) {
        val result = mutableListOf<CategoryShow>()

        getAiringTodayFromAPI({ today ->

            result.add(CategoryShow("Em exibição hoje", today))

            getPopular({ popular ->
                result.add(CategoryShow("Populares", popular))

                getTopRated({ topRated ->
                    result.add(CategoryShow("Melhores avaliadas", topRated))

                    onSuccess(result)

                },
                        { onError(it) })

            },
                    { onError(it) })

        },
                { onError(it) })


    }

    private fun getAiringTodayFromAPI(onSuccess: (shows: List<Show>) -> Unit, onError: (error: Throwable) -> Unit) {

        ShowNetwork.fetchAiringToday(
                {
                    onSuccess(it.results)
                },
                {
                    onError(it)
                }
        )

    }

    private fun getPopular(onSuccess: (shows: List<Show>) -> Unit, onError: (error: Throwable) -> Unit) {

        ShowNetwork.fetchPopular(
                {
                    onSuccess(it.results)
                },
                {
                    onError(it)
                }
        )

    }

    private fun getTopRated(onSuccess: (shows: List<Show>) -> Unit, onError: (error: Throwable) -> Unit) {

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