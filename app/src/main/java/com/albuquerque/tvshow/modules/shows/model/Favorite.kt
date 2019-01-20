package com.albuquerque.tvshow.modules.shows.model

import com.google.gson.annotations.SerializedName

class Favorite() {

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