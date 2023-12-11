package com.example.matrixapplication.dataModel

class InputMatrix(
    private val columns: Int = 10,
    private val rows: Int = 10
) {
    private var matrix: Array<IntArray> = Array(rows) { IntArray(columns) { 0 } }

    init {
        for (i in 1 until rows) {
            for (j in 0 until columns) {
                matrix[i][j] = i * columns + j + 1
            }
        }
    }

    fun refresh() {
        for (i in 1 until rows) {
            for (j in 0 until columns) {
                matrix[i][j] = i * columns + j + 1
            }
        }
    }

    fun getMatrix(): Array<IntArray> = matrix.clone()
}
