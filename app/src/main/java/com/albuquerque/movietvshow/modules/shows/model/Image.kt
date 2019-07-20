package com.albuquerque.movietvshow.modules.shows.model

import com.albuquerque.movietvshow.core.network.BaseNetwork
import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.Ignore

open class Image: RealmObject(){

    /**
     * Imagens da série
     * */
    var backdrops: RealmList<Picture> = RealmList()

    @Ignore
    var posters : List<Picture> = mutableListOf()
}

open class Picture: RealmObject() {

    @SerializedName("file_path")
    var url: String? = ""
        get() {
            return if (!field.isNullOrBlank())
                BaseNetwork.BASE_IMAGE_URL + field
            else
                ""
        }

    /**
     * Logo do canal de exibição da série
     * */
    @SerializedName("logo_path")
    var logoChannel: String? = ""
        get() {
            return if (!field.isNullOrBlank())
                BaseNetwork.BASE_IMAGE_URL + field
            else
                ""
        }

}