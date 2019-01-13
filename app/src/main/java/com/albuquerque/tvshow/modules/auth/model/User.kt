package com.albuquerque.tvshow.modules.auth.model

import com.albuquerque.tvshow.core.network.BaseNetwork.Companion.BASE_GRAVATAR_URL
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User(): RealmObject() {

    @PrimaryKey
    var id: Int = 0

    @SerializedName("session_id")
    var sessionId: String = ""

    var name: String = ""

    var username: String = ""

    var avatar: Avatar ?= null

    var avatarUrl = ""

    constructor(id: Int, sessionId: String, name: String, username: String, avatarUrl: String): this(){
        this.id = id
        this.sessionId = sessionId
        this.name = name
        this.username = username
        this.avatarUrl = avatarUrl
    }

}

open class Gravatar: RealmObject(){
    var hash: String = ""
        get() = "$BASE_GRAVATAR_URL$field.jpg?s=250"
}

open class Avatar(var gravatar: Gravatar ?= null): RealmObject()