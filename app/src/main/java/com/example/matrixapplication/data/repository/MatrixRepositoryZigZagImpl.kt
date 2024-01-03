package com.example.matrixapplication.data.repository

import javax.inject.Inject

class MatrixRepositoryZigZagImpl @Inject constructor() : MatrixRepositoryImpl() {
    override fun findFirstIndexes(element: Int): Pair<Int, Int>? {
        val matrix = matrixFlow.value.getData()
        var rightIterator = matrix[0].size - 1
        var topIterator = 0

        while (rightIterator >= 0 && topIterator < matrix.size) {
            if (matrix[topIterator][rightIterator] == element)
                return Pair(topIterator, rightIterator)
            if (element < matrix[topIterator][rightIterator])
                rightIterator--
            else
                topIterator++
        }
        return null
    }
}