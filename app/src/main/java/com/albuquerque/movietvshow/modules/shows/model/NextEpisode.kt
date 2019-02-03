package com.albuquerque.movietvshow.modules.shows.model

import com.albuquerque.movietvshow.core.extensions.toBrazilianDate
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