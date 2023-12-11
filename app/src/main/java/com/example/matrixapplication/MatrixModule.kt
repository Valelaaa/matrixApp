package com.example.matrixapplication

import dagger.Module
import dagger.Provides

@Module
class MatrixModule {
    @Provides
    fun provideDefaultInputMatrix(): InputMatrix {
        return InputMatrix(20, 20)
    }
}