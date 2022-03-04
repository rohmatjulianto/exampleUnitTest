package com.example.myunit

import com.example.myunit.data.Post
import com.example.myunit.data.User
import com.example.myunit.local.MyAppDb
import com.example.myunit.module.CoreAPI
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

interface MainRepository {
    suspend fun getUser(): ArrayList<User>
    suspend fun getPost(id: String): Post
}

class MainRepositoryImpl(val api: CoreAPI) : MainRepository {
    override suspend fun getUser(): ArrayList<User> {
        return api.getUser().body()!!
    }

    override suspend fun getPost(id: String): Post {
        return api.getPost(id).body()!!
    }

}