package com.albuquerque.movietvshow.modules.auth.business

import com.albuquerque.movietvshow.core.network.BaseNetwork
import com.albuquerque.movietvshow.modules.auth.database.AuthDatabase
import com.albuquerque.movietvshow.modules.auth.model.User
import com.albuquerque.movietvshow.modules.auth.network.AuthNetwork
import com.albuquerque.movietvshow.modules.shows.database.ShowDatabase

object AuthBusiness {

    fun getFavoritesFromAPI(onSuccess: () -> Unit, onError: (msg: Throwable) -> Unit){
        val user = getUser()!!

        AuthNetwork.requestUserFavorites(user.id, user.sessionId,
                { favorites ->

                    favorites.list.forEach { it.isFavorite = true }

                    ShowDatabase.salveOrUpdateAll(favorites.list.toMutableList(), onNext = {
                        onSuccess()
                    })
                },
                {
                    onError(it)
                }
        )
    }

    fun doLogin(username: String, password: String, onSuccess: (user: User)-> Unit, onError: (msg: Throwable) -> Unit){
        var sessionId: String

        with(AuthNetwork){
            requestToken(
                    {   request ->

                        requestValidationTokenWithLogin( username, password, request.requestToken,
                            { validation ->

                                requestCreateSession(validation.requestToken,
                                    { session ->
                                        sessionId = session.sessionId

                                        requestUser(session.sessionId,
                                            {
                                                it.sessionId = sessionId
                                                it.avatarUrl = BaseNetwork.BASE_GRAVATAR_URL + it.avatar?.gravatar?.hash + ".jpg?s=250"
                                                onSuccess(it)
                                            },
                                            {
                                                onError(it)
                                            })
                                    },

                                    {
                                        onError(it) // erro ao criar sessao
                                    })
                            },

                            {
                                onError(it)// erro ao validar token com credenciais
                            })
                    },

                    {
                        onError(it) // erro ao criar token
                    }
            )
        }

    }

    fun doLogout(onSuccess: () -> Unit, onError: (msg: Throwable) -> Unit){
        AuthNetwork.requestDeleteSession(getSessionId(),
                {
                    onSuccess()
                },
                {
                    onError(it)
                }
        )
    }

    fun getUserAvatar(): String {
        return AuthDatabase.getUserGravatarHash()
    }

    fun getSessionId(): String = AuthDatabase.getSessionIdFromDB()

    fun getUser(): User? = AuthDatabase.getUserFromDB()
}