package com.albuquerque.tvshow.modules.auth.utils

import com.albuquerque.tvshow.modules.auth.model.AuthResponse
import com.google.gson.Gson
import retrofit2.HttpException

object AuthUtils {

    fun geErrorMessage(exception: Throwable): String? {
        return if (exception is HttpException) {
            Gson().fromJson(
                    exception.response().errorBody()?.charStream(),
                    AuthResponse::class.java
            ).statusMessage

        } else {
            null
        }
    }

}