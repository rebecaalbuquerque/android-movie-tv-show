package com.albuquerque.tvshow.modules.auth.network

import com.albuquerque.tvshow.modules.auth.model.AuthRequest
import com.albuquerque.tvshow.modules.auth.model.AuthResponse
import com.albuquerque.tvshow.modules.auth.model.User
import io.reactivex.Observable
import retrofit2.http.*

interface AuthAPI {

    @GET("authentication/token/new")
    fun fetchRequestToken(
            @Query("api_key") apiKey: String
    ): Observable<AuthResponse>

    @POST("authentication/token/validate_with_login")
    fun fetchValidationToken(
            @Query("api_key") apiKey: String,
            @Body request: AuthRequest
    ): Observable<AuthResponse>

    @FormUrlEncoded
    @POST("authentication/session/new")
    fun createSession(
            @Query("api_key") apiKey: String,
            @Field("request_token") requestToken: String
    ): Observable<AuthResponse>

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "authentication/session", hasBody = true)
    fun deleteSession(
            @Query("api_key") apiKey: String,
            @Field("session_id") sessionId: String
    ): Observable<AuthResponse>

    @GET("account")
    fun fetchUser(
            @Query("api_key") apiKey: String,
            @Query("session_id") sessionId: String
    ): Observable<User>
}