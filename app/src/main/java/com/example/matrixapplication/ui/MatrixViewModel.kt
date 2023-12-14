package com.example.matrixapplication.ui

import androidx.lifecycle.ViewModel
import com.example.matrixapplication.data.repository.MatrixRepositoryImpl
import com.example.matrixapplication.domain.repository.MatrixRepository
import javax.inject.Inject

class MatrixViewModel constructor(private val matrixRepository: MatrixRepository): ViewModel() {
    val matrixDataFlow = matrixRepository.getMatrix()
    fun refreshMatrix() {
        matrixRepository.refreshMatrix()
    }
}
