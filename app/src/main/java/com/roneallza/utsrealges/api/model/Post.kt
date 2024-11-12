package com.roneallza.utsrealges.api.model

data class Post(
    val id: Int,
    val title: String,
    val slug: String,
    val body: String,
    val created_at: String,
    val updated_at: String
)