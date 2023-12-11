package com.example.matrixapplication

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class MatrixRepository @Inject constructor(var inputMatrix: InputMatrix)  {
    fun getMatrix(): Flow<InputMatrix> = flowOf(inputMatrix)
    fun refresh() = inputMatrix.refresh()
}
