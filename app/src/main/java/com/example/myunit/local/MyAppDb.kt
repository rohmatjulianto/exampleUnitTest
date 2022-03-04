package com.example.myunit.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myunit.local.dao.UserDao
import com.example.myunit.local.entities.UserEntities

@Database(entities = [UserEntities::class], version = 1)
abstract class MyAppDb : RoomDatabase() {
    abstract fun UserDao(): UserDao
}