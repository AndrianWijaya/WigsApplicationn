package com.iglo.wigsapplicationn.di.module

import android.content.Context
import com.iglo.common.untils.AppExecutors
import com.iglo.service.auth.AuthDatabaseService
import com.iglo.service.auth.AuthDatabaseServicePref
import com.iglo.service.database.Penjualan
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabasePenjualan(@ApplicationContext context: Context) = Penjualan.getDatabase(
        context,
        CoroutineScope(SupervisorJob())
    )

    @Provides
    @Singleton
    fun providesLoginDao(penjualanDatabase: Penjualan) = penjualanDatabase.loginDAO()


    @Provides
    @Singleton
    fun providesAuthDb(@ApplicationContext context: Context): AuthDatabaseService =
        AuthDatabaseServicePref(context)

    @Provides
    @Singleton
    fun providesExecutor() = AppExecutors()
}