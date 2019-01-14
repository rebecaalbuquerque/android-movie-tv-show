package com.albuquerque.tvshow.modules.auth.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey

open class User: RealmObject() {

    @PrimaryKey
    var id: Int = 0

    @SerializedName("session_id")
    var sessionId: String = ""

    var name: String = ""

    var username: String = ""

    @Ignore
    var avatar: Avatar? = null

    var avatarUrl = ""

}

open class Gravatar(var hash: String = ""): RealmObject()

open class Avatar(var gravatar: Gravatar? = null): RealmObject()