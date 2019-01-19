package com.albuquerque.tvshow.modules.shows.network

import com.albuquerque.tvshow.modules.shows.model.Show
import com.albuquerque.tvshow.modules.shows.model.ShowListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShowAPI {

    @GET("tv/{id}")
    fun fetchShow(
            @Path("id") id: Int,
            @Query("api_key") apiKey: String,
            @Query("language") language: String? = null
    ): Observable<Show>

    @GET("tv/airing_today")
    fun fetchAiringToday(
        @Query("api_key") apiKey: String,
        @Query("language") language: String? = null
    ): Observable<ShowListResponse>

    @GET("tv/popular")
    fun fetchPopular(
            @Query("api_key") apiKey: String,
            @Query("language") language: String? = null
    ): Observable<ShowListResponse>

    @GET("tv/top_rated")
    fun fetchTopRated(
            @Query("api_key") apiKey: String,
            @Query("language") language: String? = null
    ): Observable<ShowListResponse>

}