package com.albuquerque.tvshow.modules.shows.model

import com.albuquerque.tvshow.core.network.BaseNetwork
import com.google.gson.annotations.SerializedName

class Image{
    @SerializedName("file_path")
    var url: String = ""
        get() {
            return if (field.isNotBlank())
                BaseNetwork.BASE_IMAGE_URL + field
            else
                ""
        }
}