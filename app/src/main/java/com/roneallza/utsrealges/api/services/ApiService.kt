package com.roneallza.utsrealges.api.services

import com.roneallza.utsrealges.api.model.Post
import com.roneallza.utsrealges.api.model.PostResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("post")
    fun getPosts(): Call<PostResponse>

    @GET("post/{id}")
    fun getPost(@Path("id") id: Int): Call<Post>

    @POST("post")
    @FormUrlEncoded
    fun createPost(
        @Field("title") title: String,
        @Field("slug") slug: String,
        @Field("body") body: String
    ): Call<Post>

    @PUT("post/{id}")
    @FormUrlEncoded
    fun updatePost(
        @Path("id") id: Int,
        @Field("title") title: String,
        @Field("slug") slug: String,
        @Field("body") body: String
    ): Call<Post>

    @DELETE("post/{id}")
    fun deletePost(@Path("id") id: Int): Call<Void>
}
