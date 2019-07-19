package com.albuquerque.movietvshow.modules.auth.network

import com.albuquerque.movietvshow.core.network.BaseNetwork
import com.albuquerque.movietvshow.modules.auth.model.AuthResponse
import com.albuquerque.movietvshow.modules.auth.model.Favorites
import com.albuquerque.movietvshow.modules.auth.model.User

object AuthNetwork: BaseNetwork() {

    private val api by lazy { getRetrofitBuilder().build().create(AuthAPI::class.java) }

    // Login
    fun requestToken(onSuccess: (response: AuthResponse) -> Unit, onError: (error: Throwable) -> Unit ){

        doRequest(api, onSuccess, onError) {
            fetchRequestToken(API_KEY)
        }

    }

    fun requestValidationTokenWithLogin(user: String, pass: String, requestToken: String, onSuccess: (response: AuthResponse) -> Unit, onError: (error: Throwable) -> Unit ){

        doRequest(api, onSuccess, onError) {
            fetchValidationToken(API_KEY, user, pass, requestToken)
        }

    }

    fun requestCreateSession(requestToken: String, onSuccess: (response: AuthResponse) -> Unit, onError: (error: Throwable) -> Unit ){

        doRequest(api, onSuccess, onError) {
            createSession(API_KEY, requestToken)
        }

    }

    // Logout
    fun requestDeleteSession(sessionId: String, onSuccess: (response: AuthResponse) -> Unit, onError: (error: Throwable) -> Unit){
        doRequest(api, onSuccess, onError){
            deleteSession(API_KEY, sessionId)
        }
    }

    // Usuário
    fun requestUser(sessionId: String, onSuccess: (response: User) -> Unit, onError: (error: Throwable) -> Unit){

        doRequest(api, onSuccess, onError){
            fetchUser(API_KEY, sessionId)
        }

    }

}