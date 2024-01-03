package com.example.matrixapplication.domain

import android.util.Log
import javax.inject.Singleton

@Singleton
class InputMatrix(val columns: Int = 10, val rows: Int = 10) {
    private val tag = "InputMatrix"
    private var matrix: Array<IntArray> = Array(rows) { IntArray(columns) { 0 } }
    private val minIncrease = 0
    private val maxIncrease = 10

    init {
        initializeMatrix()
//        Log.d(tag, "New instance of Input Matrix created $this")
    }

    private fun initializeMatrix() {
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                matrix[i][j] = if (i > 0 && j > 0) {
                    maxOf(
                        matrix[i - 1][j],
                        matrix[i][j - 1]
                    ) + (minIncrease..maxIncrease).random() + 1
                } else if (i > 0) {
                    matrix[i - 1][j] + (minIncrease..maxIncrease).random() + 1
                } else if (j > 0) {
                    matrix[i][j - 1] + (minIncrease..maxIncrease).random() + 1
                } else {
                    (minIncrease..maxIncrease).random() + 1
                }
            }
        }
    }


    fun getData(): Array<IntArray> = matrix
    override fun toString(): String {
        return matrix.joinToString(separator = "\n") { it ->
            it.joinToString(", ") {
                "$it"
            }
        }
    }

    private fun getReference(): String {
        return Integer.toHexString(System.identityHashCode(this))
    }

    fun findFirst(toFind: Int): Pair<Int, Int>? {
        if (toFind < matrix[0][0] || toFind > matrix[rows - 1][columns - 1])
            return null

        var result = Pair(0, 0)

        var startRow = 0
        var startColumns = 0
        var currentRows = rows - 1
        var currentColumns = columns - 1
        var middleRows = currentRows / 2
        var middleColumns = currentColumns / 2

        var firstQuarterMaxElement: Int
        var secondQuarterMaxElement: Int
        var thirdQuarterMaxElement: Int
        var fourthQuarterMaxElement: Int

        while (matrix[result.first][result.second] != toFind) {

            firstQuarterMaxElement = matrix[middleRows][middleColumns]
            secondQuarterMaxElement = matrix[middleRows][currentColumns]
            thirdQuarterMaxElement = matrix[currentRows][middleColumns]
            fourthQuarterMaxElement = matrix[currentRows][currentColumns]

            result = when (toFind) {
                firstQuarterMaxElement -> Pair(middleRows, middleColumns)
                secondQuarterMaxElement -> Pair(middleRows, currentColumns)

                thirdQuarterMaxElement -> Pair(currentRows, middleColumns)
                fourthQuarterMaxElement -> Pair(currentRows, currentColumns)
                else -> Pair(0, 0)
            }

            when {
                toFind < firstQuarterMaxElement -> {
                    currentRows = middleRows
                    currentColumns = middleColumns

                }

                toFind < secondQuarterMaxElement -> {
                    currentRows = middleRows
                    startColumns = middleColumns

                }

                toFind < thirdQuarterMaxElement -> {
                    startRow = middleRows
                    currentColumns = middleColumns
                }

                toFind < fourthQuarterMaxElement -> {
                    startRow = middleRows
                    startColumns = middleColumns

                }

                else -> {
                    if (matrix[result.first][result.second] == toFind)
                        return result
                    else
                        return null
                }
            }

            middleRows = (startRow + currentRows) / 2
            middleColumns = (startColumns + currentColumns) / 2

        }
        return result
    }
}
