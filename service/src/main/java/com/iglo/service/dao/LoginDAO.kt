package com.iglo.service.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.iglo.common.entity.Login

@Dao
interface LoginDAO {
    @Insert(onConflict = IGNORE)
    fun registerUser(login: Login)

    @Query("SELECT * FROM Login WHERE User = :user")
    fun getUser(user: String): LiveData<Login>
}