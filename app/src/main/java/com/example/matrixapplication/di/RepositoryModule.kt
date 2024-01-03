package com.example.matrixapplication.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.example.matrixapplication.data.repository.MatrixRepositoryImpl
import com.example.matrixapplication.data.repository.MatrixRepositoryZigZagImpl
import com.example.matrixapplication.di.matrix.MatrixViewModelFactory
import com.example.matrixapplication.domain.repository.MatrixRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface RepositoryModule {
    @Binds
    fun bindsMatrixRepository(matrixRepository: MatrixRepositoryZigZagImpl): MatrixRepository
}

@Module
class ViewModelModule {
    @Provides
    fun provideMatrixViewModelFactory(
        viewModelStore: ViewModelStore,
        repository: MatrixRepository
    ): ViewModelProvider.Factory = MatrixViewModelFactory(viewModelStore, repository)
}