package com.albuquerque.tvshow.modules.shows.business

import com.albuquerque.tvshow.core.business.BaseBusiness
import com.albuquerque.tvshow.modules.auth.business.AuthBusiness
import com.albuquerque.tvshow.modules.auth.model.AuthResponse
import com.albuquerque.tvshow.modules.shows.database.ShowDatabase
import com.albuquerque.tvshow.modules.shows.model.*
import com.albuquerque.tvshow.modules.shows.network.ShowNetwork

object ShowsBusiness : BaseBusiness() {

    fun getDirectorsNameFormatted(directors: List<Director>): String {

        return when(directors.size){
            0 -> "N/I"

            1 -> directors[0].name

            else -> {

                var names = ""

                for(i in 0 until directors.size - 2)
                    names += directors[i].name + ", "


                names += directors[directors.size-1].name

                names

            }

        }

    }

    fun isShowFavorite(showId: Int): Boolean{
        return ShowDatabase.getShowFromDB(showId) != null
    }

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

    fun getShow(id: Int, onSuccess: (show: Show) -> Unit, onError: (error: Throwable) -> Unit){
        ShowNetwork.requestShow(id,
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

        with(ShowDatabase.getFavorites()){
            if(this.isNotEmpty())
                result.add(Category("Favoritas", this))
        }

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

        ShowNetwork.requestAiringToday(
                {
                    onSuccess(it.shows)
                },
                {
                    onError(it)
                }
        )

    }

    private fun getPopular(onSuccess: (shows: List<Show>) -> Unit, onError: (error: Throwable) -> Unit) {

        ShowNetwork.requestPopular(
                {
                    onSuccess(it.shows)
                },
                {
                    onError(it)
                }
        )

    }

    private fun getTopRated(onSuccess: (shows: List<Show>) -> Unit, onError: (error: Throwable) -> Unit) {

        ShowNetwork.requestTopRated(
                {
                    onSuccess(it.shows)
                },
                {
                    onError(it)
                }
        )

    }

}