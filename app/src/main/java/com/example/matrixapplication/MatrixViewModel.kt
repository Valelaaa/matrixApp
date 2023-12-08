package com.example.matrixapplication

import androidx.lifecycle.ViewModel
import com.example.matrixapplication.dataModel.MatrixRepository
import javax.inject.Inject

class MatrixViewModel @Inject constructor(private var matrixRepository: MatrixRepository) :
    ViewModel() {

}