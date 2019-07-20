package com.albuquerque.movietvshow.modules.movies.model

import com.albuquerque.movietvshow.core.extensions.toBrazilianDate
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Movie: RealmObject() {

    @SerializedName("original_title")
    var originalTitle: String = ""

    var title: String = ""

    var status: String = "-"

    var tagline: String = "-"

    @SerializedName("vote_average")
    var average: Double = 0.0

    var overview: String = ""
        get() {
            return if(field.isBlank() || field.isEmpty())
                "Informação não fornecida."
            else
                field
        }

    @SerializedName("release_date")
    var releaseDate: String = ""
        get() = field.toBrazilianDate()

}