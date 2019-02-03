package com.albuquerque.movietvshow.modules.shows.network

import com.albuquerque.movietvshow.modules.auth.model.AuthResponse
import com.albuquerque.movietvshow.modules.shows.model.Category
import com.albuquerque.movietvshow.modules.shows.model.Favorite
import com.albuquerque.movietvshow.modules.shows.model.Show
import io.reactivex.Observable
import retrofit2.http.*

interface ShowAPI {

    @POST("account/{account_id}/favorite")
    fun postFavorite(
            @Path("account_id") accountId: Int,
            @Body favorite: Favorite,
            @Query("session_id") sessionId: String,
            @Query("api_key") apiKey: String,
            @Query("language") language: String? = null
    ): Observable<AuthResponse>

    @GET("tv/{id}")
    fun fetchShow(
            @Path("id") id: Int,
            @Query("api_key") apiKey: String,
            @Query("append_to_response") append: String,
            @Query("language") language: String? = null
    ): Observable<Show>

    @GET("tv/airing_today")
    fun fetchAiringToday(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String? = null
    ): Observable<Category>

    @GET("tv/popular")
    fun fetchPopular(
            @Query("api_key") apiKey: String,
            @Query("page") page: Int,
            @Query("language") language: String? = null
    ): Observable<Category>

    @GET("tv/top_rated")
    fun fetchTopRated(
            @Query("api_key") apiKey: String,
            @Query("page") page: Int,
            @Query("language") language: String? = null
    ): Observable<Category>

}