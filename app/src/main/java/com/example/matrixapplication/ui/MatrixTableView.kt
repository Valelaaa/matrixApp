package com.example.matrixapplication.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.compose.ui.graphics.Color
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.matrixapplication.R
import com.example.matrixapplication.databinding.RecycleViewLotofCustomTextviewItemBinding
import com.example.matrixapplication.domain.InputMatrix
import com.example.matrixapplication.ui.PrimaryRVAdaptor.ConstraintRowHolder

private class CustomViewImplementation(private val binding: RecycleViewLotofCustomTextviewItemBinding) :
    ConstraintRowHolder(binding.root) {
    override fun bind(array: IntArray, highlighted: Int, col: Int) {
        binding.recycleViewCustomTextviewItem.setLine(array, col)
        if (highlighted != -1){
            binding.root.setBackgroundColor(0xC6FFC107.toInt())
        }
        else{
            binding.root.setBackgroundColor(0x00000000.toInt())
        }
    }
}

private val factory2 = object : ConstraintRowHolder.Factory {
    override fun create(view: ViewGroup, viewType: Int): ConstraintRowHolder =
        CustomViewImplementation(
            RecycleViewLotofCustomTextviewItemBinding.inflate(
                LayoutInflater.from(view.context),
                view,
                false
            )
        )
}

internal fun paintMatrixPosition(recyclerView: RecyclerView, row: Int, column: Int) {
    (recyclerView.adapter as? PrimaryRVAdaptor)?.apply {
        highLightElementByPosition(row, column)

        if(row >= 0 && column >= 0){
            val columnWidth = recyclerView.width / getColumnsCount()

            recyclerView.post {
                recyclerView.layoutManager?.scrollToPosition(row)
                (recyclerView.parent as HorizontalScrollView).scrollX = (column - 1) * columnWidth
            }
        }
    }

}

internal fun updateMatrixTableView(
    matrix: InputMatrix,
    recyclerView: RecyclerView,
    context: Context
) {
    if (recyclerView.adapter == null) {
        val rVAdapter = PrimaryRVAdaptor(factory = factory2)
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = rVAdapter
    }
    (recyclerView.adapter as? PrimaryRVAdaptor)?.submitList(matrix.getData().asList())
}

fun updateMatrixTableView(
    matrix: InputMatrix,
    matrixTable: TableLayout,
    context: Context
) {
    matrixTable.removeAllViews()

    for (i in 0 until matrix.rows) {
        val tableRow = TableRow(context)
        tableRow.id = i
        for (j in 0 until matrix.columns) {
            val textView = TextView(context)
            textView.text = matrix.getData()[i][j].toString()
            textView.setPadding(20, 10, 10, 10)
            textView.setBackgroundResource(R.drawable.border)
            tableRow.addView(textView)
            textView.id = (j * matrix.columns + i)
        }
        matrixTable.addView(tableRow)
    }
}