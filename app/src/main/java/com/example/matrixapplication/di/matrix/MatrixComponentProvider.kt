package com.example.matrixapplication.di.matrix

import com.example.matrixapplication.di.AppComponent

interface MatrixComponentProvider {
    fun provideAppComponent(): AppComponent
}