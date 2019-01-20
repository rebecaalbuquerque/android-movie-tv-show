package com.albuquerque.tvshow.modules.shows.model

import com.albuquerque.tvshow.core.extensions.toBrazilianDate
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class NextEpisode: RealmObject(){

    @PrimaryKey
    var id: Int = 0

    @SerializedName("air_date")
    var airDate: String = ""
        get() = field.toBrazilianDate()
}