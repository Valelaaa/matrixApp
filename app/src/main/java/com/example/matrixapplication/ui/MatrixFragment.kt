package com.example.matrixapplication.ui

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.matrixapplication.databinding.FragmentMatrixBinding
import com.example.matrixapplication.di.matrix.MatrixComponentProvider
import dagger.Lazy
import kotlinx.coroutines.launch
import java.lang.IllegalStateException
import java.time.Instant
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class MatrixFragment : Fragment() {
    private val TAG = "MatrixFragment"

    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>


    private val viewModel: MatrixViewModel by viewModels { viewModelFactory.get() }

    private var _binder: FragmentMatrixBinding? = null
    private val binder: FragmentMatrixBinding
        get() = _binder!!

    override fun onAttach(context: Context) {
        (context as? MatrixComponentProvider)?.provideAppComponent()?.inject(this)
            ?: throw IllegalStateException("Provide Component")
        super.onAttach(context)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binder = FragmentMatrixBinding.inflate(layoutInflater)

        displayMatrix()

        binder.refreshMatrix.setOnClickListener {
            viewModel.refreshMatrix()
            displayMatrix()
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

    @OptIn(ExperimentalTime::class)
    private fun displayMatrix() {
        val timedValue = measureTimedValue {
            lifecycleScope.launch {
                val startTime: Long = System.currentTimeMillis()
                viewModel.matrixDataFlow.collect {
                    updateMatrixTableView(it, binder.matrixTable, requireContext())
                }
                val endTime: Long = System.currentTimeMillis()
                Log.d(tag, (startTime - endTime).toString())
            }
        }
        Log.d(TAG, timedValue.duration.toString())

    }

    companion object {
        fun newInstance(): Fragment {
            return MatrixFragment()
        }
    }
}