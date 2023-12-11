package com.example.matrixapplication

import android.content.Context
import dagger.BindsInstance
import dagger.Component

@Component(modules = [MatrixModule::class])
interface MatrixComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): MatrixComponent
    }
    fun inject(activity:MainActivity)
    fun inject(fragment:MatrixFragment)
    fun getMatrix(): InputMatrix
}