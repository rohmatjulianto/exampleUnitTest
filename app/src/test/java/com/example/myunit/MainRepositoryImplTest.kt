package com.example.myunit

import com.example.myunit.data.Post
import com.example.myunit.data.User
import com.example.myunit.module.CoreAPI
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class MainRepositoryImplTest {
    val mockServer = MockWebServer()
    private lateinit var api: CoreAPI
    lateinit var repository: MainRepositoryImpl

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    @Before
    fun setup(){
        mockServer.start(8000)
        api = Retrofit.Builder()
            .baseUrl(mockServer.url("/"))
            .client(client)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoreAPI::class.java)

        repository = MainRepositoryImpl(api)
    }


    @Test
    fun getUser() {
        runTest {
            val inputStrem = javaClass.classLoader!!.getResourceAsStream("placeholder_users.json")
            mockServer.apply{
                enqueue(MockResponse().setResponseCode(200).setBody(inputStrem!!.source().buffer().readUtf8()))
            }
            val actual = repository.getUser()
            val expected = arrayListOf(User("1", "Bret", "Sincere@april.biz"))
            assertEquals(expected, actual)
        }


    }

    @Test
    fun getPost() {
        runTest {
            val inputStream = javaClass.classLoader!!.getResourceAsStream("placeholder_post.json")
            mockServer.apply{
                enqueue(MockResponse().setResponseCode(200).setBody(inputStream?.source()!!.buffer().readUtf8()))
            }
            val actual = repository.getPost("1")
            val expect = Post(1, "title", "body")
            assertEquals(expect, actual)
        }
    }

    @After
    fun shutDown(){
        mockServer.shutdown()
    }

}
