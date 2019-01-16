package com.albuquerque.tvshow.modules.shows.model

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

    @SerializedName("poster_path")
    var posterPath: String = ""

    var overview: String = ""

}