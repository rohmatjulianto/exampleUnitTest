package com.example.myunit.module

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        provideOkHttpClient()
    }


    single {
        Gson()
    }

    single {
        provideRetrofit(
            get(),
            "https://jsonplaceholder.typicode.com/"
        )
    }

    single {
        provideCoreApi(get())
    }
}

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    url: String,
): Retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .baseUrl(url)
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .build()


private fun provideCoreApi(retrofit: Retrofit): CoreAPI = retrofit.create(CoreAPI::class.java)

private fun provideOkHttpClient() =
    OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        .build()