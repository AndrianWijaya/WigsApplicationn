package com.iglo.common.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Login")
data class Login(
    @PrimaryKey
    @field:ColumnInfo(name = "User")
    val user: String,

    @field:ColumnInfo(name = "Password")
    val password: String
)