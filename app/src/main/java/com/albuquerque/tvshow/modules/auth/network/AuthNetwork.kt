package com.albuquerque.tvshow.modules.auth.network

import com.albuquerque.tvshow.core.network.BaseNetwork
import com.albuquerque.tvshow.modules.auth.model.AuthRequest
import com.albuquerque.tvshow.modules.auth.model.AuthResponse
import com.albuquerque.tvshow.modules.auth.model.User

object AuthNetwork: BaseNetwork() {

    private val api by lazy { getRetrofitBuilder().build().create(AuthAPI::class.java) }

    fun requestToken( onSuccess: (response: AuthResponse) -> Unit, onError: (error: Throwable) -> Unit ){

        doRequest<AuthResponse, AuthAPI>(api, onSuccess, onError) {
            fetchRequestToken(API_KEY)
        }

    }

    fun requestValidationTokenWithLogin(user: String, pass: String, requestToken: String, onSuccess: (response: AuthResponse) -> Unit, onError: (error: Throwable) -> Unit ){

        doRequest(api, onSuccess, onError) {
            fetchValidationToken(API_KEY, AuthRequest(user, pass, requestToken))
        }

    }

    fun requestCreateSession(requestToken: String, onSuccess: (response: AuthResponse) -> Unit, onError: (error: Throwable) -> Unit ){

        doRequest(api, onSuccess, onError) {
            createSession(API_KEY, requestToken)
        }

    }

    fun requestUser(sessionId: String, onSuccess: (response: User) -> Unit, onError: (error: Throwable) -> Unit){

        doRequest(api, onSuccess, onError){
            fetchUser(API_KEY, sessionId)
        }

    }

}