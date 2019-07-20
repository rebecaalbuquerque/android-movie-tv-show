package com.albuquerque.movietvshow.modules.shows.business

import com.albuquerque.movietvshow.core.business.BaseBusiness
import com.albuquerque.movietvshow.modules.auth.business.AuthBusiness
import com.albuquerque.movietvshow.modules.auth.model.AuthResponse
import com.albuquerque.movietvshow.modules.shows.database.ShowDatabase
import com.albuquerque.movietvshow.modules.shows.enum.TypeCategory.*
import com.albuquerque.movietvshow.modules.shows.model.Category
import com.albuquerque.movietvshow.modules.shows.model.Favorite
import com.albuquerque.movietvshow.modules.shows.model.Show
import com.albuquerque.movietvshow.modules.shows.network.ShowNetwork

object ShowsBusiness : BaseBusiness() {

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

    fun getShowFromAPI(id: Int, onSuccess: (show: Show) -> Unit, onError: (error: Throwable) -> Unit) {
        ShowNetwork.requestShow(id,
                { show ->

                    // Só atualiza quem já está no BD que no caso são apenas os shows favoritos
                    ShowDatabase.getShowFromDB(id)?.let { ShowDatabase.saveOrUpdate(show) }
                    onSuccess(show)
                },
                {
                    onError(it)
                }
        )
    }

    fun getShowFromDB(id: Int): Show? {
        return ShowDatabase.getShowFromDB(id)
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

        ShowNetwork.requestPopular(page,
                onSuccess = {
                    onSuccess(it.shows)
                },

                onError = {
                    onError(it)
                }
        )

    }

    fun getTopRatedFromAPI(page: Int = 1, onSuccess: (shows: List<Show>) -> Unit, onError: (error: Throwable) -> Unit) {

        ShowNetwork.requestTopRated(page,
                onSuccess = {
                    onSuccess(it.shows)
                },

                onError = {
                    onError(it)
                }
        )

    }

    fun getFavoritesFromAPI(page: Int = 1, onSuccess: (shows: List<Show>) -> Unit, onError: (error: Throwable) -> Unit) {
        val user = AuthBusiness.getUser()!!

        ShowNetwork.requestFavorites(user.id, user.sessionId, page,
                onSuccess = {
                    onSuccess(it.shows)
                },

                onError = {
                    onError(it)
                }
        )

    }

}