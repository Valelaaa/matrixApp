package com.example.matrixapplication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.matrixapplication.databinding.FragmentMatrixBinding
import kotlinx.coroutines.launch

class MatrixFragment : Fragment() {
    private val viewModel: MatrixViewModel =
        (requireActivity() as MainActivity).appComponent.viewModelsFactory()
    private var _binder: FragmentMatrixBinding? = null
    private val binder: FragmentMatrixBinding
        get() = _binder!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binder = FragmentMatrixBinding.inflate(layoutInflater)

        lifecycleScope.launch {
            viewModel.matrixDataFlow.collect {
                matrixTableView(it,binder.matrixTable, requireContext())
            }
        }
        lifecycleScope.launch {
            viewModel.matrixDataFlow.collect {
                println(it)
            }
        }

        binder.refreshMatrix.setOnClickListener {
            viewModel.refreshMatrix()
            lifecycleScope.launch {
                viewModel.matrixDataFlow.collect {
                    updateMatrixTableView(it, binder.matrixTable, requireContext())
                }
            }
        }
        return binder.root
    }

    override fun onDestroyView() {
        _binder = null
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}