package com.albuquerque.tvshow.modules.shows.model

import com.google.gson.annotations.SerializedName

class Category() {

    var name: String = ""

    var page: Int = 0

    @SerializedName("total_results")
    var totalResults: Int = 0

    @SerializedName("total_pages")
    var totalPages: Int = 0

    @SerializedName("results")
    var shows: List<Show> = listOf()

    constructor(name: String, shows: List<Show>): this(){
        this.name = name
        this.shows = shows
    }

}