package com.albuquerque.tvshow.modules.shows.model

import com.google.gson.annotations.SerializedName

class ShowListResponse {

    var page: Int = 0

    @SerializedName("total_results")
    var totalResults: Int = 0

    @SerializedName("total_pages")
    var totalPages: Int = 0

    var results: List<Show> = listOf()
}