package com.example.matrixapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import javax.inject.Inject
import javax.inject.Singleton

class MatrixViewModel @Inject constructor(var matrixRepository:MatrixRepository) {
    val matrixLiveData: LiveData<InputMatrix> = matrixRepository.getMatrix().asLiveData()

    fun refreshMatrix(){
        matrixRepository.refresh()
    }
}