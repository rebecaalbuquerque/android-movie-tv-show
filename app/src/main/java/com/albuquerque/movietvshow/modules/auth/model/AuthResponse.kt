package com.albuquerque.movietvshow.modules.auth.model

import com.google.gson.annotations.SerializedName

class AuthResponse {

    var success: Boolean? = null

    @SerializedName("expires_at")
    var expiresAt: String = ""

    @SerializedName("request_token")
    var requestToken: String = ""

    @SerializedName("session_id")
    var sessionId: String = ""

    // ------ Erro(401;404) ------ //

    @SerializedName("status_code")
    var statusCode: Int = -1

    @SerializedName("status_message")
    var statusMessage: String = ""

}