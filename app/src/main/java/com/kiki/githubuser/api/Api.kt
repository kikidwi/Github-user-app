package com.kiki.githubuser.api

import com.kiki.githubuser.ui.detail.DetailUserResponse
import com.kiki.githubuser.model.user
import com.kiki.githubuser.model.userResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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")

    fun getSearchUser(
        @Query("q") query: String
    ): Call<userResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<user>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<user>>
}