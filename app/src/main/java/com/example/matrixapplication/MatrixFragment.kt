package com.example.matrixapplication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
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
        val refreshButton: Button = view.findViewById(R.id.refresh_matrix)
        var loggingText = StringBuilder("")

        lifecycleScope.launch {
            matrixTableView(viewModel, view, requireContext())
        }
        refreshButton.setOnClickListener {
            viewModel.refreshMatrix()
        }
        loggingView.text = loggingText
        loggingText.append(viewModel.logsText)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}