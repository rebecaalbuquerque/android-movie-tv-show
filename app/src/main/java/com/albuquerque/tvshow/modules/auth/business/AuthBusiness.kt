package com.albuquerque.tvshow.modules.auth.business

import com.albuquerque.tvshow.modules.auth.database.AuthDatabase
import com.albuquerque.tvshow.modules.auth.model.User
import com.albuquerque.tvshow.modules.auth.network.AuthNetwork

object AuthBusiness {

    fun doLogin(username: String, password: String, onSuccess: (user: User)-> Unit, onError: (msg: Throwable) -> Unit){

        with(AuthNetwork){
            requestToken(
                    {   request ->
                        requestValidationTokenWithLogin(
                                username,
                                password,
                                request.requestToken,
                                { validation ->
                                    requestCreateSession(validation.requestToken,
                                            { session ->

                                                requestUser(session.sessionId,
                                                        {
                                                            AuthDatabase.saveOrUpdate(it)
                                                            onSuccess(it)
                                                        },
                                                        {
                                                            onError(it)
                                                        }
                                                )
                                            },

                                            {
                                                // erro ao criar sessao
                                                onError(it)
                                            }
                                    )
                                },

                                {
                                    // erro ao validar token com credenciais
                                    onError(it)
                                }
                        )
                    },

                    {
                        // erro ao criar token
                        onError(it)
                    }
            )
        }

    }

}