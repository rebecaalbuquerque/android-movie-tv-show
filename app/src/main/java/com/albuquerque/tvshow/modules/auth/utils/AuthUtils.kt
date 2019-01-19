package com.albuquerque.tvshow.modules.auth.utils

import com.albuquerque.tvshow.modules.auth.model.AuthResponse
import com.google.gson.Gson
import retrofit2.HttpException
import java.net.UnknownHostException

object AuthUtils {

    fun geErrorMessage(exception: Throwable): String? {

        return when (exception) {
            is HttpException -> {
                Gson().fromJson(
                    exception.response().errorBody()?.charStream(),
                    AuthResponse::class.java
                ).statusMessage
            }

            is UnknownHostException -> {
                "Verifique sua conexão com a internet"
            }

            else -> null
        }
    }

}