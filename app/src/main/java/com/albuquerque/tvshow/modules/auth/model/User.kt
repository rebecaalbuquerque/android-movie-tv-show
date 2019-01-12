package com.albuquerque.tvshow.modules.auth.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User: RealmObject() {

    @PrimaryKey
    var id: Int = 0

    @SerializedName("session_id")
    var sessionId: String = ""

    var name: String = ""

    var username: String = ""


}