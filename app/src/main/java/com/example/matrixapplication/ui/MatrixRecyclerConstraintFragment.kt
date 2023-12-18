package com.example.matrixapplication.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.matrixapplication.databinding.RecyclerviewFragmentMultitextviewBinding
import com.example.matrixapplication.di.matrix.MatrixComponentProvider
import dagger.Lazy
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class MatrixRecyclerConstraintFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>


    private val viewModel: MatrixViewModel by viewModels { viewModelFactory.get() }

    private var _binder: RecyclerviewFragmentMultitextviewBinding? = null
    private val binder: RecyclerviewFragmentMultitextviewBinding
        get() = _binder!!


    override fun onAttach(context: Context) {
        (context as? MatrixComponentProvider)?.provideAppComponent()?.inject(this)
            ?: throw IllegalStateException(
                "Illegal State onAttach MatrixRecyclerConstraintFragment inject" +
                        "(provide component)"
            )
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binder = RecyclerviewFragmentMultitextviewBinding.inflate(layoutInflater)

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

    @OptIn(ExperimentalTime::class)
    private fun displayMatrix() {
        val timedValue = measureTimedValue {
            lifecycleScope.launch {
                val startTime: Long = System.currentTimeMillis()
                viewModel.matrixDataFlow.collect {
                    updateMatrixTableView(it, binder.matrixRecyclerView, requireContext())
                }
                val endTime: Long = System.currentTimeMillis()
                Log.d(tag, "Matrix Creation Time:: " + (startTime - endTime).toString())
            }
        }
        Log.d(tag, timedValue.duration.toString())
    }

    companion object {
        fun newInstance(): Fragment {
            return MatrixRecyclerConstraintFragment()
        }
    }
}
