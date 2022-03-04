package com.example.myunit.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myunit.local.entities.UserEntities

@Dao
interface UserDao {
    @Query("SELECT * FROM UserEntities")
    fun getAll(): List<UserEntities>

    @Query("SELECT * FROM UserEntities WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<UserEntities>

    @Query("SELECT * FROM UserEntities WHERE id = :first")
    fun findById(id: String): UserEntities

    @Insert
    fun insertAll(vararg users: UserEntities)

    @Delete
    fun delete(user: UserEntities)
}