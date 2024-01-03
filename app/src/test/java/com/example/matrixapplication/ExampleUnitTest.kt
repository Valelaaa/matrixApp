package com.example.matrixapplication

import com.example.matrixapplication.domain.InputMatrix
import org.junit.Test

import org.junit.Assert.*
import java.lang.Exception
import java.util.Objects

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testMatrixFindNotNull() {
        println("TestStarted")
        val matrix = InputMatrix(50, 50)
        println("Matrix created")
        for (i in 0..matrix.rows - 1) {
            for (j in 0..matrix.columns - 1) {
                val toFind = matrix.getData()[i][j]
                try {
                    val pair = matrix.findFirst(toFind)
                    assertEquals(toFind, matrix.getData()[pair!!.first][pair!!.second])
                } catch (np: Exception) {
                    val inCorrect = matrix.findFirst(toFind)
                }

            }
        }
    }


    @Test
    fun findTestNull() {
        println("TestStarted")
        val matrix = InputMatrix(100, 100)
        println("Matrix created")
        val parameters = intArrayOf(0, 200)
        for (i in 0..parameters.size - 1) {
            val pair = matrix.findFirst(parameters[i])
            assertNull(pair)
        }

    }
}