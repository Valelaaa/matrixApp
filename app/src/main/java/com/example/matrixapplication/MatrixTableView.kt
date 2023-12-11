package com.example.matrixapplication

import android.content.Context
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

fun matrixTableView(
    matrix: InputMatrix,
    matrixTable: TableLayout,
    context: Context
) {
    for (i in 0 until matrix.rows) {
        val tableRow = TableRow(context)

        for (j in 0 until matrix.columns) {
            val textView = TextView(context)
            textView.text = matrix.getData()[i][j].toString()
            textView.setPadding(20, 10, 10, 10)
            textView.setBackgroundResource(R.drawable.border)
            tableRow.addView(textView)
        }
        matrixTable.addView(tableRow)
    }
}

fun updateMatrixTableView(
    matrix: InputMatrix,
    matrixTable: TableLayout,
    context: Context
) {
    matrixTable.removeAllViews()

    for (i in 0 until matrix.rows) {
        val tableRow = TableRow(context)

        for (j in 0 until matrix.columns) {
            val textView = TextView(context)
            textView.text = matrix.getData()[i][j].toString()
            textView.setPadding(20, 10, 10, 10)
            textView.setBackgroundResource(R.drawable.border)
            tableRow.addView(textView)
        }
        matrixTable.addView(tableRow)
    }
}