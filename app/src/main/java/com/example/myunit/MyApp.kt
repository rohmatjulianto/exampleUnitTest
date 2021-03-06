package com.example.myunit

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.room.Room
import com.example.myunit.local.MyAppDb
import com.example.myunit.module.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        startKoin{
            androidContext(this@MyApp)
            modules(networkModule)
            modules(mainModules)
        }
    }

    val mainModules = module {
//        single {
//            Room.databaseBuilder(get(), MyAppDb::class.java, "unit-db").build()
//        }
        single<MainRepository> {
            MainRepositoryImpl(get())
        }
        viewModel {MainViewModel(get())}
    }


}

