package com.albuquerque.movietvshow.modules.shows.model

import com.google.gson.annotations.SerializedName
import io.realm.annotations.PrimaryKey

class Favorite(){

    @PrimaryKey
    var id: Int = 0

    @SerializedName("media_type")
    var mediaType: String = ""

    @SerializedName("media_id")
    var mediaId: Int = 0

    @SerializedName("favorite")
    var isFavorite: Boolean = false

    constructor(mediaType: String, mediaId: Int, isFavorite: Boolean): this(){
        this.mediaType = mediaType
        this.mediaId = mediaId
        this.isFavorite = isFavorite
    }
}