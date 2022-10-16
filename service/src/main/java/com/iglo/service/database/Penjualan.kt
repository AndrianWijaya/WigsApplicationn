package com.iglo.service.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.iglo.common.entity.Login
import com.iglo.service.dao.LoginDAO
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [Login::class],
    version = 1,
    exportSchema = false
)
abstract class Penjualan : RoomDatabase() {
    abstract fun loginDAO(): LoginDAO

    companion object {
        @Volatile
        private var instance: Penjualan? = null

        @JvmStatic
        fun getDatabase(context: Context, applicationScope: CoroutineScope): Penjualan {
            if (instance == null) {
                synchronized(Penjualan::class.java) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        Penjualan::class.java, "Penjualan"
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return instance as Penjualan
        }
    }
}