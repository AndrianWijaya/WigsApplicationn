package com.iglo.wigsapplicationn.di.module

import com.iglo.common.untils.AppExecutors
import com.iglo.service.auth.AuthDatabaseService
import com.iglo.service.dao.LoginDAO
import com.iglo.service.repository.LoginRepository
import com.iglo.service.use_case.ProductUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun providesLoginRepository(
        loginDao: LoginDAO,
        executors: AppExecutors,
        authDbService: AuthDatabaseService
    ) = LoginRepository(loginDao, executors,authDbService)

    @Provides
    fun provideProductUseCase() = ProductUseCase()

}