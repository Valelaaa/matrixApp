package com.example.matrixapplication.ui

import androidx.lifecycle.ViewModel
import com.example.matrixapplication.domain.repository.MatrixRepository

class MatrixViewModel constructor(private val matrixRepository: MatrixRepository): ViewModel() {
    val matrixDataFlow = matrixRepository.getMatrix()
    fun refreshMatrix() {
        matrixRepository.refreshMatrix()
    }
    fun getFoundIndexes(element: Int):Pair<Int,Int>?{
        return matrixRepository.findFirstIndexes(element).also {
            println("!!! getFoundIndexes($element) -> $it")
        }
    }
}
