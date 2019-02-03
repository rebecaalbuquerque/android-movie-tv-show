package com.albuquerque.movietvshow.modules.shows.model

import com.albuquerque.movietvshow.modules.shows.enum.TypeCategory
import com.google.gson.annotations.SerializedName

class Category() {

    lateinit var type: TypeCategory

    var page: Int = 0

    @SerializedName("total_results")
    var totalResults: Int = 0

    @SerializedName("total_pages")
    var totalPages: Int = 0

    @SerializedName("results")
    var shows: List<Show> = listOf()

    constructor(category: TypeCategory, shows: List<Show>): this(){
        this.type = category
        this.shows = shows
    }

}