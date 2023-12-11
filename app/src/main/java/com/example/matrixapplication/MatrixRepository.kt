package com.example.matrixapplication

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class MatrixRepository @Inject constructor(var inputMatrix: InputMatrix)  {
    private val _matrixFlow = MutableStateFlow(inputMatrix)

    private val matrixFlow = _matrixFlow.asStateFlow()
    fun refreshMatrix() {
        Log.d("MatrixRepository","Refresh Matrix in Matrix Repository")
        _matrixFlow.update { inputMatrix.createNewInstance()}
    }

    fun getMatrix(): Flow<InputMatrix> = matrixFlow
}
