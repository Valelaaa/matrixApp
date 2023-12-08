package com.example.matrixapplication

import com.example.matrixapplication.dataModel.MatrixRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface RepositoryModule {
    @Provides
    @Singleton
    fun provideMatrixRepository(): MatrixRepository
}
