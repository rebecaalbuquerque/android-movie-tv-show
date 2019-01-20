package com.albuquerque.tvshow.modules.shows.business

import com.albuquerque.tvshow.core.business.BaseBusiness
import com.albuquerque.tvshow.modules.auth.business.AuthBusiness
import com.albuquerque.tvshow.modules.auth.model.AuthResponse
import com.albuquerque.tvshow.modules.shows.database.ShowDatabase
import com.albuquerque.tvshow.modules.shows.model.Category
import com.albuquerque.tvshow.modules.shows.model.Favorite
import com.albuquerque.tvshow.modules.shows.model.Image
import com.albuquerque.tvshow.modules.shows.model.Show
import com.albuquerque.tvshow.modules.shows.network.ShowNetwork

object ShowsBusiness : BaseBusiness() {

    fun markAsFavorite(show: Show, onSuccess: (response: AuthResponse) -> Unit, onError: (error: Throwable) -> Unit){
        val user = AuthBusiness.getUser()!!

        ShowNetwork.postAsFavorite(user.id, Favorite("tv", show.id, show.isFavorite), user.sessionId,
                {
                    onSuccess(it)
                },
                {
                    onError(it)
                }
        )
    }

    fun getPictures(id: Int, onSuccess: (images: List<Image>) -> Unit, onError: (error: Throwable) -> Unit){
        ShowNetwork.fetchPictures(id,
                {
                    onSuccess(it.backdrops)
                },
                {
                    onError(it)
                }
        )
    }

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

    fun getCategories(onSuccess: (categories: List<Category>) -> Unit, onError: (error: Throwable) -> Unit) {
        val result = mutableListOf<Category>()

        getAiringTodayFromAPI({ today ->

            result.add(Category("Em exibição hoje", today))

            getPopular({ popular ->
                result.add(Category("Populares", popular))

                getTopRated({ topRated ->
                    result.add(Category("Melhores avaliadas", topRated))

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
                    onSuccess(it.shows)
                },
                {
                    onError(it)
                }
        )

    }

    private fun getPopular(onSuccess: (shows: List<Show>) -> Unit, onError: (error: Throwable) -> Unit) {

        ShowNetwork.fetchPopular(
                {
                    onSuccess(it.shows)
                },
                {
                    onError(it)
                }
        )

    }

    private fun getTopRated(onSuccess: (shows: List<Show>) -> Unit, onError: (error: Throwable) -> Unit) {

        ShowNetwork.fetchTopRated(
                {
                    onSuccess(it.shows)
                },
                {
                    onError(it)
                }
        )

    }

}