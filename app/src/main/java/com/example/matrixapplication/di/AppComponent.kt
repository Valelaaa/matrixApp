package com.example.matrixapplication.di

import androidx.lifecycle.ViewModelStore
import com.example.matrixapplication.di.matrix.MatrixViewModelFactory
import com.example.matrixapplication.ui.MatrixFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, ViewModelModule::class], dependencies = [ViewModelStore::class])
interface AppComponent {
    fun inject(fragment: MatrixFragment)

    @Component.Builder
    interface Builder {
        fun dependency(vieModelStore: ViewModelStore): Builder
        fun build(): AppComponent
    }
}