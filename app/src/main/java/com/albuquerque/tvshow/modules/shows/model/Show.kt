package com.albuquerque.tvshow.modules.shows.model

import com.albuquerque.tvshow.core.extensions.toBrazilianDate
import com.albuquerque.tvshow.core.network.BaseNetwork.Companion.BASE_IMAGE_URL
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

    @SerializedName("next_episode_to_air")
    var nextEpisode: NextEpisode? = null

    @SerializedName("backdrop_path")
    var backdropPath: String = ""
        get() = BASE_IMAGE_URL + field

    @SerializedName("poster_path")
    var posterPath: String = ""
        get() = BASE_IMAGE_URL + field

    var overview: String = ""

    @SerializedName("vote_average")
    var average: Double = 0.0

    @SerializedName("created_by")
    var directors: RealmList<Director> = RealmList()

    var networks: RealmList<Image> = RealmList()

    @SerializedName("number_of_seasons")
    var seasons: Int = 0

    var isFavorite: Boolean = false


}

