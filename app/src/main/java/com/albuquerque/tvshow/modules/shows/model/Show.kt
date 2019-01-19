package com.albuquerque.tvshow.modules.shows.model

import com.albuquerque.tvshow.core.network.BaseNetwork.Companion.BASE_IMAGE_URL
import com.google.gson.annotations.SerializedName

class Show {

    var id: Int = 0

    @SerializedName("original_name")
    var originalName: String = ""

    var name: String = ""

    @SerializedName("first_air_date")
    var firstAirDate: String = ""

    @SerializedName("backdrop_path")
    var backdropPath: String = ""
        get() = BASE_IMAGE_URL + field

    @SerializedName("poster_path")
    var posterPath: String = ""
        get() = BASE_IMAGE_URL + field

    var overview: String = ""

}