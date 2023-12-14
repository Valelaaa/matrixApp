package com.example.matrixapplication.domain.repository

import com.example.matrixapplication.domain.InputMatrix
import kotlinx.coroutines.flow.Flow

interface MatrixRepository {
    fun refreshMatrix()
    fun getMatrix(): Flow<InputMatrix>
}