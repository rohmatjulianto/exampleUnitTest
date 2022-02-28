package com.example.myunit.module

import com.example.myunit.data.Post
import com.example.myunit.data.User
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CoreAPI {
    @GET("/users")
    suspend fun getUser() : Response<ArrayList<User>>

    @GET("/posts/{postId}")
    suspend fun getPost(@Path("postId") id:String): Response<Post>
}