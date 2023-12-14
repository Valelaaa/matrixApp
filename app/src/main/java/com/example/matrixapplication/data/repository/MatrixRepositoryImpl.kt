package com.example.matrixapplication.data.repository

import android.util.Log
import com.example.matrixapplication.domain.InputMatrix
import com.example.matrixapplication.domain.repository.MatrixRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class MatrixRepositoryImpl @Inject constructor() : MatrixRepository {
    private val tag = "MatrixRepositoryImpl"

    private val _matrixFlow = MutableStateFlow(createNewMatrix())

    private val matrixFlow = _matrixFlow.asStateFlow()

    init {
        Log.d(tag, "MatrixRepository created $this")
    }
    override fun refreshMatrix() {
        Log.d(tag, "Refreshing matrix")
        _matrixFlow.update { createNewMatrix() }
    }
    private fun createNewMatrix(): InputMatrix {
        return InputMatrix((10..100).random(), (10..100).random());
    }

    override fun getMatrix(): Flow<InputMatrix> = matrixFlow
}
