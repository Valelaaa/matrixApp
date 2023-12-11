package com.example.matrixapplication

import android.util.Log
import javax.inject.Singleton

@Singleton
class InputMatrix(val columns: Int = 10, val rows: Int = 10) {
    private var matrix: Array<IntArray> = Array(rows) { IntArray(columns) { 0 } }

    init {
        initializeMatrix()
    }

    private fun initializeMatrix() {
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                matrix[i][j] = if (i > 0 && j > 0) {
                    maxOf(matrix[i - 1][j], matrix[i][j - 1]) + (0..10).random() + 1
                } else if (i > 0) {
                    matrix[i - 1][j] + (0..10).random() + 1
                } else if (j > 0) {
                    matrix[i][j - 1] + (0..10).random() + 1
                } else {
                    (0..10).random() + 1
                }
            }
        }
    }


    fun createNewInstance(): InputMatrix {
        Log.d("InputMatrix", "Refreshing matrix")
        val newMatrix = InputMatrix(columns, rows)
        newMatrix.initializeMatrix()
        return newMatrix
    }

    fun getData(): Array<IntArray> = matrix
    override fun toString(): String {
        return matrix.joinToString(separator = "\n") {
            it.joinToString(", "){
                "$it"
            }
        }
    }

}
