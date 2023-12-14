package com.example.matrixapplication.di.matrix

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.example.matrixapplication.domain.repository.MatrixRepository
import com.example.matrixapplication.ui.MatrixViewModel
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

class MatrixViewModelFactory constructor(
    private val viewModelStore: ViewModelStore?,
    private val repository: MatrixRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MatrixViewModel(repository) as T
}