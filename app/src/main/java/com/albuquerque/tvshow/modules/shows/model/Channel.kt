package com.albuquerque.tvshow.modules.shows.model

import com.albuquerque.tvshow.core.network.BaseNetwork.Companion.BASE_IMAGE_URL
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Channel: RealmObject() {

    @SerializedName("logo_path")
    var logo: String = ""
        get() = BASE_IMAGE_URL + field

    var name: String = ""

    var id: Int = 0

}