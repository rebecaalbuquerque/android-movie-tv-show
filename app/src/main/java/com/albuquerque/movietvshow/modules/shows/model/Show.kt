package com.albuquerque.movietvshow.modules.shows.model

import com.albuquerque.movietvshow.core.extensions.toBrazilianDate
import com.albuquerque.movietvshow.core.network.BaseNetwork.Companion.BASE_IMAGE_URL
import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Show: RealmObject() {

    @PrimaryKey
    var id: Int = 0

    @SerializedName("original_name")
    var originalName: String = ""

    var name: String = ""

    @SerializedName("first_air_date")
    var firstAirDate: String = ""
        get() = field.toBrazilianDate()

    @SerializedName("backdrop_path")
    var backdropPath: String = ""
        get() = BASE_IMAGE_URL + field

    @SerializedName("poster_path")
    var posterPath: String = ""
        get() = BASE_IMAGE_URL + field

    var overview: String = ""

    @SerializedName("vote_average")
    var average: Double = 0.0

    @SerializedName("number_of_seasons")
    var seasons: Int = 0

    var networks: RealmList<Image> = RealmList()

    @SerializedName("next_episode_to_air")
    var nextEpisode: NextEpisode? = null

    @SerializedName("created_by")
    var directors: RealmList<Director> = RealmList()

    var images: Image? = null

    var isFavorite: Boolean = false

}

