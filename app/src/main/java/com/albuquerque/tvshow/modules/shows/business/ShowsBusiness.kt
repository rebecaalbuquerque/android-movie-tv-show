package com.albuquerque.tvshow.modules.shows.business

import com.albuquerque.tvshow.core.business.BaseBusiness
import com.albuquerque.tvshow.modules.auth.business.AuthBusiness
import com.albuquerque.tvshow.modules.auth.model.AuthResponse
import com.albuquerque.tvshow.modules.shows.database.ShowDatabase
import com.albuquerque.tvshow.modules.shows.enum.TypeCategory.*
import com.albuquerque.tvshow.modules.shows.model.*
import com.albuquerque.tvshow.modules.shows.network.ShowNetwork

object ShowsBusiness : BaseBusiness() {

    fun getDirectorsNameFormatted(directors: List<Director>): String {

        return when (directors.size) {
            0 -> "N/I"

            1 -> directors[0].name

            else -> {

                var names = ""

                for (i in 0 until directors.size - 2)
                    names += directors[i].name + ", "


                names += directors[directors.size - 1].name

                names

            }

        }

    }

    fun isShowFavorite(showId: Int): Boolean {
        return ShowDatabase.getShowFromDB(showId) != null
    }

    fun markAsFavorite(show: Show, onSuccess: (response: AuthResponse) -> Unit, onError: (error: Throwable) -> Unit) {
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

    fun getShow(id: Int, onSuccess: (show: Show) -> Unit, onError: (error: Throwable) -> Unit) {
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

        with(ShowDatabase.getFavorites()) {
            if (this.isNotEmpty())
                result.add(Category(FAVORITAS, this))
        }

        getAiringTodayFromAPI(onSuccess = { today ->

            result.add(Category(EM_EXIBICAO, today))

            getPopularFromAPI(onSuccess = { popular ->
                result.add(Category(POPULARES, popular))

                getTopRatedFromAPI(onSuccess = { topRated ->
                    result.add(Category(MELHORES_AVALIADAS, topRated))

                    onSuccess(result)

                },
                        onError = { onError(it) })

            },
                    onError = { onError(it) })

        },
                onError = { onError(it) })


    }

    fun getAiringTodayFromAPI(page: Int = 1, onSuccess: (shows: List<Show>) -> Unit, onError: (error: Throwable) -> Unit) {

        ShowNetwork.requestAiringToday(page,
                onSuccess = {
                    onSuccess(it.shows)
                },

                onError = {
                    onError(it)
                }
        )

    }

    fun getPopularFromAPI(page: Int = 1, onSuccess: (shows: List<Show>) -> Unit, onError: (error: Throwable) -> Unit) {

        ShowNetwork.requestPopular(
                onSuccess = {
                    onSuccess(it.shows)
                },

                onError = {
                    onError(it)
                }
        )

    }

    fun getTopRatedFromAPI(page: Int = 1, onSuccess: (shows: List<Show>) -> Unit, onError: (error: Throwable) -> Unit) {

        ShowNetwork.requestTopRated(
                onSuccess = {
                    onSuccess(it.shows)
                },

                onError = {
                    onError(it)
                }
        )

    }

}