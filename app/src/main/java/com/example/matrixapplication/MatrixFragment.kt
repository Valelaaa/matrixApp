package com.example.matrixapplication

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import javax.inject.Inject

class MatrixFragment @Inject constructor(var viewModel: MatrixViewModel) : Fragment() {
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_matrix, container, false)

        val loggingView: TextView = view.findViewById(R.id.logging_view)
        var logingText = StringBuilder("")
        val matrixTable: TableLayout = view.findViewById(R.id.matrix_table)

        var rowsCount = 0
        var columnsCount = 0
        viewModel.matrixLiveData.observe(viewLifecycleOwner) { inputMatrix ->
            if (inputMatrix != null) {
                val matrix = inputMatrix.getMatrix()
                rowsCount = matrix.size
                columnsCount = if (matrix.isNotEmpty()) matrix[0].size else 0

                Log.d("MainApplication", "rows: $rowsCount columns: $columnsCount")
            }
        }
        for (i in 0 until rowsCount) {
            val tableRow = TableRow(requireContext())

            for (j in 0 until columnsCount) {
                val textView = TextView(requireContext())
                textView.text = "[$i][$j]" // Здесь можно задать текст по умолчанию
                logingText.append("[$i][$j]")
                textView.setPadding(20, 10, 10, 10) // Установим отступы
                textView.setBackgroundResource(R.drawable.border)
                // Добавим TextView в TableRow
                tableRow.addView(textView)
            }

            // Добавим TableRow в TableLayout
            matrixTable.addView(tableRow)
        }

        loggingView.text = logingText

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}