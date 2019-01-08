package com.albuquerque.tvshow.modules.auth.business

import com.albuquerque.tvshow.modules.auth.database.AuthDatabase
import com.albuquerque.tvshow.modules.auth.network.AuthNetwork

object AuthBusiness {

    fun doLogin(username: String, password: String){

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
                                                        },
                                                        {
                                                            // erro request user
                                                        }
                                                )
                                            },

                                            {
                                                // erro ao criar sessao
                                            }
                                    )
                                },

                                {
                                    // erro ao validar token com credenciais
                                }
                        )
                    },

                    {
                        // erro ao criar token
                    }
            )
        }

    }

}