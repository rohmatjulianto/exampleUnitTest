package com.example.myunit.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntities(
    @PrimaryKey  val uid: Int,
    @ColumnInfo(name = "id") val id: String?,
    @ColumnInfo(name = "username") val username: String?,
    @ColumnInfo(name = "email") val email: String?,
)
