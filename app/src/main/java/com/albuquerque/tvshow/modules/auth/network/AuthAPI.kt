package com.albuquerque.tvshow.modules.auth.network

import com.albuquerque.tvshow.modules.auth.model.AuthRequest
import com.albuquerque.tvshow.modules.auth.model.AuthResponse
import com.albuquerque.tvshow.modules.auth.model.User
import io.reactivex.Observable
import retrofit2.http.*

interface AuthAPI {

    @GET("authentication/token/new?api_key={api_key}")
    fun fetchRequestToken(@Path("api_key") apiKey: String): Observable<AuthResponse>

    @POST("authentication/token/validate_with_login?api_key={api_key}")
    fun fetchValidationToken(
            @Path("api_key") apiKey: String, @Body request: AuthRequest): Observable<AuthResponse>

    @POST("authentication/session/new?api_key={api_key}")
    fun createSession(
            @Path("api_key") apiKey: String,
            @Field("request_token") requestToken: String): Observable<AuthResponse>

    @GET("account?api_key={api_key}&session_id={session_id}")
    fun fetchUser(@Path("api_key") apiKey: String, @Path("session_id") sessionId: String): Observable<User>
}