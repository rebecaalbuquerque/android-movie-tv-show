package com.albuquerque.tvshow.modules.auth.model

import com.google.gson.annotations.SerializedName

class AuthRequest() {

    var username: String = ""

    var password: String = ""

    @SerializedName("request_token")
    var requestToken: String = ""

    constructor(username: String, password: String, requestToken: String): this(){
        this.username = username
        this.password = password
        this.requestToken = requestToken
    }

}