package com.example.matrixapplication

import android.content.Context
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

suspend fun matrixTableView(viewModel: MatrixViewModel, currentView: View, context: Context):View {
    val matrixTable: TableLayout = currentView.findViewById(R.id.matrix_table)
    viewModel.matrixLiveData.collect {
        viewModel.rows = it.getMatrix().size
        viewModel.columns = it.getMatrix()[0].size

        for (i in 0 until viewModel.rows) {
            val tableRow = TableRow(context)

            for (j in 0 until viewModel.columns) {
                val textView = TextView(context)
                textView.text =
                    it.getMatrix()?.get(i)?.get(j)
                        .toString() // Здесь можно задать текст по умолчанию
                viewModel.logsText.append(textView.text.toString() + "\n")

                textView.setPadding(20, 10, 10, 10) // Установим отступы
                textView.setBackgroundResource(R.drawable.border)
                tableRow.addView(textView)
            }

            matrixTable.addView(tableRow)
        }

    }
    return matrixTable
}