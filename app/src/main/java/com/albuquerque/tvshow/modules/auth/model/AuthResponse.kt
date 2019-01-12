package com.albuquerque.tvshow.modules.auth.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

class AuthResponse {

    var success: Boolean? = null

    @SerializedName("expires_at")
    var expiresAt: String = ""

    @SerializedName("request_token")
    var requestToken: String = ""

    @SerializedName("session_id")
    var sessionId: String = ""

    @SerializedName("status_code")
    var statusCode: Int = -1

    @SerializedName("status_message")
    var statusMessage: String = ""

}