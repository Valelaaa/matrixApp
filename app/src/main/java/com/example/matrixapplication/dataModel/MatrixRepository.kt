package com.example.matrixapplication.dataModel

import dagger.Provides
import kotlinx.coroutines.flow.Flow

interface MatrixRepository {
        @Provides
        fun getMatrix(): Flow<InputMatrix>

}