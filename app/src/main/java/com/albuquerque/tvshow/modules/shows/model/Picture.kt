package com.albuquerque.tvshow.modules.shows.model

import com.albuquerque.tvshow.core.network.BaseNetwork.Companion.BASE_IMAGE_URL
import com.google.gson.annotations.SerializedName

class Picture {

    var backdrops: List<Image> = mutableListOf()
    var posters : List<Image> = mutableListOf()
}

class Image{
    @SerializedName("file_path")
    var url: String = ""
        get() {
            return if (field.isNotBlank())
                BASE_IMAGE_URL + field
            else
                ""
        }
}