package com.iglo.wigsapplicationn.di.module

import com.iglo.service.use_case.ProductUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun provideProductUseCase() = ProductUseCase()
}