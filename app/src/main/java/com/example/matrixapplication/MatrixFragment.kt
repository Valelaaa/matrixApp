package com.example.matrixapplication

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.example.matrixapplication.dataModel.MatrixRepository
import javax.inject.Inject

class MatrixFragment : Fragment() {


    private lateinit var viewModel: MatrixViewModel
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_matrix, container, false)

        val loggingView: TextView = view.findViewById(R.id.logging_view)
        var logingText = StringBuilder("")
        val matrixTable: TableLayout = view.findViewById(R.id.matrix_table)
        val rowsCount = 50
        val columnsCount = 50
        for (i in 0 until rowsCount) {
            val tableRow = TableRow(requireContext())

            for (j in 0 until columnsCount) {
                val textView = TextView(requireContext())
                textView.text = "[$i][$j]"
                logingText.append("[$i][$j]")
                textView.setPadding(10, 10, 10, 10)
                textView.setBackgroundResource(R.drawable.border)
                tableRow.addView(textView)
            }

            matrixTable.addView(tableRow)
        }

        loggingView.text = logingText

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MatrixViewModel::class.java)
        // TODO: Use the ViewModel
    }

}