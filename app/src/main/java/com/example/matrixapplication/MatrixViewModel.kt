package com.example.matrixapplication

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MatrixViewModel @Inject constructor(private var matrixRepository: MatrixRepository): ViewModel() {
    val matrixDataFlow = matrixRepository.getMatrix()
    fun refreshMatrix() {
        matrixRepository.refreshMatrix()
    }
}
